class Queue {
	public static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public Node getNext() {
			return this.next;
		}
	}

	private Node head;
	private Node tail;

	public boolean isEmpty() {
		return this.head != null;
	}

	public int peek() {
		return this.head.getValue();
	}

	public void add(int value) {
		Node newNode = new Node(value);

		if (this.tail != null) {
			this.tail.next = newNode;
		}

		this.tail = newNode;

		if (this.head == null) {
			this.head = newNode;
		}
	}

	public int remove() {
		int value = this.head.value;

		this.head = this.head.next;
		if (this.head == null) {
			this.tail = null;
		}

		return value;
	}
}
