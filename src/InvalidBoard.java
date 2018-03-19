package src;



public class InvalidBoard extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBoard(){
		
	}
	
	InvalidBoard(String str){
		super(str);
	}
}
