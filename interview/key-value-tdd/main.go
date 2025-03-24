package main

import (
	"fmt"
	"log"
	"time"

	"github.com/ghophp/cs/interview/key-value-tdd/internal/store/memory"
)

func main() {
	store := memory.NewMemoryStore()
	store.Set("key", "value", 10*time.Second)
	value, err := store.Get("key")
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(value)
}
