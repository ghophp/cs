/**
 * Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array so that all the Rs
 * come first, the Gs come second, and the Bs come last. You can only swap elements of the array.
 *
 * Do this in linear time and in-place.
 *
 * For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
 */

public class RgbSorting {
	private String[] currentArray;

	public RgbSorting(String[] currentArray) {
		this.currentArray = currentArray;
	}

	public void sort() {
		int middleValue = (int) 'G';
		int i = 0;
		int j = 0;
		int n = this.currentArray.length - 1;

		while (j <= n) {
			int current = (int) this.currentArray[j].charAt(0);
			if (current > middleValue) {
				this.swap(i, j);
				i++;
				j++;
			} else if (current < middleValue) {
				this.swap(j, n);
				n--;
			} else {
				j++;
			}
		}
	}

	/**
	 * Swap A to B
	 * @param a
	 * @param b
	 */
	private void swap(int a, int b) {
		String copy = this.currentArray[a];
		this.currentArray[a] = this.currentArray[b];
		this.currentArray[b] = copy;
	}

	public String toString() {
		StringBuilder currentString = new StringBuilder();
		currentString.append("[");

		for (String c : this.currentArray) {
			currentString.append("'" + c + "',");
		}

		currentString.append("]");
		return currentString.toString();
	}

	public static void main(String[] args) {
		String[] currentArray = {"G", "B", "R", "R", "B", "R", "G"};

		RgbSorting rgbSorting = new RgbSorting(currentArray);
		rgbSorting.sort();

		System.out.println("Check that " + rgbSorting.toString() + " is equal to ['R','R','R','G','G','B','B',]");
	}
}