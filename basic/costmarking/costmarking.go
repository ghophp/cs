package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Maze struct {
	Points []*Point
}

type Point struct {
	x int
	y int
	v int // value of the current mark
}

type Person struct {
	current *Point
	goal    *Point
}

func PointFromInput(input string) (*Point, error) {
	if len(input) <= 0 {
		return nil, fmt.Errorf("Invalid input")
	}

	coor := strings.Split(input, ",")
	if len(coor) < 2 {
		return nil, fmt.Errorf("Invalid input")
	}

	x, err := strconv.Atoi(coor[0])
	if err != nil {
		return nil, err
	}
	y, err := strconv.Atoi(coor[1])
	if err != nil {
		return nil, err
	}

	return &Point{
		x: x,
		y: y,
	}, nil
}

func (m *Maze) Mark(k int, goal *Point) {
	// TODO: recursive movement
}

func (p *Point) Equals(to *Point) bool {
	return p.x == to.x && p.y == to.y
}

func (p *Point) String() string {
	return strconv.Itoa(p.x) + "," + strconv.Itoa(p.y)
}

func main() {
	var maze = &Maze{}
	for x := 0; x < 8; x++ {
		for y := 0; y < 8; y++ {
			maze.Points = append(maze.Points, &Point{
				x: x,
				y: y,
			})
		}
	}

	var (
		init = &Point{
			x: 0,
			y: 0,
		}
		person = &Person{
			current: init,
			goal:    init,
		}
		input = ""
	)

	for input == "" {
		reader := bufio.NewReader(os.Stdin)
		fmt.Print("Enter coordinates (current " + person.current.String() + "): ")

		input, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println(err)
			continue
		}

		goal, err := PointFromInput(strings.TrimSpace(input))
		if err != nil {
			fmt.Println(err)
			continue
		}

		person.goal = goal
		for !person.current.Equals(person.goal) {
			var k int

			person.current.x = person.goal.x
			person.current.y = person.goal.y
		}
	}
}
