
public class Point {
	int x;
	int y;
	double distance;
	
	
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
	
}
