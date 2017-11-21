package main

import (
	"fmt"
	"sort"
)

func sumOfTwo(a []int, b []int, v int) bool {
	sort.Ints(a)
	sort.Ints(b)

	for x := 0; x < len(a); x++ {
		for i := 0; i < len(b); i++ {
			var (
				sum int = a[x] + b[i]
			)

			if sum == v {
				return true
			} else if sum > v {
				break
			}
		}
	}

	return false
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be true: %v", sumOfTwo([]int{2, 1, 5, 6, 7, 10, 5, 2}, []int{2, 1, 5, 6, 7, 8, 10, 12}, 3)))
	fmt.Println(fmt.Sprintf("Expected output to be false: %v", sumOfTwo([]int{2, 1}, []int{2, 1}, 7)))
}
