package main

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"

	"github.com/ghophp/cs/interview/worker-pool-tdd/pkg/worker-pool/memory"
)

func main() {
	pool := memory.NewMemoryWorkerPool(10)
	pool.AddTask(func() {
		fmt.Println("Hello, World!")
	})
	pool.Start()

	// Create a channel to listen for signals
	sigChan := make(chan os.Signal, 1)
	signal.Notify(sigChan, syscall.SIGINT, syscall.SIGTERM)

	// Wait for either Ctrl+C (SIGINT) or Ctrl+Q (SIGTERM)
	<-sigChan

	pool.Stop()
}
