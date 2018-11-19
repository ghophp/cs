import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static final String TOO_CHAOTIC = "Too chaotic";

    // Complete the minimumBribes function below.
    // 2 1 5 3 4 / x = 4 / expected = 5 / fi = 3 / si = 2 / fv = 3 / sv = 5
    // 5 3 4 // 3 5 4 // 3 4 5
    static void minimumBribes(int[] q) {
        int swapCounter = 0;

        for (int x = q.length - 1; x > 0; x--) {
            int expectedValueAtIndex = x + 1;
            if (expectedValueAtIndex == q[x]) {
                continue;
            }

            int firstBribeIndex = x - 1;
            int secondBribeIndex = x - 2;
            int firstBribe = q[firstBribeIndex];

            if (secondBribeIndex < 0) {
                if (firstBribe == expectedValueAtIndex) {
                    swap(q, x, firstBribeIndex);
                    swapCounter++;
                } else {
                    System.out.println(TOO_CHAOTIC);
                    return;
                }
            } else {
                int secondBribe = q[secondBribeIndex];

                if (firstBribe != expectedValueAtIndex && 
                    secondBribe != expectedValueAtIndex) {
                    System.out.println(TOO_CHAOTIC);
                    return;
                }

                if (firstBribe == expectedValueAtIndex) {
                    swap(q, firstBribeIndex, x);
                    swapCounter++;
                } else {
                    swap(q, secondBribeIndex, firstBribeIndex);
                    swap(q, firstBribeIndex, x);

                    swapCounter++;
                    swapCounter++;
                }
            }
        }

        System.out.println(String.valueOf(swapCounter));
    }

    private static void swap(int[] q, int firstIndex, int secondIndex) {
        int copy = q[firstIndex];
        q[firstIndex] = q[secondIndex];
        q[secondIndex] = copy;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
