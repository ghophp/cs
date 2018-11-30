import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                int currentHourglassSum = hourglassSum(arr, row, column);
                if (currentHourglassSum >= maxSum) {
                    maxSum = currentHourglassSum;
                }
            } 
        }

        return maxSum;
    }

    static int hourglassSum(int[][] arr, int row, int column) {
        int currentSum = 0;
        for (int cColumn = column; cColumn <= (column + 2); cColumn++) {
            currentSum += arr[row][cColumn];
            currentSum += arr[row + 2][cColumn];
        }

        currentSum += arr[row + 1][column + 1];
        return currentSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
