package memory

import (
	"fmt"
	"sync"
	"time"
)

type MemoryStore struct {
	mu sync.RWMutex
	db map[string]string
}

type NotFoundError struct {
	key string
}

func (e *NotFoundError) Error() string {
	return fmt.Sprintf("key %s not found", e.key)
}

func NewMemoryStore() *MemoryStore {
	return &MemoryStore{
		db: make(map[string]string),
	}
}

func (s *MemoryStore) Get(key string) (string, error) {
	s.mu.RLock()
	defer s.mu.RUnlock()
	value, ok := s.db[key]
	if !ok {
		return "", &NotFoundError{key: key}
	}
	return value, nil
}

func (s *MemoryStore) Set(key string, value string, ttl time.Duration) error {
	s.mu.Lock()
	defer s.mu.Unlock()
	s.db[key] = value
	go func() {
		time.Sleep(ttl)
		s.mu.Lock()
		delete(s.db, key)
		s.mu.Unlock()
	}()
	return nil
}

func (s *MemoryStore) Delete(key string) error {
	s.mu.Lock()
	defer s.mu.Unlock()
	delete(s.db, key)
	return nil
}
