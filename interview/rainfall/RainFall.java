/**
* Given a array that describe the height at a current position.
* When there is a valley formed the rain will accumullate.
*
* eg. [3, 1, 3]
* -|_|- 1st step 3 / 2nd step 1 / 3rd step 3
*
* eg. [5, 1, 1, 1, 1, 2, 2, 2, 2]
* accumulated rainfall = 1
*
* eg. [2, 1, 5, 1, 2]
*
* Going with two pointers one from left to right
* one from right to left. In this case
*
* In this scenario the answer would be 2 since the depth of the valley would
* be 1 - 3 the highest accumlatio on the valley.
*/

class RainFall {
	private List<Integer> stepHeights;

	public RainFall(List<Integer> stepHeights) {
		this.stepHeights = stepHeights;
	}

	public int getRainFallAcummulation() {
		int accumulatedRain = 0;
		int valleyBegin = -1;

		List<Integer> currentValleyItems = new ArrayList();

		for (int x = 0; x < this.stepHeights.size() - 1; x++) {
			int currentHeight = this.stepHeights.get(x);
			int nextHeight = this.stepHeights.get(x + 1);

			if (currentHeight > nextHeight && valleyBegin <= 0) {
				valleyBegin = currentHeight;
			}

			if (currentHeight < nextHeight && nextHeight > valleyBegin) {
				valleyBegin = -1;

			}
		}

		return accumulatedRain;
	}

	private int getRainFallAcummulation(List<Integer> steps, int max) {
		int sum = 0;
		for (int x = 0; x < steps.size(); x++) {

		}
		return sum;
	}
}