/**
 * Given a string, find the palindrome that can be made by inserting the fewest number of characters as possible anywhere in the word.
 * If there is more than one palindrome of minimum length that can be made, return the lexicographically earliest one (the first one alphabetically).
 *
 * For example, given the string "race", you should return "ecarace", since we can add three letters to it (which is the smallest amount to make a palindrome).
 * There are seven other palindromes that can be made from "race" by adding three letters, but "ecarace" comes first alphabetically.
 *
 * As another example, given the string "google", you should return "elgoogle".
 */

public class FewerPalindromeInsert {
	private String currentString = "";

	public void setCurrentString(String currentString) {
		this.currentString = currentString;
	}

	public int getInsertAmount() {
		return 0;
	}

	public static void main(String[] args) {
		FewerPalindromeInsert fewerPalindromeInsert = new FewerPalindromeInsert();

		fewerPalindromeInsert.setCurrentString("race");
		System.out.println("For the string 'race' the result should be 3 equals " + fewerPalindromeInsert.getInsertAmount());

		fewerPalindromeInsert.setCurrentString("google");
		System.out.println("For the string 'google' the result should be 2 equals " + fewerPalindromeInsert.getInsertAmount());

		fewerPalindromeInsert.setCurrentString("ball");
		System.out.println("For the string 'ball' the result should be 3 equals " + fewerPalindromeInsert.getInsertAmount());

		fewerPalindromeInsert.setCurrentString("tiger");
		System.out.println("For the string 'tiger' the result should be 4 equals " + fewerPalindromeInsert.getInsertAmount());

		fewerPalindromeInsert.setCurrentString("xxxx");
		System.out.println("For the string 'xxxx' the result should be 0 equals " + fewerPalindromeInsert.getInsertAmount());
	}
}