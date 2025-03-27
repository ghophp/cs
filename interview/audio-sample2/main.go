package main

import (
	"fmt"
	"time"

	"github.com/eiannone/keyboard"
)

func main() {
	// Initialize keyboard
	err := keyboard.Open()
	if err != nil {
		fmt.Println("Error opening keyboard:", err)
		return
	}
	defer keyboard.Close()

	isSpeaking := false
	var stopProcessingChan chan struct{}
	var speechBuffer string

	fmt.Println("Press '1' to start speaking. Press '2' to process. Press 'q' to quit.")

	for {
		char, _, err := keyboard.GetKey()
		if err != nil {
			fmt.Println("Error reading key:", err)
			continue
		}

		// Quit the program
		if char == 'q' {
			fmt.Println("Exiting program.")
			break
		}

		// User starts speaking
		if char == '1' && !isSpeaking {
			fmt.Println("[START] User started speaking...")
			isSpeaking = true
			speechBuffer = ""

			// Close previous processing channel to interrupt any ongoing processing
			if stopProcessingChan != nil {
				close(stopProcessingChan)
			}
		}

		// User stops speaking
		if char == '2' && isSpeaking {
			fmt.Println("[STOP] User stopped speaking. Processing...")

			// Create a new channel for this processing operation
			processingChan := make(chan struct{})

			// Store the channel to be able to interrupt future operations
			stopProcessingChan = processingChan

			// Simulate processing the speech input
			go processSpeech(speechBuffer, processingChan)
			isSpeaking = false
		}

		// While speaking, accumulate text input (simulated)
		if isSpeaking {
			speechBuffer += "some_audio_chunk "
		}
	}
}

// Simulated speech processing pipeline (STT → LLM → TTS)
func processSpeech(speech string, stopChan chan struct{}) {
	fmt.Println("[STT] Converting speech to text:", speech)
	time.Sleep(1 * time.Second) // Simulate processing time

	select {
	case <-stopChan:
		fmt.Println("[INTERRUPTED] Stopping STT.")
		return
	default:
	}

	fmt.Println("[LLM] Generating response from LLM...")
	time.Sleep(1 * time.Second) // Simulate LLM processing

	select {
	case <-stopChan:
		fmt.Println("[INTERRUPTED] Stopping LLM processing.")
		return
	default:
	}

	fmt.Println("[TTS] Converting response to speech output...")
	time.Sleep(1 * time.Second) // Simulate TTS generation

	select {
	case <-stopChan:
		fmt.Println("[INTERRUPTED] Stopping TTS output.")
		return
	default:
	}

	fmt.Println("[DONE] Speech output finished!")
}
