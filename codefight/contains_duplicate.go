package main

import (
	"fmt"
)

func containsDuplicates(a []int) bool {
	if len(a) <= 1 {
		return false
	}

	var repeated = map[int]bool{}

	for x := 0; x < len(a); x++ {
		if _, ok := repeated[a[x]]; ok {
			return true
		}

		repeated[a[x]] = true
	}

	return false
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be true: %v", containsDuplicates([]int{2, 1, 5, 2})))
	fmt.Println(fmt.Sprintf("Expected output to be false: %v", containsDuplicates([]int{2, 1, 5, 4})))
	fmt.Println(fmt.Sprintf("Expected output to be true: %v", containsDuplicates([]int{2, 1, 5, 4, 6, 7, 7})))
	fmt.Println(fmt.Sprintf("Expected output to be false: %v", containsDuplicates([]int{2, 1, 5, 10})))
}
