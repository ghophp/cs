package main

import (
    "fmt"
    "math"
)

func main() {
    var (
        n int
        queue = make([]int, 0)
    )

    fmt.Scan(&n)

    for x := 0; x < n; x++ {
        var operation string
        fmt.Scan(&operation)

        if (operation == "1") {
            var value int
            fmt.Scan(&value)

            queue = append(queue, value)
        } else if (operation == "2") {
            queue = queue[:len(queue)-1]
        } else if (operation == "3") {
            var max = math.MinInt32
            for _, current := range queue {
                if (current > max) {
                    max = current
                }
            }

            fmt.Printf("%d\n", max)
        }
    }
}
