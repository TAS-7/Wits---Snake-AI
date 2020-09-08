
public class Point {
	int x;
	int y;
	double distance = 0;
	
	int danger=0;
	
	
	public void manhattanDistance(Point A) {
		this.distance = Math.abs(A.getX()-this.getX())+Math.abs(A.getY()-this.getY());
		
	}
	public Point(int x, int y) {
		this.x =x;
		this.y = y;
		
	}
	
	public  int getX () {
		return this.x;
	}
	public  int getY () {
		return this.y;
	}
	public  double getDistance () {
		return this.distance;
	}
	public void setDistance(double d) {
		this.distance =d ;
	}
	public int getDanger() {
		return this.danger;
	}
	public void setDanger(int [][] playField) {
		int number = 0;
		int north = this.y-1;
		int south = this.y+1;
		int east = this.x-1;
		int west = this.x+1;
		
		if(north>=0 &&north<=49 && this.x>=0 &&this.x<=49) {
			number = number + playField [north][this.x];
		}
		if(this.y-2>=0 &&this.y-2 <=49 && this.x>=0 &&this.x<=49 ) {
			number = number + playField [this.y-2][this.x];
		}
		
		
		if(south>=0 &&south<=49 && this.x>=0 &&this.x<=49) {
			number = number + playField [south][this.x];
		}
		if(this.y+2>=0 && this.y+2 <=49 && this.x>=0 &&this.x<=49) {
			number = number + playField [this.y+2][this.x];
		}
		
		
		if(this.y>=0 &&this.y<=49 && west>=0 &&west<=49) {
			number = number + playField [this.y][west];
		}
		if(this.y>=0 &&this.y<=49 && this.x+2>=0 &&this.x+2<=49) {
			number = number + playField [this.y][this.x+2];
		}
		
		
		if(this.y>=0 &&this.y<=49 && east>=0 &&east<=49) {
			number = number + playField [this.y][east];
		}

		if(this.y>=0 &&this.y<=49 && this.x-2>=0 &&this.x-2<=49) {
			number = number + playField [this.y][this.x-2];
		}
		
		
		
		
		
		
		this.danger = number;
		
	}
	
}
