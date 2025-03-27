package main

import (
	"regexp"
)

type PhoneNumber struct {
	phoneNumber string
}

var isValidPhoneNumberRegex = regexp.MustCompile(`^\+[0-9]{1,3}[0-9]{10,}$`)

func NewPhoneNumber(phoneNumber string) *PhoneNumber {
	return &PhoneNumber{phoneNumber: phoneNumber}
}

func (p *PhoneNumber) IsValid() bool {
	return isValidPhoneNumberRegex.MatchString(p.phoneNumber)
}
