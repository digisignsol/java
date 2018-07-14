import java.util.ArrayList;

public class Bpo {
	protected int x,y;
	private boolean v;

	private ArrayList<Bpo> nextPosArr = new ArrayList<Bpo>();
		
	private  Bpo(int x, int y){
		this.x = x;
		this.y = y;
		v = false;
		if((x>-1)&&(x<8)&&(y>-1)&&(y<8) && (Marker.marked[x][y] == false))
			v = true;
	}
	
	private void FindNextPos() {

		int dltax[] = {2,2,1,1,-2,-2,-1,-1};
		int dltay[] = {1,-1,+2,-2,1,-1,2,-2};
		
		for(int i = 0; i < 8; i++){
			Bpo oBpo= new Bpo(x+dltax[i], y+dltay[i]);
			if (oBpo.v) {
				nextPosArr.add(oBpo);
			}
			else
				oBpo = null;
		}

	}
	public String toString() {
		return "{["+ x + "]" + "["+ y +"]" + ":" 
			+ nextPosArr.size() + ","+ Marker.marked[x][y]+ "}"  ;		
	}

	
	Bpo getNextPos(){
		
		FindNextPos();
		//System.out.println("> " + this);
		for(Bpo i:nextPosArr){
			i.FindNextPos();
			//System.out.println("  >>" + i);
			for(Bpo j:i.nextPosArr) {
				j.FindNextPos();
				//System.out.println("    >>>" + j);
			}			
		}
		
		if(nextPosArr.size()>0) {
			Bpo minVal = nextPosArr.get(0);
			for(Bpo tmp:nextPosArr ){
				 if(tmp.nextPosArr.size() < minVal.nextPosArr.size()  )
					minVal = tmp; 
			}
			return new Bpo(minVal.x,minVal.y);   
		};
		return null; 
	}
	

	public static void main(String[] args) {
		Bpo n;
		for(int i = 0; i < 8; i++) 
			for(int j = 0; j < 8; j++) {	
				n = new Bpo(i,j);
				n.KnightsTrail();
			}
		
		System.exit(0);
	}
	
	void KnightsTrail(){
		
		BoardGraphics B = new BoardGraphics(8);
		
		Marker.init();
		Bpo n = this; //new Bpo(0,1);
		System.out.println("   Starting at " + n);
		int Counter = 1;
		while(n != null) {
			Marker.mark(n);
			B.mark(n, Counter++);
			//System.out.println("$ Move " + (Counter) + ":" + n);
			n = n.getNextPos();
			if (Counter> 65){
				System.out.println("Infinite loop");
				break;
			}
		}
		B.draw();
		
	}
}

