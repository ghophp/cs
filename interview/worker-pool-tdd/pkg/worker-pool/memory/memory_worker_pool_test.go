package memory

import (
	"fmt"
	"testing"
)

func TestNewMemoryWorkerPool(t *testing.T) {
	pool := NewMemoryWorkerPool(10)
	if pool == nil {
		t.Errorf("Expected pool to be non-nil")
	}
}

func TestMemoryWorkerPool_StartRunsTasksAndStopsWaitsForTasksToFinish(t *testing.T) {
	pool := NewMemoryWorkerPool(10)
	pool.Start()

	counter := 0
	addCounter := func() {
		counter++
	}

	pool.AddTask(addCounter)
	pool.AddTask(addCounter)
	pool.AddTask(addCounter)

	pool.Stop()

	if counter != 3 {
		t.Errorf("Expected counter to be 3, got %d", counter)
	}
}

func TestMemoryWorkerPool_ConcurrentExecution(t *testing.T) {
	// Create a pool with fewer workers than tasks to ensure some tasks must run concurrently
	pool := NewMemoryWorkerPool(2)
	pool.Start()

	// Use channels for synchronization
	start := make(chan struct{})  // Signal to start all tasks
	running := make(chan int, 10) // Track which tasks are running
	done := make(chan int, 10)    // Track which tasks have completed

	// Create 4 tasks that all block waiting for the start signal
	for i := 0; i < 4; i++ {
		taskNum := i
		pool.AddTask(func() {
			fmt.Println("Starting task")
			// Wait for start signal
			<-start

			// Report that this task is running
			running <- taskNum

			// Simulate work
			for j := 0; j < 10000; j++ {
			}

			fmt.Println("Task completed")
			// Report completion
			done <- taskNum
		})
	}

	// Create a goroutine to check for concurrent execution
	concurrentTasks := make(chan int, 1)
	go func() {
		runningTasks := make(map[int]bool)
		maxConcurrent := 0

		// Release the tasks
		close(start)

		// Count how many tasks report as running before any complete
		for i := 0; i < 4; i++ {
			select {
			case task := <-running:
				runningTasks[task] = true
				if len(runningTasks) > maxConcurrent {
					maxConcurrent = len(runningTasks)
				}
			case <-done:
				// A task completed before all started running
			}
		}

		// Drain any remaining messages
		for len(running) > 0 {
			<-running
		}
		for len(done) > 0 {
			<-done
		}

		concurrentTasks <- maxConcurrent
	}()

	// Wait for all tasks to complete
	pool.Stop()

	// Check if tasks ran concurrently
	maxConcurrent := <-concurrentTasks
	close(concurrentTasks)

	// With 2 workers, we should see at least 2 tasks running concurrently
	if maxConcurrent < 2 {
		t.Errorf("Expected at least 2 concurrent tasks, but got %d", maxConcurrent)
	}
}
