package main

import (
	"fmt"
)

func main() {
	var candles int
	fmt.Scan(&candles)

	candleHeights := make([]int, candles)
	for c := 0; c < candles; c++ {
		fmt.Scan(&candleHeights[c])
	}

	var (
		tallest int = -1
		counter     = map[int]int{}
	)

	for _, c := range candleHeights {
		if c > tallest {
			tallest = c
		}

		_, ok := counter[c]
		if !ok {
			counter[c] = 1
		} else {
			counter[c] = counter[c] + 1
		}
	}

	fmt.Println(counter[tallest])
}
