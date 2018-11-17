class Node {
	private String value;
	private Node left;
	private Node right;

	public Node(String value) {
		this.value = value;
	}

	public Node insertLeft(String value) {
		Node newNode = new Node(value);
		if (this.left != null) {
			newNode.setLeft(this.left);
		}
		this.left = newNode;
		return newNode;
	}

	public Node insertRight(String value) {
		Node newNode = new Node(value);
		if (this.right != null) {
			newNode.setRight(this.right);
		}
		this.right = newNode;
		return newNode;
	}

	public String getValue() {
		return this.value;
	}

	public Node getRight() {
		return this.right;
	}

	public boolean hasRight() {
		return this.right != null;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return this.left;
	}

	public boolean hasLeft() {
		return this.left != null;
	}

	public void setLeft(Node left) {
		this.left = left;
	}
}