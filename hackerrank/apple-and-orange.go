package main

import (
	"fmt"
)

func main() {
	var (
		houseStart int
		houseEnd   int
	)

	fmt.Scan(&houseStart)
	fmt.Scan(&houseEnd)

	var (
		appleTree  int
		orangeTree int
	)

	fmt.Scan(&appleTree)
	fmt.Scan(&orangeTree)

	var (
		nApples  int
		nOranges int
	)

	fmt.Scan(&nApples)
	fmt.Scan(&nOranges)

	apples := make([]int, nApples)
	for c := 0; c < nApples; c++ {
		fmt.Scan(&apples[c])
	}

	oranges := make([]int, nOranges)
	for c := 0; c < nOranges; c++ {
		fmt.Scan(&oranges[c])
	}

	var (
		applesFallAtHouse  int
		orangesFallAtHouse int
	)

	for _, a := range apples {
		var fallPosition int = appleTree + a
		if fallPosition >= houseStart && fallPosition <= houseEnd {
			applesFallAtHouse++
		}
	}

	for _, o := range oranges {
		var fallPosition int = orangeTree + o
		if fallPosition >= houseStart && fallPosition <= houseEnd {
			orangesFallAtHouse++
		}
	}

	fmt.Println(applesFallAtHouse)
	fmt.Println(orangesFallAtHouse)
}
