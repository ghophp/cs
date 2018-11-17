class StackUsageMain {
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);

		System.out.println("--- Remove ---");
		System.out.println(String.valueOf(stack.remove()));
		System.out.println(String.valueOf(stack.remove()));
		System.out.println(String.valueOf(stack.remove()));
		System.out.println(String.valueOf(stack.remove()));
	}
}