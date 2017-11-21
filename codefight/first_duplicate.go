package main

import (
	"fmt"
)

func firstDuplicate(a []int) int {
	for _, dupe := range a {
		if dupe < 0 {
			dupe = -dupe
		}

		if a[dupe-1] < 0 {
			return dupe
		}

		a[dupe-1] = -a[dupe-1]
	}

	return -1
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be 3: %d", firstDuplicate([]int{2, 3, 3, 1, 5, 2})))
}
