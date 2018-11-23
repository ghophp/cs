import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class MyQueue<T> {
        private Stack<T> bufferItemsStack = new Stack();
        private Stack<T> secondaryItemsStack = new Stack();

        public void enqueue(T value) {
            this.bufferItemsStack.push(value);
        }

        public T dequeue() {
            this.transferItems();
            return this.secondaryItemsStack.pop();
        }

        public T peek() {
            this.transferItems();
            return this.secondaryItemsStack.peek();
        }

        private void transferItems() {
            if (this.secondaryItemsStack.isEmpty()) {
                while (!this.bufferItemsStack.isEmpty()) {
                    this.secondaryItemsStack.push(this.bufferItemsStack.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}