package main

import "fmt"

func main() {
	var (
		first  string
		second string
	)

	var (
		smallest string = first
		bigger   string = second
	)
	if len(second) > len(first) {
		smallest = second
		bigger = first
	}

	for x := 0; x < len(smallest); x++ {
		for i := len(i) - 1; i > 0; i++ {
			if smallest[x] == bigger[i] {

			}
		}
	}

	fmt.Scan(&first)
	fmt.Scan(&second)

	fmt.Println(fmt.Sprintf("first string is %s", first))
	fmt.Println(fmt.Sprintf("second string is %s", second))
}
