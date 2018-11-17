import java.util.LinkedList;

class BinaryTree {
	private Node root;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public void breadthFirstSearch() {
		LinkedList<Node> bfsQueue = new LinkedList();
		bfsQueue.add(this.root);

		while (!bfsQueue.isEmpty()) {
			Node current = bfsQueue.poll();
			System.out.println(current.getValue());

			if (current.hasLeft()) {
				bfsQueue.add(current.getLeft());
			}
			if (current.hasRight()) {
				bfsQueue.add(current.getRight());
			}
		}
	}

	public void preOrder() {
		this.preOrder(this.root);
	}

	public void inOrder() {
		this.inOrder(this.root);
	}

	public void postOrder() {
		this.postOrder(this.root);
	}

	private void preOrder(Node current) {
		System.out.println(current.getValue());

		if (current.hasLeft()) {
			this.preOrder(current.getLeft());
		}

		if (current.hasRight()) {
			this.preOrder(current.getRight());
		}
	}

	private void inOrder(Node current) {
		if (current.hasLeft()) {
			this.inOrder(current.getLeft());
		}

		System.out.println(current.getValue());

		if (current.hasRight()) {
			this.inOrder(current.getRight());
		}
	}

	private void postOrder(Node current) {
		if (current.hasLeft()) {
			this.postOrder(current.getLeft());
		}

		if (current.hasRight()) {
			this.postOrder(current.getRight());
		}

		System.out.println(current.getValue());
	}
}
