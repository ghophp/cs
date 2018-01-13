package main

import (
	"fmt"
	"math"
)

func main() {
	var lineLength int
	fmt.Scan(&lineLength)

	var (
		sumLeft  int
		sumRight int
		matrix   = make([][]int, lineLength)
	)

	for x := 0; x < lineLength; x++ {
		matrix[x] = make([]int, lineLength)
		for i := 0; i < lineLength; i++ {
			fmt.Scan(&matrix[x][i])
		}

		sumLeft = sumLeft + matrix[x][x]
		sumRight = sumRight + matrix[x][(lineLength-1)-x]
	}

	fmt.Print(int(math.Abs(float64(sumLeft - sumRight))))
}
