package main

import (
	"fmt"
)

func main() {
	// Example phone numbers
	phoneNumbers := []string{
		"+11234567890",  // Valid
		"+491234567890", // Valid
		"+1123456789",   // Invalid (too short)
		"11234567890",   // Invalid (no +)
		"+1 1234567890", // Invalid (has space)
	}

	for _, number := range phoneNumbers {
		phone := NewPhoneNumber(number)
		fmt.Printf("Phone number: %s is valid: %v\n", number, phone.IsValid())
	}
}
