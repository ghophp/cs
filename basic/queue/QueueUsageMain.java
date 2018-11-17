class QueueUsageMain {
	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);

		System.out.println("--- Remove ---");
		System.out.println(String.valueOf(queue.remove()));
		System.out.println(String.valueOf(queue.remove()));
		System.out.println(String.valueOf(queue.remove()));
		System.out.println(String.valueOf(queue.remove()));
	}
}