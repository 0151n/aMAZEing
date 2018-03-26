package src;



import java.awt.Point;
import java.util.*;


public class Board{

	private List<List<Block>> board = new ArrayList<List<Block>>(); 
	private int sizex;
	private int sizey;
	private Point start;
	private Point end;


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
		if(board.size() == sizex && board.get(0).size() == sizey){
			clearBoard();
		}	
		//fill array with y block arrays
		for(int i = 0;i < sizex;i++){
			board.add(new ArrayList<Block>());
			//fill each array with x empty blocks
			for(int j = 0;j < sizey;j++){
				board.get(i).add(new Block(1));
			}
		}
	}
	//reset the board to an empty one
	void clearBoard(){
		for(int i = 0;i < sizex;i++){
			for(int j = 0;j < sizey;j++){
				setBlock(i,j,0);
			}
		}
	}
	//create a random valid board with a start and an end
	void CreateRandomBoard(){
		//reset board to make sure its empty
		CreateEmptyBoard();
		//set up random generator
		Random rand = new Random();	
		//length of path between start and end
		int pathLength = rand.nextInt(sizex * 2)  + sizey;
		//create new array to hold path from start to end
		int[][] path = new int[pathLength][3];
		//set start position
		// second dimension 0 is x, 1 is y
		path[0][0] = rand.nextInt(sizex); 
		path[0][1] = rand.nextInt(sizey);
	       	//set start block
		System.out.println(path[0][0] + ":" + path[0][1]);

		//generate random path from start to end
		int pos = 1; //counter for path array
		int direction; //random direction for next path element
		int prevDirection = -1; //inverse of diection travelled by last path element
		
		while(pos < pathLength){
			direction = rand.nextInt(4);
			//north
			if(direction == 0 && direction != prevDirection && path[pos - 1][1] > 0){
				path[pos][0] = path[pos - 1][0];
				path[pos][1] = path[pos - 1][1] - 1;
				path[pos][2] = 0;
				pos++;
				prevDirection = 3;
			//east
			} else if(direction == 1  && direction != prevDirection && path[pos - 1][0] < sizex - 1){
				path[pos][0] = path[pos - 1][0] + 1;
				path[pos][1] = path[pos - 1][1];
				path[pos][2] = 1;
				pos++;
				prevDirection = 2;
			//west
			} else if(direction == 2  && direction != prevDirection && path[pos - 1][0] > 0){
				path[pos][0] = path[pos - 1][0] - 1;
				path[pos][1] = path[pos - 1][1];
				path[pos][2] = 2;
				pos++;
				prevDirection = 1;
			//south
			} else if(direction == 3  && direction != prevDirection && path[pos - 1][1] < sizey - 1){
				path[pos][0] = path[pos - 1][0];
				path[pos][1] = path[pos - 1][1] + 1;
				path[pos][2] = 3;
				pos++;
				prevDirection = 0;
			}
		}
		for(int i = 0;i < sizex;i++){
			for(int j = 0;j < sizey;j++){
				setBlock(i,j,(((rand.nextInt(210) + 1))));
				setBlockType(i,j,Block.bType.PLAIN_BLOCK);
			}
		}
		
		setBlockType(path[pathLength - 1][0],path[pathLength - 1][1],Block.bType.END_BLOCK);	
		setBlockType(path[0][0],path[0][1],Block.bType.START_BLOCK);
		System.out.println("/" + getBlock(path[0][0],path[0][1]).isStart() + "/");
		
		//draw path through blocks in order to ensure board is valid (e.g. at least one path from start node to finish node
		for(int i = 0;i < pathLength;i++){
			switch(path[i][2]){
				//north 
				case 0:
					//remove south wall
					setBlock(path[i][0],path[i][1],getBlockSeed(path[i][0],path[i][1]) / 5);
				break;

				//east
				case 1:
					//remove west wall
					setBlock(path[i][0],path[i][1],getBlockSeed(path[i][0],path[i][1]) / 7);

				break;

				//west
				case 2:
					//remove east wall
					setBlock(path[i][0],path[i][1],getBlockSeed(path[i][0],path[i][1]) / 3);
				break;

				//south
				case 3:
					//remove north wall
					setBlock(path[i][0],path[i][1],getBlockSeed(path[i][0],path[i][1]) / 2);
				break;
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
	void setBlockType(int x,int y,Block.bType type){
		board.get(x).get(y).setType(type);
	}
	void setBlock(int x, int y, Block block){
		board.get(x).get(y) == block;
	}
	@Override
	public String toString(){
		String out = "";
		int i,j; // Extra k
		//add first row to string
		out += ".";
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallN() ? "---" : "   ") + ".";
		out += "\n";		
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallW() ? "|" : " ") +  "   ";
		out += (getBlock(i - 1,0).isWallE() ? "|" : " ");
		out += "\n.";		
		for(i = 0;i < sizex;i++)out += (getBlock(i,0).isWallS() ? "---" : "   ") + ".";		
		out += "\n";
		//add all the rest of the rows to string
		for(j = 1; j < sizey;j++){
			for(i = 0;i < sizex;i++)out += (getBlock(i,j).isWallW() ? "|" : " ") + " " + (getBlock(i,j).isStart() ? "X" : " ") + " "; 
			out += (getBlock(i - 1,j).isWallE() ? "|" : " ");
			out += "\n.";		
			for(i = 0;i < sizex;i++)out += (getBlock(i,j).isWallS() ? "---" : "   ") + ".";		
			out += "\n";
		}
		
		return out;
	}
	void test(){
		for(int j = 0;j < sizey;j++){
			for(int i = 0;i < sizex;i++){
				System.out.print(getBlockSeed(i,j) + " ");
			}
			System.out.println("");
		}
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
