import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static Node lca(Node root, int v1, int v2) {
      	List<Node> pathOne = createPathIndex(root, v1);
        List<Node> pathTwo = createPathIndex(root, v2);

        return getMinimumCommonNode(pathOne, pathTwo);
    }

    private static List<Node> createPathIndex(Node root, int value) {
        ArrayList<Node> nodePath = new ArrayList();
        
        Node currentNode = root;
        nodePath.add(currentNode);

        while (currentNode.data != value) {
            if (currentNode.data > value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            if (currentNode != null) {
                nodePath.add(currentNode);
            }
        }

        return nodePath;
    }

    private static Node getMinimumCommonNode(
        List<Node> firstSet, 
        List<Node> secondSet
    ) {
        Node min = null;
        for (Node n : firstSet) {
            for (Node m : secondSet) {
                if (n == m) {
                    min = n;
                }
            }
        }
        return min;
    }

	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
      	int v1 = scan.nextInt();
      	int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }	
}