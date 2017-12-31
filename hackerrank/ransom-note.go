package main

import "fmt"

func main() {
	var (
		nMagazine   int
		nRansomNote int
		nFound      int
	)

	fmt.Scan(&nMagazine)
	fmt.Scan(&nRansomNote)

	var (
		magazine = make(map[string]int, nMagazine)
	)

	for x := 0; x < nMagazine; x++ {
		var current string
		fmt.Scan(&current)

		magazine[current]++
	}

	for x := 0; x < nRansomNote; x++ {
		var current string
		fmt.Scan(&current)

		if _, ok := magazine[current]; ok && magazine[current] > 0 {
			nFound++
			magazine[current]--
		}
	}

	if nFound >= nRansomNote {
		fmt.Println("Yes")
	} else {
		fmt.Println("No")
	}
}
