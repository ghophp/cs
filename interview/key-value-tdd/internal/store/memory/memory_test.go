package memory

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

func TestMemoryStore_Get(t *testing.T) {
	store := NewMemoryStore()
	store.Set("key", "value", 10*time.Second)
	value, err := store.Get("key")
	if err != nil {
		t.Fatal(err)
	}
	if value != "value" {
		t.Fatalf("expected value to be 'value', got '%s'", value)
	}
}

func TestMemoryStore_Get_NotFound(t *testing.T) {
	store := NewMemoryStore()
	value, err := store.Get("non-existent-key")
	if err == nil {
		t.Fatalf("expected error to be a NotFoundError, got nil")
	}
	if value != "" {
		t.Fatalf("expected value to be empty, got '%s'", value)
	}
	errNotFound, ok := err.(*NotFoundError)
	if !ok {
		t.Fatalf("expected error to be a NotFoundError, got %T", err)
	}
	if errNotFound.Error() != "key non-existent-key not found" {
		t.Fatalf("expected error to be 'key non-existent-key not found', got '%s'", errNotFound.Error())
	}
	if errNotFound.key != "non-existent-key" {
		t.Fatalf("expected error key to be 'non-existent-key', got '%s'", errNotFound.key)
	}
}

func TestMemoryStore_Set_Expiry(t *testing.T) {
	store := NewMemoryStore()
	store.Set("key", "value", 10*time.Millisecond)
	time.Sleep(10 * time.Millisecond)
	value, err := store.Get("key")
	if err == nil {
		t.Fatalf("expected error to be a NotFoundError, got nil")
	}
	if value != "" {
		t.Fatalf("expected value to be empty, got '%s'", value)
	}
	errNotFound, ok := err.(*NotFoundError)
	if !ok {
		t.Fatalf("expected error to be a NotFoundError, got %T", err)
	}
	if errNotFound.Error() != "key key not found" {
		t.Fatalf("expected error to be 'key key not found', got '%s'", errNotFound.Error())
	}
}

func TestMemoryStore_Set(t *testing.T) {
	store := NewMemoryStore()
	err := store.Set("key", "value", 10*time.Second)
	if err != nil {
		t.Fatal(err)
	}
}

func TestMemoryStore_Set_Concurrent(t *testing.T) {
	store := NewMemoryStore()
	wg := sync.WaitGroup{}
	wg.Add(4)

	errCh := make(chan error, 4)
	setKey := func(key string, value string) {
		defer wg.Done()
		err := store.Set(key, value, 10*time.Second)
		if err != nil {
			errCh <- err
		}
	}

	go setKey("key", "value1")
	go setKey("key", "value2")
	go setKey("key", "value3")
	go setKey("key", "value4")

	wg.Wait()
	close(errCh)

	for err := range errCh {
		t.Fatal(err)
	}

	value, err := store.Get("key")
	if err != nil {
		t.Fatal(err)
	}
	if value == "" {
		t.Fatalf("expected value to be set, got '%s'", value)
	}

	fmt.Printf("the winner of the race is: %s\n", value)
}

func TestMemoryStore_Delete(t *testing.T) {
	store := NewMemoryStore()
	store.Set("key", "value", 10*time.Second)
	err := store.Delete("key")
	if err != nil {
		t.Fatal(err)
	}

	value, err := store.Get("key")
	if err != nil {
		if err, ok := err.(*NotFoundError); !ok {
			t.Fatalf("expected error to be a NotFoundError, got %T", err)
		}
	}
	if value != "" {
		t.Fatalf("expected value to be empty, got '%s'", value)
	}
}
