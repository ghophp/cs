package main

import "fmt"

const (
	EmptyRepresentation = " "
	StepRepresentation  = "#"
)

func renderStep(height, n int) {
	for x := 0; x < height-n; x++ {
		fmt.Print(EmptyRepresentation)
	}
	for x := 0; x < n; x++ {
		fmt.Print(StepRepresentation)
	}

	if n >= height {
		return
	} else {
		fmt.Print("\n")
	}

	renderStep(height, n+1)
}

func main() {
	var height int
	fmt.Scan(&height)

	renderStep(height, 1)
}
