/**
* Given a matrix:

* [1, 2, 3]
* [4, 5, 6]
* [7, 8, 9]
*
* The algorithm should calculate from 0,0 to 2,2
* what is the minimum cost to get there
*/

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MinCostMatrix {
	private List<List<Integer>> currentMatrix;
	private Integer currentSum = 0;

	public MinCostMatrix(List<List<Integer>> currentMatrix) {
		this.currentMatrix = currentMatrix;
	}

	public Integer getMinCost() {
		this.currentSum = 0;
		return this.getMinCost(currentMatrix.size() - 1, currentMatrix.size() - 1);
	}

	private Integer getMinCost(int currentRow, int currentColumn) {
		if (currentRow == 0 && currentColumn == 0) {
			return currentSum;
		}

		Integer topCost = currentRow - 1 >= 0 ? currentMatrix.get(currentRow - 1).get(currentColumn) : Integer.MAX_VALUE;
		Integer rightCost = currentColumn - 1 >= 0 ? currentMatrix.get(currentRow).get(currentColumn - 1) : Integer.MAX_VALUE;

		if (topCost < rightCost) {
			this.currentSum += topCost;
			return this.getMinCost(currentRow - 1, currentColumn);
		} else {
			this.currentSum += rightCost;
			return this.getMinCost(currentRow, currentColumn - 1);
		}
	}

	public static void main(String[] args) {
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		matrix.add(Arrays.asList(1, 2, 3));
		matrix.add(Arrays.asList(4, 5, 6));
		matrix.add(Arrays.asList(7, 8, 9));

		MinCostMatrix minCostMatrix = new MinCostMatrix(matrix);
		System.out.println("Expected result 13 is equal to " + minCostMatrix.getMinCost());

		List<List<Integer>> matrix2 = new ArrayList<List<Integer>>();
		matrix2.add(Arrays.asList(10, 5, 2));
		matrix2.add(Arrays.asList(15, 6, 40));
		matrix2.add(Arrays.asList(20, 2, 1));

		MinCostMatrix minCostMatrix2 = new MinCostMatrix(matrix2);
		System.out.println("Expected result 23 is equal to " + minCostMatrix2.getMinCost());
	}
}