package src;



public class aMAZEing {

	public static void main(String[] args) {
		System.out.println("Hello There");
		Board board = new Board(4,4);
		board.CreateRandomBoard();
		System.out.print(board);
		//board.test();
	}
}
