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
* be 1 - 3 the highest accumlation on the valley.
*/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class RainFall {
	private List<Integer> path;
	private Queue<Integer> queue;

	public RainFall(List<Integer> path) {
		this.path = path;
		this.queue = new LinkedList<Integer>();
	}

	public void setPath(List<Integer> path) {
		this.path = path;
	}

	public Integer getRainFall() {
		this.queue.clear();

		if (this.path.size() <= 2) {
			return 0;
		}

		Integer currentMax = this.path.get(0);
		Integer currentStep = 1;
		Integer totalAccumulation = 0;

		while (currentStep < this.path.size()) {
			Integer currentHeight = this.path.get(currentStep);

			if (currentHeight < currentMax) {
				this.queue.add(currentHeight);
			} else {
				totalAccumulation += this.sumCurrentValley(currentHeight);
				currentMax = currentHeight;
			}

			currentStep++;
		}

		if (!this.queue.isEmpty()) {
			totalAccumulation += this.sumCurrentValley(this.path.get(this.path.size() - 1));
		}

		return totalAccumulation;
	}

	private Integer sumCurrentValley(Integer maxValleyHeight) {
		Integer accumulation = 0;
		while (!this.queue.isEmpty()) {
			accumulation += maxValleyHeight - this.queue.poll();
		}
		return accumulation;
	}

	public static void main(String[] args) {
		List<Integer> path = Arrays.asList(3, 2, 1, 1, 3); // 5
		List<Integer> path2 = Arrays.asList(1, 0, 1, 2, 3, 4); // 1
		List<Integer> path3 = Arrays.asList(5, 1, 2, 3); // 9
		List<Integer> path4 = Arrays.asList(5, 1, 2, 3, 4, 5); // 10

		RainFall rainFall = new RainFall(path);
		System.out.println("Expected result 5 is equal to " + rainFall.getRainFall());

		rainFall.setPath(path2);
		System.out.println("Expected result 1 is equal to " + rainFall.getRainFall());

		rainFall.setPath(path3);
		System.out.println("Expected result 3 is equal to " + rainFall.getRainFall());

		rainFall.setPath(path4);
		System.out.println("Expected result 10 is equal to " + rainFall.getRainFall());
	}
}