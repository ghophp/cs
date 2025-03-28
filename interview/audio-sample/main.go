package main

import (
	"fmt"
	"io"
	"os"
	"sync"
	"time"

	"github.com/eiannone/keyboard"
)

func main() {
	var inChannel = make(chan byte)
	var outChannel = make(chan byte)

	// Control channels to restart playback
	restartInChan := make(chan bool)
	restartOutChan := make(chan bool)

	// Start playback goroutines
	go handleWavFile("sample.wav", inChannel, restartInChan)
	go handleWavFile("sample.wav", outChannel, restartOutChan)

	// Control channel to manage the active channel
	controlChan := make(chan string)

	// Channel to signal when to quit the program
	quitChan := make(chan bool)

	// Process user input in a separate goroutine
	go func() {
		// Initialize keyboard
		err := keyboard.Open()
		if err != nil {
			fmt.Println("Error opening keyboard:", err)
			return
		}
		defer keyboard.Close()

		fmt.Println("Press 1 to start inChannel, 2 to start outChannel, q to quit")

		for {
			char, key, err := keyboard.GetKey()
			if err != nil {
				fmt.Println("Error reading keyboard:", err)
				continue
			}

			if key == keyboard.KeyCtrlC {
				quitChan <- true
				return
			}

			switch char {
			case '1':
				controlChan <- "in"
				restartInChan <- true
				fmt.Println("Switched to inChannel and restarted playback")
			case '2':
				controlChan <- "out"
				restartOutChan <- true
				fmt.Println("Switched to outChannel and restarted playback")
			case 'q':
				quitChan <- true
				return
			}
		}
	}()

	// Process audio channels
	activeChannel := "none"

	for {
		select {
		case command := <-controlChan:
			activeChannel = command
		case b := <-inChannel:
			if activeChannel == "in" {
				fmt.Printf("Incoming audio sample: %d\n", b)
			}
		case b := <-outChannel:
			if activeChannel == "out" {
				fmt.Printf("Outgoing audio sample: %d\n", b)
			}
		case <-quitChan:
			fmt.Println("Exiting program")
			return
		case <-time.After(time.Second * 10):
			if activeChannel != "none" {
				fmt.Println("No audio data for 10 seconds")
			}
		}
	}
}

func handleWavFile(fileName string, dataChan chan byte, restartChan chan bool) {
	var mutex sync.Mutex
	var shouldRestart bool = false
	var isPlaying bool = false

	// Start the playback handler goroutine
	go func() {
		for {
			// Check if we should restart
			mutex.Lock()
			if shouldRestart && !isPlaying {
				shouldRestart = false
				isPlaying = true
				fmt.Printf("Starting playback of %s now\n", fileName)
				mutex.Unlock()

				// Play the file
				readWavFile(fileName, dataChan)

				// Mark playback as finished
				mutex.Lock()
				isPlaying = false
				mutex.Unlock()

				fmt.Printf("File %s finished, sending zeros\n", fileName)
			} else {
				mutex.Unlock()
			}

			// Send zeros when not playing
			dataChan <- 0
			time.Sleep(10 * time.Millisecond)
		}
	}()

	// Don't start the file automatically, just send zeros initially
	fmt.Printf("Channel for %s ready, waiting for key press\n", fileName)

	// Listen for restart signals
	for {
		<-restartChan

		mutex.Lock()
		currentlyPlaying := isPlaying
		if currentlyPlaying {
			fmt.Printf("Already playing %s, ignoring restart request\n", fileName)
		} else {
			shouldRestart = true
			fmt.Printf("Queueing playback of %s\n", fileName)
		}
		mutex.Unlock()
	}
}

func readWavFile(fileName string, c chan byte) {
	// Open the WAV file for reading
	file, err := os.Open(fileName)
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	// Create a buffer to read the file
	buffer := make([]byte, 1024)

	// Read the file in chunks and send each byte to the channel
	for {
		n, err := file.Read(buffer)
		if err != nil {
			if err != io.EOF {
				fmt.Println("Error reading file:", err)
			}
			return // Just return, don't close the channel
		}

		for i := 0; i < n; i++ {
			c <- buffer[i]
		}
	}
}
