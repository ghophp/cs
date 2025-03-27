package main

import (
	"testing"
)

type testCaseIsValid struct {
	phoneNumber string
	expected    bool
	message     string
}

func TestPhoneNumber_IsValid(t *testing.T) {
	testCases := []testCaseIsValid{
		{phoneNumber: "+11234567890", expected: true, message: "Phone number with +1 and 10 digits should be valid"},
		{phoneNumber: "+491234567890", expected: true, message: "Phone number from +49 with 10 digits should be valid"},
		{phoneNumber: "+1123456789", expected: false, message: "Phone number with less than 10 digits should not be valid"},
		{phoneNumber: "11234567890", expected: false, message: "Phone number without + should not be valid"},
		{phoneNumber: "+1 1234567890", expected: false, message: "Phone number with non-numeric characters after the country code should not be valid"},
	}

	for _, testCase := range testCases {
		t.Run(testCase.message, func(t *testing.T) {
			phoneNumber := NewPhoneNumber(testCase.phoneNumber)
			if phoneNumber.IsValid() != testCase.expected {
				t.Errorf("Expected %v, got %v, for test case %s", testCase.expected, phoneNumber.IsValid(), testCase.message)
			}
		})
	}
}
