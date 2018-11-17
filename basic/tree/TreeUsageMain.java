class TreeUsageMain {
	public static void main(String[] args) {
		Node nodeA = new Node("a");
		Node nodeB = nodeA.insertLeft("b");
		Node nodeC = nodeA.insertRight("c");

		Node nodeD = nodeB.insertRight("d");

		Node nodeE = nodeC.insertLeft("e");
		Node nodeF = nodeC.insertRight("f");

		BinaryTree binaryTree = new BinaryTree(nodeA);

		System.out.println("--- DFS ---");

		System.out.println("--- Pre Order ---");
		binaryTree.preOrder();

		System.out.println("--- In Order ---");
		binaryTree.inOrder();

		System.out.println("--- Post Order ---");
		binaryTree.postOrder();
		System.out.println("\n\n");

		System.out.println("--- BFS ---");
		binaryTree.breadthFirstSearch();
	}
}