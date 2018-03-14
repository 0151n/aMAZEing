public class aMAZEingMain {

	public static void main(String[] args) {
		System.out.println("·Hello");
		for (int i = 1; i < 100; i++) {
			Block b = new Block(i);
			System.out.println("____ Seed: " + i + " ____");
			System.out.print(b.print());
			System.out.println("__________________\n");
		}
		
		Board test = new Board(4,5);
		test.setBlock(0,0,210);
		test.setBlock(2,3,98);
		test.setBlock(3,1,96);
		test.setBlock(3,4,210);
		System.out.print(test);
	}
}
