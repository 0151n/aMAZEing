public class Block {
	
	protected enum bType{PLAIN_BLOCK, START_BLOCK, END_BLOCK}

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
	
	public Block(int seed) {
		blockSet(seed);
	}
	
	/**
	 *   Seed Layout
	 * 
	 *       2
	 *     ·---·
	 *   7 | 1 | 3
	 *     ·---·
	 *       5
	 *     
	 * Type: Plain default
	 *       Start = 11
	 *       End = 13
	 */
		
	
	public void blockSet(int seed) {
		
		setWallN(false);
		setWallS(false);
		setWallE(false);
		setWallW(false);
		setType(bType.PLAIN_BLOCK);
		
		if(seed % 2 == 0)
			setWallN(true);
		if(seed % 3 == 0)
			setWallE(true);
		if(seed % 5 == 0)
			setWallS(true);
		if(seed % 7 == 0)
			setWallW(true);
		if(seed % 11 == 0)
			setType(bType.START_BLOCK);
		if(seed % 13 == 0)
			setType(bType.END_BLOCK);
	}
	
	public int blockGet() {
		
		int seed = 1;
		
		if(wallN)
			seed*=2;
		if(wallE)
			seed*=3;
		if(wallS)
			seed*=5;
		if(wallW)
			seed*=7;
		if(type == bType.START_BLOCK)
			seed*=11;
		if(type == bType.END_BLOCK)
			seed*=13;
		
		return seed;
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
		return "Block [ Seed=" + blockGet() + ", wallN=" + wallN + ", wallS=" + wallS + ", wallE=" + wallE + ", wallW=" + wallW + ", type=" + type
				+ "]";
	}
	
	
}
