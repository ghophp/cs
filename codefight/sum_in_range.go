package main

import (
	"fmt"
	"math"
)

var mod10plus7 = int(math.Pow(10, 9) + 7)

func hashIt(x, y int) string {
	return fmt.Sprintf("%d.%d", x, y)
}

// if I know the sum from 0,10 the sum from 3,7 is inside of that index, so I need
// to figure it out how to take this difference

func sumInRange(nums []int, queries [][]int) int {
	var (
		hashSet = map[string]int{}
		sum     int
	)

	for x := 0; x < len(queries); x++ {
		var hashed string = hashIt(queries[x][0], queries[x][1])
		if v, ok := hashSet[hashed]; ok {
			sum = sum + v
			continue
		}

		var innerSum int
		for i := queries[x][0]; i <= queries[x][1]; i++ {
			innerSum = innerSum + nums[i]
		}

		hashSet[hashed] = innerSum

		sum = sum + innerSum
	}

	return (sum + mod10plus7) % mod10plus7
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be 10: %v", sumInRange([]int{3, 0, -2, 6, -3, 2}, [][]int{[]int{0, 2}, []int{2, 5}, []int{0, 5}})))
}
