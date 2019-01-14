/**
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.
 *
 * For example, given the following preorder traversal:
 * [a, b, d, e, c, f, g]
 *
 * And the following inorder traversal:
 * [d, b, e, a, f, c, g]
 *
 * You should return the following tree:
 *     a
 *    / \
 *   b   c
 *  / \ / \
 * d  e f  g
 */

import java.util.Arrays;

public class TreeReconstruct {
	public Node head;

	public Node reconstruct(String[] preOrder, String[] inOrder) throws IllegalStateException {
		if (preOrder.length <= 0 && inOrder.length <= 0) {
			return null;
		}
		if (preOrder.length == 1 && inOrder.length == 1) {
			return new Node(preOrder[0]);
		}

		int rootIndex = this.search(inOrder, preOrder[0]);
		if (rootIndex < 0) {
			throw new IllegalStateException("Sets are not the same! " +
				Arrays.toString(preOrder) + " " +
				Arrays.toString(inOrder));
		}

		Node root = new Node(preOrder[0]);
		root.left = this.reconstruct(
			Arrays.copyOfRange(preOrder, 1, rootIndex + 1),
			Arrays.copyOfRange(inOrder, 0, rootIndex)
		);

		root.right = this.reconstruct(
			Arrays.copyOfRange(preOrder, rootIndex + 1, preOrder.length),
			Arrays.copyOfRange(inOrder, rootIndex + 1, inOrder.length)
		);

		return root;
	}

	private int search(String[] values, String value) {
		for (int x = 0; x < values.length; x++) {
			String c = values[x];
			if (c.equals(value)) {
				return x;
			}
		}

		return -1;
	}

	public void printPreOrder() {
		System.out.print("[");
		this.printPreOrder(this.head);
		System.out.print("]");
	}

	private void printPreOrder(Node current) {
		System.out.print(current.value);

		if (current.left != null) {
			this.printPreOrder(current.left);
		}
		if (current.right != null) {
			this.printPreOrder(current.right);
		}
	}

	public void printInOrder() {
		System.out.print("[");
		this.printInOrder(this.head);
		System.out.print("]");
	}

	private void printInOrder(Node current) {
		if (current.left != null) {
			this.printInOrder(current.left);
		}

		System.out.print(current.value);

		if (current.right != null) {
			this.printInOrder(current.right);
		}
	}

	private class Node {
		public String value;
		public Node left;
		public Node right;

		public Node(String value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		TreeReconstruct treeReconstruct = new TreeReconstruct();
		treeReconstruct.head = treeReconstruct.reconstruct(
			new String[]{"a", "b", "d", "e", "c", "f" ,"g"},
			new String[]{"d", "b", "e", "a", "f", "c", "g"}
		);

		System.out.print("Preorder print is equal to [a,b,d,e,c,f,g] = ");
		treeReconstruct.printPreOrder();

		System.out.println("");
		System.out.println("");

		System.out.print("Inorder print is equal to [d,b,e,a,f,c,g] = ");
		treeReconstruct.printInOrder();

		System.out.println("");
		System.out.println("");
	}
}