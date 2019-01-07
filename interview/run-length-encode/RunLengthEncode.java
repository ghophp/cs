/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated successive characters
 * as a single count and character. For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 *
 * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists solely of alphabetic
 * characters. You can assume the string to be decoded is valid.
 */

import java.util.ArrayList;

public class RunLengthEncode {
	public static String encode(String currentUnencoded) {
		StringBuilder stringBuilder = new StringBuilder();

		int x = 0;
		int currentCounter = 0;

		while (x < currentUnencoded.length()) {
			String current = String.valueOf(currentUnencoded.charAt(x));
			stringBuilder.append(current);

			currentCounter = 0;
			for (int i = x + 1; i < currentUnencoded.length(); i++) {
				String next = String.valueOf(currentUnencoded.charAt(i));
				if (current.equals(next)) {
					currentCounter++;
				} else {
					if (currentCounter > 0) {
						stringBuilder.append(String.valueOf(currentCounter));
						x = x + currentCounter;
					}
					break;
				}
			}

			x++;

			if (currentCounter >= currentUnencoded.length() - x) {
				break;
			}
		}

		if (currentCounter > 0) {
			stringBuilder.append(String.valueOf(currentCounter));
		}

		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		System.out.println(RunLengthEncode.encode("AAAABBBCCDAA"));
		System.out.println(RunLengthEncode.encode("AABCC"));
		System.out.println(RunLengthEncode.encode("ABC"));
		System.out.println(RunLengthEncode.encode("AAAA"));
	}
}