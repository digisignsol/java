
public class BoardGraphics {
	int cbsize = 0;  
	public char board[][];
	public static final char ulc = '\u250c';
	public static final char urc = '\u2510';
	public static final char llc = '\u2514';
	public static final char lrc = '\u2518';
	public static final char hl = '\u2500';
	public static final char vl = '\u2502';
	public static final char hb = '\u251c';
	public static final char he = '\u2524';
	public static final String plus = "\u253c";
	public static final char vb = '\u252c';
	public static final char ve = '\u2534';
	
	BoardGraphics(int size){
		cbsize = size*2+1;
		board = new char[cbsize][cbsize];
		
		String x = "";
		for(int i = 0; i < size; i++) 
			x += plus + hl + hl + hl+hl;
		x += plus;
				
		String y = "";
		for(int i = 0; i < size; i++ ) {
			y += vl + "    ";
		}
		y += vl;
		
		for(int i=0; i < size*2; i+=2 ) {
			board[i] = x.toCharArray();
			board[i+1]= y.toCharArray();
		}
		board[cbsize-1]= x.toCharArray();
	}
	
	public void draw(){
		System.out.println();
		for(int i = 0; i < cbsize; i++) {
			System.out.print("   ");
			System.out.println(board[i]);
		}
	}
	
	public void mark(int x, int y) {
		if ((x > -1) && (x < 8) && (y > -1) && (y < 8)) 
			board[x*2+1][y*4+2] = '@';
	}
	public void mark(Bpo o, int ch) {
		int x = o.x;
		int y = o.y;
		if ((x > -1) && (x < 8) && (y > -1) && (y < 8))
			if(ch > 9) {
				board[x*2+1][y*5+2] = String.valueOf(ch).toCharArray()[0];
				board[x*2+1][y*5+3] = String.valueOf(ch).toCharArray()[1];
				if(ch == 64) 
					board[x*2+1][y*5+4] = '*';
			}
			else {
				board[x*2+1][y*5+2] = String.valueOf(ch).toCharArray()[0];
				if(ch == 1) 
					board[x*2+1][y*5+1] = '*';
			}
	}
}

