// Const-marking algorithm is focused on find the smallest route, in this case we are implementing
// it to work on a 10x10 map. The movement can be to all directions (diagonal included).
//
// TODO: Improve the next square to recursive mark
// TODO: Create the ASC representation of the algorithm
// TODO: Compare the resource usage of reseting the map vs copying it
package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
	"time"
)

const (
	RightBottomDiagonal = "RightBottomDiagonal"
	RightTopDiagonal    = "RightTopDiagonal"
	LeftBottomDiagonal  = "LeftBottomDiagonal"
	LeftTopDiagonal     = "LeftTopDiagonal"
	Bottom              = "Bottom"
	Top                 = "Top"
	Right               = "Right"
	Left                = "Left"
)

type Maze struct {
	Points []*Point
}

type Point struct {
	x int
	y int
	k int // value of the current mark
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

func GetDirectionFromCurrentToGoal(current *Point, goal *Point) string {
	fmt.Println(fmt.Sprintf("%v %v", current, goal))
	if current.x > goal.x && current.y > goal.y {
		return RightBottomDiagonal
	} else if current.x > goal.x && current.y < goal.y {
		return RightTopDiagonal
	} else if current.x < goal.x && current.y > goal.y {
		return LeftBottomDiagonal
	} else if current.x < goal.x && current.y < goal.y {
		return LeftTopDiagonal
	} else if current.x == goal.x && current.y > goal.y {
		return Bottom
	} else if current.x == goal.x && current.y < goal.y {
		return Top
	} else if current.x > goal.x && current.y == goal.y {
		return Right
	} else if current.x < goal.x && current.y == goal.y {
		return Left
	}
	return ""
}

func FindNextFromAround(around []*Point, current *Point, direction string) *Point {
	if len(around) <= 0 {
		return nil
	}

	for _, p := range around {
		if (direction == RightBottomDiagonal && p.x-current.x == 1 && p.y-current.y == 1) ||
			(direction == RightTopDiagonal && p.x-current.x == 1 && p.y-current.y == -1) ||
			(direction == LeftBottomDiagonal && p.x-current.x == -1 && p.y-current.y == 1) ||
			(direction == LeftTopDiagonal && p.x-current.x == -1 && p.y-current.y == -1) ||
			(direction == Bottom && p.x-current.x == 0 && p.y-current.y == 1) ||
			(direction == Top && p.x-current.x == 0 && p.y-current.y == -1) ||
			(direction == Right && p.x-current.x == 1 && p.y-current.y == 0) ||
			(direction == Left && p.x-current.x == -1 && p.y-current.y == 0) {
			return p
		}
	}

	return nil
}

func (m *Maze) Mark(k int, goal *Point, current *Point, from *Point) {
	time.Sleep(200 * time.Millisecond)
	fmt.Println(fmt.Sprintf("Marking the point %d %d with k %d", goal.x, goal.y, k))

	if !current.Equals(goal) {
		c := m.FindPoint(goal)
		if c == nil {
			return
		}

		c.k = k
		k = k + 1

		if from == nil {
			around := m.FindUnmarkedPointsAround(c)
			if len(around) <= 0 {
				return
			}

			for _, p := range around {
				p.k = k
				m.Mark(k, p, current, c)
			}

			direction := GetDirectionFromCurrentToGoal(current, goal)
			fmt.Println("Current direction " + direction)

			if len(direction) > 0 {
				next := FindNextFromAround(around, c, direction)
				if next != nil {
					m.Mark(k, next, current, nil)
				}
			}
		}
	}
}

func (m *Maze) FindPoint(p *Point) *Point {
	for _, c := range m.Points {
		if c.Equals(p) {
			return c
		}
	}
	return nil
}

func (m *Maze) FindUnmarkedPointsAround(p *Point) []*Point {
	var around []*Point = make([]*Point, 0, 8)
	for _, c := range m.Points {
		if c.IsNear(p) && c.k < 0 {
			around = append(around, c)
		}
	}
	return around
}

func (m *Maze) FindSmallestKAround(person *Person, k int) *Point {
	for _, c := range m.Points {
		if c.IsNear(person.current) && c.k >= 0 && c.k < k {
			return c
		}
	}
	return nil
}

func (m *Maze) CleanMarkers() {
	for i, _ := range m.Points {
		m.Points[i].k = -1
	}
}

func (p *Point) IsNear(to *Point) bool {
	return math.Abs(float64(p.x-to.x)) <= 1 && math.Abs(float64(p.y-to.y)) <= 1
}

func (p *Point) Equals(to *Point) bool {
	return p.x == to.x && p.y == to.y
}

func (p *Point) String() string {
	return strconv.Itoa(p.x) + "," + strconv.Itoa(p.y)
}

func main() {
	var maze = &Maze{}
	for x := 0; x < 10; x++ {
		for y := 0; y < 10; y++ {
			maze.Points = append(maze.Points, &Point{
				x: x,
				y: y,
				k: -1,
			})
		}
	}

	var (
		k    int
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
		maze.Mark(
			k,
			goal,
			person.current,
			nil)

		for !person.current.Equals(person.goal) {
			c := maze.FindPoint(person.current)
			if c == nil {
				fmt.Println("Something went really wrong with the math")
				continue
			}

			move := maze.FindSmallestKAround(person, c.k)
			if move != nil {
				fmt.Println(fmt.Sprintf("Moving to %d %d with k %d", move.x, move.y, move.k))

				person.current.x = move.x
				person.current.y = move.y
			}
		}

		maze.CleanMarkers()
	}
}
