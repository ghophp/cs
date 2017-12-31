// TODO: finish
package main

import "fmt"

const (
	AddOperation  = "add"
	FindOperation = "find"
)

type (
	Operation struct {
		Operation string
		Value     string
	}
)

func main() {
	var nOperations int
	fmt.Scan(&nOperations)

	var operations = make([]*Operation, 0, nOperations)
	for x := 0; x < nOperations; x++ {
		var current = &Operation{}
		fmt.Scan(&current.Operation, &current.Value)

		operations = append(operations, current)
	}

	// TODO: implement the Trie add and find
}
