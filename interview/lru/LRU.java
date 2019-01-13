/**
 * Implement an LRU (Least Recently Used) cache. It should be able to be initialized with a cache size n, and
 * contain the following methods:
 *
 * set(key, value): sets key to value. If there are already n items in the cache and we are adding a new item,
 * then it should also remove the least recently used item.
 *
 * get(key): gets the value at key. If no such key exists, return null.
 *
 * Each operation should run in O(1) time.
 */

import java.util.HashMap;

public class LRU {
	private LRUNode head, bottom;
	private HashMap<String, LRUNode> hashMap;
	private int maxSize;

	public LRU(int maxSize) {
		this.maxSize = maxSize;
		this.hashMap = new HashMap<>();
	}

	public void set(String key, Integer value) {
		LRUNode currentNode = null;

		if (this.hashMap.containsKey(key)) {
			currentNode = this.hashMap.get(key);
			currentNode.value = value;
			this.remove(currentNode);
		} else {
			currentNode = new LRUNode(key, value);
			if (this.hashMap.size() >= this.maxSize) {
				this.hashMap.remove(bottom.key);
				this.remove(bottom);
			}
		}

		this.add(currentNode);
		this.hashMap.put(key, currentNode);
	}

	public Integer get(String key) {
		if (this.hashMap.containsKey(key)) {
			LRUNode currentNode = this.hashMap.get(key);
			this.remove(currentNode);
			this.add(currentNode);

			return currentNode.value;
		}

		return null;
	}

	private void add(LRUNode newNode) {
		newNode.right = this.head;
		newNode.left = null;

		if (this.head != null) {
			this.head.left = newNode;
		}

		this.head = newNode;

		if (this.bottom == null) {
			this.bottom = this.head;
		}
	}

	private void remove(LRUNode node) {
		if (node.left != null) {
			node.left.right = node.right;
		} else {
			this.head = node.right;
		}

		if (node.right != null) {
			node.right.left = node.left;
		} else {
			this.bottom = node.left;
		}
	}

	private class LRUNode {
		public String key;
		public Integer value;
		public LRUNode left;
		public LRUNode right;

		public LRUNode(String key, Integer value) {
			this.key = key;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LRU lru = new LRU(2);
		lru.set("test1", 1);
		lru.set("test2", 2);

		System.out.println("get test2 key should return 2 = " + lru.get("test2"));

		System.out.println("set test3 = 3");
		lru.set("test3", 3);

		System.out.println("get test1 now should return null = " + lru.get("test1"));
		System.out.println("get test2 key should return 2 = " + lru.get("test2"));
		System.out.println("get test3 now should return 3 = " + lru.get("test3"));
	}
}