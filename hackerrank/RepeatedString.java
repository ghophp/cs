import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    private static String LETTER_A = "a";

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        int originalCount = getCount(s, s.length() <= n ? s.length() : n);
        if (originalCount <= 0 || s.length() >= n) {
            return originalCount;
        } else if (s.length() <= 1) {
            return originalCount * n;
        }

        long repeatN = n / s.length();
        long roundCount = repeatN * originalCount;
        
        if (n % s.length() == 0) {
            return roundCount;
        } else {
            return roundCount + getCount(s, (n - (repeatN * s.length())));
        }
    }

    private static int getCount(String s, long n) {
        int occourrencesCount = 0;
        for (int x = 0; x < n; x++) {
            String current = String.valueOf(s.charAt(x));
            if (current.equals(LETTER_A)) {
                occourrencesCount++;
            }
        }
        return occourrencesCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
