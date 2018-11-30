import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[][] grid;
    static int nRow = 0;
    static int nCol = 0;
    static int currentMeasure = 0;

    // Complete the maxRegion function below.
    static int maxRegion() {
        int currentMaxRegion = 0;
        for (int row = 0; row < nRow; row++) {
            for (int col = 0; col < nCol; col++) {
                if (grid[row][col] == 1) {
                    currentMeasure = 1;
                    grid[row][col] = -1;

                    measureRegion(row, col);
                    if (currentMeasure > currentMaxRegion) {
                        currentMaxRegion = currentMeasure;
                    }
                }
            }
        }
        return currentMaxRegion;
    }

    static void measureRegion(int row, int col) {
        boolean hasLeft = (col - 1) >= 0;
        boolean hasRight = (col + 1) < nCol;
        boolean hasTop = (row - 1) >= 0;
        boolean hasUnder = (row + 1) < nRow;

        if (hasLeft && grid[row][col-1] == 1) {
            grid[row][col-1] = -1;
            currentMeasure++;
            measureRegion(row, col-1);
        }
        if (hasTop && grid[row-1][col] == 1) {
            grid[row-1][col] = -1;
            currentMeasure++;
            measureRegion(row-1, col);
        }
        if (hasUnder && grid[row+1][col] == 1) {
            grid[row+1][col] = -1;
            currentMeasure++;
            measureRegion(row+1, col);
        }
        if (hasRight && grid[row][col+1] == 1) {
            grid[row][col+1] = -1;
            currentMeasure++;
            measureRegion(row, col+1);
        }
        if (hasTop && hasLeft && grid[row-1][col-1] == 1) {
            grid[row-1][col-1] = -1;
            currentMeasure++;
            measureRegion(row-1, col-1);
        }
        if (hasTop && hasRight && grid[row-1][col+1] == 1) {
            grid[row-1][col+1] = -1;
            currentMeasure++;
            measureRegion(row-1, col+1);
        }
        if (hasUnder && hasLeft && grid[row+1][col-1] == 1) {
            grid[row+1][col-1] = -1;
            currentMeasure++;
            measureRegion(row+1, col-1);
        }
        if (hasUnder && hasRight && grid[row+1][col+1] == 1) {
            grid[row+1][col+1] = -1;
            currentMeasure++;
            measureRegion(row+1, col+1);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        nRow = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        nCol = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        grid = new int[nRow][nCol];

        for (int i = 0; i < nRow; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < nCol; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion();

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
