package src;



import java.awt.Point;
import java.util.*;


public class Board{

	private List<List<Block>> board = new ArrayList<List<Block>>(); 
	private int sizex;
	private int sizey;
	
	private HashMap<Point,Block> boardP = new HashMap<Point,Block>();
	//private ArrayList<Point> solution = new ArrayList<Point>();
	
	//default constructor
	Board(){
		sizex = 0;
		sizey = 0;
	}
	Board(int sizex,int sizey){
		this.sizex = sizex;
		this.sizey = sizey;
		CreateEmptyBoard();
	}
	//intilize all the blocks in this board
	void CreateEmptyBoard(){
		//fill array with y block arrays
		for(int i = 0;i < sizex;i++){
			board.add(new ArrayList<Block>());
			//fill each array with x empty blocks
			for(int j = 0;j < sizey;j++){
				board.get(i).add(new Block(1));
			}
		}
	}
	int getBlockSeed(int x,int y){
		if(x < sizex && y < sizey){
			return board.get(x).get(y).blockGet();		
		} else {
			return 0;
		}
	}
	Block getBlock(int x,int y){
		if(x < sizex && y < sizey){
			return board.get(x).get(y);		
		} else {
			return new Block(1);
		}
	}

	void setBlock(int x,int y,int seed){
		if(x < sizex && y < sizey){
			//set block by seed
	//		Block temp = new Block(1);
			board.get(x).get(y).blockSet(seed);
			//add walls to adjacent blocks if the exist in current block
			if(x < sizex - 1 && seed % 3 == 0)board.get(x + 1).get(y).setWallW(true);		
			if(x > 0 && seed % 7 == 0)board.get(x - 1).get(y).setWallE(true);		
			if(y < sizey - 1 && seed % 5 == 0 )board.get(x).get(y + 1).setWallN(true);		
			if(y > 0 && seed % 2 == 0 )board.get(x).get(y - 1).setWallS(true);		
		}
	}
	@Override
	public String toString(){
		String out = "";
		int i,j; // Extra k
		//add first row to string
		out += ".";
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallN() ? "---" : "   ") + ".";
		out += "\n";		
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallW() ? "|" : " ") + "   ";
		out += (getBlock(i - 1,0).isWallE() ? "|" : " ");
		out += "\n.";		
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallS() ? "---" : "   ") + ".";		
		out += "\n";
		//add all the rest of the rows to string
		for(j = 1; j < sizey;j++){
			for(i = 0;i < sizex;i++)out += (getBlock(i,j).isWallW() ? "|" : " ") + "   ";
			out += (getBlock(i - 1,j).isWallE() ? "|" : " ");
			out += "\n.";		
			for(i = 0;i < sizex;i++)out += (getBlock(i,j).isWallS() ? "---" : "   ") + ".";		
			out += "\n";
		}
		
		return out;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	protected void CreateEmptyHashBoard(){
		
		for(int i = 0;i < sizex;i++){			
			for(int j = 0;j < sizey;j++){
				boardP.put(new Point(i, j), new Block(1));
			}
		}
	}
	
	protected Boolean checkIfValid() throws InvalidBoard {
		getStart();
		getEnd();
		return false;
	}
	
	/**
	 * Method to get starting block of board using HashMaps
	 * 
	 * @return Starting Block
	 * @throws InvalidBoard If there are more than 1 starting block.
	 */
	protected Block getStart() throws InvalidBoard{
		ArrayList<Block> Blocks = new ArrayList<Block>();
		for(Block block : boardP.values()) {
			if(block.getType() == Block.bType.START_BLOCK)
				Blocks.add(block);
		}
		
		if(Blocks.size() != 1) {
			throw new InvalidBoard("More than one start block on board");
		}
		else
			return Blocks.get(0);
	}
	
	/**
	 * Method to set board's start block.
	 * 
	 * @param point
	 */
	protected void setStart(Point point) {
		Block block = boardP.get(point);
		block.setType(Block.bType.START_BLOCK);
		boardP.put(point, block);
	}
	
	/**
	 * Method to set board's end block.
	 * 
	 * @param point
	 */
	protected void setEnd(Point point) {
		Block block = boardP.get(point);
		block.setType(Block.bType.END_BLOCK);
		boardP.put(point, block);
	}
	
	/**
	 * Method to get end block of board using HashMaps
	 * 
	 * @return End Block
	 * @throws InvalidBoard If there are more than 1 end block.
	 */
	protected Block getEnd() throws InvalidBoard{
		ArrayList<Block> Blocks = new ArrayList<Block>();
		for(Block block : boardP.values()) {
			if(block.getType() == Block.bType.END_BLOCK)
				Blocks.add(block);
		}
		
		if(Blocks.size() != 1) {
			throw new InvalidBoard("More than one end block on board");
		}
		else
			return Blocks.get(0);
	}
	
	/**
	 * Method returns block north of point given. Returns Null if there isn't one. Using HashMaps
	 * 
	 * @param block 
	 * @return
	 */
	protected Block getNorth(Point point) {
		if(point.getY() <= 0)
			return null;
		else {
			point.setLocation(point.getX(), point.getY() + 1);
			return boardP.get(point);
		}
	}
	
	/**
	 * Method returns block East of point given. Returns Null if there isn't one. Using HashMaps
	 * 
	 * @param block 
	 * @return
	 */
	protected Block getEast(Point point) {
		if(point.getX() >= sizex)
			return null;
		else {
			point.setLocation(point.getX(), point.getY() + 1);
			return boardP.get(point);
		}
	}
	
	/**
	 * Method returns block South of point given. Returns Null if there isn't one. Using HashMaps
	 * 
	 * @param block 
	 * @return
	 */
	protected Block getSouth(Point point) {
		if(point.getY() >= sizey)
			return null;
		else {
			point.setLocation(point.getX(), point.getY() + 1);
			return boardP.get(point);
		}
	}
	
	/**
	 * Method returns block West of point given. Returns Null if there isn't one. Using HashMaps
	 * 
	 * @param block 
	 * @return
	 */
	protected Block getWest(Point point) {
		if(point.getX() <= 0)
			return null;
		else {
			point.setLocation(point.getX(), point.getY() + 1);
			return boardP.get(point);
		}
	}
	
}
