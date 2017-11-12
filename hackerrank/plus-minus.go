package main

import "fmt"

func main() {
	var size int
	fmt.Scan(&size)

	elements := make([]int, size)
	for c := 0; c < size; c++ {
		fmt.Scan(&elements[c])
	}

	var (
		nPositive int
		nNegative int
		nZero     int
	)

	for _, e := range elements {
		if e == 0 {
			nZero++
			continue
		}
		if e > 0 {
			nPositive++
		} else {
			nNegative++
		}
	}

	var (
		fractionPositive = float64(nPositive) / float64(size)
		fractionNegative = float64(nNegative) / float64(size)
		fractionZero     = float64(nZero) / float64(size)
	)

	fmt.Println(fmt.Sprintf("%f", fractionPositive))
	fmt.Println(fmt.Sprintf("%f", fractionNegative))
	fmt.Println(fmt.Sprintf("%f", fractionZero))
}
