package main

import (
	"fmt"
	"strings"
)

func main() {
	var (
		size  int
		shift int
	)

	fmt.Scan(&size)
	fmt.Scan(&shift)

	var numbers = make([]int, size)
	for x := 0; x < size; x++ {
		fmt.Scan(&numbers[x])
	}

	var indexes []int = make([]int, size)
	for i, n := range numbers {
		var current int

		if i > shift {
			current = i - shift
		} else {
			current = (i + size) - shift
		}

		if current >= size {
			current = 0
		}

		indexes[current] = n
	}

	for i, n := range indexes {
		numbers[i] = n
	}

	fmt.Println(strings.Trim(fmt.Sprint(numbers), "[]"))
}
