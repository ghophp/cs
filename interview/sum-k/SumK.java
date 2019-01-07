/**
 * Given a list of integers S and a target number k, write a function that returns a subset of S that adds up to k.
 * If such a subset cannot be made, then return null.
 *
 * Integers can appear more than once in the list. You may assume all numbers in the list are positive.
 *
 * For example, given S = [12, 1, 61, 5, 9, 2] and k = 24, return [12, 9, 2, 1] since it sums up to 24.
 */

public class SumK {
	private int[] currentSet;
	private int currentK;

	public void setCurrentSet(int[] currentSet) {
		this.currentSet = currentSet;
	}

	public void setCurrentK(int[] currentK) {
		this.currentK = currentK;
	}

	public int[] getSumSubset() {
		for (int x = 0; x < this.currentSet.length; x++) {
			List<Integer> currentSubset = new ArrayList<>();

			int currentSum
			for (int i = x; i < this.currentSet.length; i++) {
				if (x == i) {
					continue;
				}


			}
		}

		return null;
	}

	public static void main(String[] args) {
		SumK sumK = new SumK();

		sumK.setCurrentSet(new int[]{12, 1, 61, 5, 9, 2});
		sumK.setCurrentK(24);

		System.out.println(sumK.getSumSubset());

		sumK.setCurrentSet(new int[]{1, 2, 3});
		sumK.setCurrentK(2);

		System.out.println(sumK.getSumSubset());
	}
}