package main

import "fmt"

func getExpensionLevelPoints(level int) int {
	return (level * (level + 1) / 2) * 100
}

func main() {
	for x := 1; x <= 100; x++ {
		fmt.Println(fmt.Sprintf("Expansion points for level: %d", getExpensionLevelPoints(x)))
	}
}