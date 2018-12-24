import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int currentMaxSum;

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int x = 0; x < arr.length - 2; x++) {
            currentMaxSum = arr[x];
            maxSubsetSum(arr, x, x + 2);
            if (currentMaxSum > maxSum) {
                maxSum = currentMaxSum;
            }
        }
        return maxSum;
    }

    static int maxSubsetSum(int[] arr, int base, int x) {
        if (x >= arr.length) {
            return 0;
        }
        
        int currentSum = arr[base];
        for (int i = x; i < arr.length; i=i+2) {
            currentSum += arr[i];
        }

        if (currentSum > currentMaxSum) {
            currentMaxSum = currentSum;
        }

        return maxSubsetSum(arr, base, x + 1);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
