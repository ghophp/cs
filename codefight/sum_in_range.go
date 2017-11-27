package main

import (
	"fmt"
	"math"
)

var (
	mod10plus7 = int(math.Pow(10, 9) + 7)
)

func sumInRange(nums []int, queries [][]int) int {
	var (
		prefix = make([]int, len(nums)+1)
		sum    int
	)

	for x := 0; x < len(nums); x++ {
		sum = sum + nums[x]
		prefix[x+1] = sum
	}

	sum = 0
	for _, query := range queries {
		sum = sum + (prefix[query[1]+1] - prefix[query[0]])
	}

	return (sum + mod10plus7) % mod10plus7
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be 10: %v", sumInRange([]int{3, 0, -2, 6, -3, 2}, [][]int{[]int{0, 2}, []int{2, 5}, []int{0, 5}})))
}
