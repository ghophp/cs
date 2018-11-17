class Stack {
	public static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	private Node top;

	public boolean isEmpty() {
		return this.top == null;
	}

	public void add(int value) {
		Node newNode = new Node(value);
		newNode.next = top;

		top = newNode;
	}

	public int remove() {
		int value = this.top.getValue();
		this.top = this.top.next;

		return value;
	}
}