public class aMAZEingMain {

	public static void main(String[] args) {
		System.out.println("·Hello");
		for (int i = 1; i < 100; i++) {
			Block b = new Block(i);
			System.out.println("____ Seed: " + i + " ____");
			System.out.print(b.print());
			System.out.println("__________________\n");
		}
	}
}
