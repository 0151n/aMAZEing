import java.util.*;

public class Board{

	private List<List<Block>> board = new ArrayList<List<Block>>(); 
	private int sizex;
	private int sizey;

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
			Block temp = new Block(1);
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
		int i,j,k;
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
}
