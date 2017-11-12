package main

import (
	"fmt"
)

func main() {
	var students int
	fmt.Scan(&students)

	grades := make([]int, students)
	for c := 0; c < students; c++ {
		fmt.Scan(&grades[c])
	}

	for _, grade := range grades {
		if (grade%5) == 0 || (40-grade) >= 3 || grade >= 100 {
			fmt.Println(grade)
			continue
		}

		for x := 1; x < 3; x++ {
			if ((grade + x) % 5) == 0 {
				grade = grade + x
				break
			}
		}

		fmt.Println(grade)
	}
}
