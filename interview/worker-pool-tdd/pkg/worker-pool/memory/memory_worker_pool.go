package memory

import (
	"fmt"
	"sync"
)

type MemoryWorkerPool struct {
	workers int
	tasks   chan func()
	quit    chan struct{}
	wg      sync.WaitGroup
}

func NewMemoryWorkerPool(numWorkers int) *MemoryWorkerPool {
	return &MemoryWorkerPool{
		workers: numWorkers,
		tasks:   make(chan func(), numWorkers),
		quit:    make(chan struct{}),
		wg:      sync.WaitGroup{},
	}
}

func (m *MemoryWorkerPool) AddTask(task func()) {
	fmt.Println("Adding task")

	select {
	case m.tasks <- task:
		// Task added to channel
	case <-m.quit:
		// Pool is stopped, don't add more tasks
	}
}

func (m *MemoryWorkerPool) Start() {
	fmt.Println("Starting workers")

	for i := 0; i < m.workers; i++ {
		m.wg.Add(1)
		go func() {
			defer m.wg.Done()
			for {
				fmt.Println("Waiting for task")
				select {
				case task, ok := <-m.tasks:
					fmt.Println("Received task")
					if !ok {
						return
					}
					task()
				case <-m.quit:
					fmt.Println("Quitting")
					return
				}
			}
		}()
	}
}

func (m *MemoryWorkerPool) Stop() {
	fmt.Println("Stopping workers")

	close(m.quit)
	close(m.tasks)
	m.wg.Wait()
}
