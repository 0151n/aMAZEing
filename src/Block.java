package src;
import java.util.*;


public class Block {
	
	public enum bType{PLAIN_BLOCK, START_BLOCK, END_BLOCK}

	private boolean wallN;
	private boolean wallS;
	private boolean wallE;
	private boolean wallW;
	private bType type;
	
	public Block(boolean wallN, boolean wallS, boolean wallE, boolean wallW, bType type) {
		this.wallN = wallN;
		this.wallS = wallS;
		this.wallE = wallE;
		this.wallW = wallW;
		this.type = type;
	}
	
	// Returns descriptive string in format NESWT where T = Type (s = start, e = end, p = plain).
	// Might be used to remove need for getter methods.
	public String getBlock() {
		String str = "";
		//Walls
		str = ( wallN ?"|":" ") + ( wallE ?"|":" ") + ( wallS ?"|":" ") + ( wallW ?"|":" ");
		//Type
		if(type == bType.START_BLOCK){
			str += "s";
		} else if(type == bType.END_BLOCK){
			str += "e";
		} else {
			str += "p";
		}
		return str;
	}
	
	public boolean isWallN() {
		return wallN;
	}
	public void setWallN(boolean wallN) {
		this.wallN = wallN;
	}
	public boolean isWallS() {
		return wallS;
	}
	public void setWallS(boolean wallS) {
		this.wallS = wallS;
	}
	public boolean isWallE() {
		return wallE;
	}
	public void setWallE(boolean wallE) {
		this.wallE = wallE;
	}
	public boolean isWallW() {
		return wallW;
	}
	public void setWallW(boolean wallW) {
		this.wallW = wallW;
	}
	public bType getType() {
		return type;
	}
	public void setType(bType type) {
		this.type = type;
	}

	public String print() {
		String T = " ";
		if(type == bType.START_BLOCK)
			T = "S";
		if(type == bType.END_BLOCK)
			T = "E";
		
		return "·" + ( wallN ?"---":"   ") + "·\n" 
				+ ( wallW ?"|":" ") + " " + T + " " + ( wallE ?"|":" ") + "\n" 
				+ "·" + ( wallS ?"---":"   ") + "·\n";
	}
	
	@Override
	public String toString() {
		return "Block [ wallN=" + wallN + ", wallS=" + wallS + ", wallE=" + wallE + ", wallW=" + wallW + ", type=" + type + "]";
	}
	
	public void randomize(){
		Random rand = new Random();
		setWallN((rand.nextInt(3) == 0 ? true : false));
		setWallE((rand.nextInt(3) == 0 ? true : false));
		setWallS((rand.nextInt(3) == 0 ? true : false));
		setWallW((rand.nextInt(3) == 0 ? true : false));
	}	
}
