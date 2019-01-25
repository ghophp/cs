import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ReverseLinkedList {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep) {
        while (node != null) {
            System.out.print(String.valueOf(node.data));
            node = node.next;
            if (node != null) {
                System.out.print(sep);
            }
        }
    }

    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        if (head == null) {
            return head;
        }

        DoublyLinkedListNode next = head.next;
        head.next = head.prev;
        head.prev = next;

        return head.prev == null ? head : reverse(head.prev);
    }

    public static void main(String[] args) throws IOException {
        DoublyLinkedList llist = new DoublyLinkedList();
		llist.insertNode(1);
		llist.insertNode(2);
		llist.insertNode(3);
		llist.insertNode(4);

		System.out.println("Original List:");
		printDoublyLinkedList(llist.head, " ");

		System.out.println("");
		System.out.println("Reversed List:");

        DoublyLinkedListNode reversedHead = reverse(llist.head);
        printDoublyLinkedList(reversedHead, " ");

        System.out.println("");
    }
}
