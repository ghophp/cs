package main

import (
	"fmt"
)

const (
	alphabet = "abcdefghijlmnopqrstuvxzwyk"
)

func firstNotRepeatingCharacter(s string) string {
	var alphabetHash = map[byte]int{}

	for x := 0; x < len(alphabet); x++ {
		alphabetHash[alphabet[x]] = -1
	}

	for x := len(s) - 1; x >= 0; x-- {
		if alphabetHash[s[x]] == 0 {
			continue
		}

		if alphabetHash[s[x]] < 0 {
			alphabetHash[s[x]] = x + 1
		} else {
			alphabetHash[s[x]] = 0
		}
	}

	var lowestIndex int = -1
	for _, index := range alphabetHash {
		if index <= 0 {
			continue
		}
		if lowestIndex < 0 || index < lowestIndex {
			lowestIndex = index
		}
	}

	if lowestIndex < 0 {
		return "_"
	}

	return string(s[lowestIndex-1])
}

func main() {
	fmt.Println(fmt.Sprintf("Expected output to be c: %s", firstNotRepeatingCharacter("abacabad")))
	fmt.Println(fmt.Sprintf("Expected output to be _: %s", firstNotRepeatingCharacter("abacabaabacaba")))
	fmt.Println(fmt.Sprintf("Expected output to be z: %s", firstNotRepeatingCharacter("z")))
}
