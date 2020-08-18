import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {
	public static Point createPoint(String p) {
		String[] tmp = p.split(",");
		int x = Integer.parseInt(tmp[0]);
		int y = Integer.parseInt(tmp[1]);
		Point P = new Point(x, y);
		return P;
	}
	
	
	public static boolean isMoveValid(Point P, int [][] playArea) {
		boolean bool = false;
		if (P.getX()<=49 && P.getX()>=0 && P.getY()<=49 && P.getY()>=0 && playArea[P.getY()][P.getX()]==0) {
			bool =true;
		}
		return bool;
	}

	public static void drawObstacles(String snakeDesc, int snakeNum, int[][] playArea) {
		String[] snakePoints = snakeDesc.split(" ");
		for (int i = 0; i < snakePoints.length; i++) {
			if (i + 1 <= snakePoints.length - 1) {
				drawLine(snakePoints[i], snakePoints[i + 1], snakeNum, playArea);
			}

		}

	}

	public static void drawSnake(String snakeDesc, int snakeNum, int[][] playArea) {
		String[] snakePoints = snakeDesc.split(" ");
		for (int i = 3; i < snakePoints.length; i++) {
			if (i + 1 <= snakePoints.length - 1) {
				drawLine(snakePoints[i], snakePoints[i + 1], snakeNum, playArea);
			}

		}

	}
	//BFS
	public static Point nextMove(ArrayList<Point> validMoves, Point apple, Point prev,int timer) {
		Point minPoint = new Point(0,0);
		Point maxPoint = new Point(0,0);
		for (int i=0; i<validMoves.size(); i++) {
			if (minPoint.getDistance() == 0 && minPoint.getDistance()== 0) {
				minPoint =validMoves.get(i);
				maxPoint =validMoves.get(i);
				
			}
			else if(minPoint.getDistance() != 0 &&minPoint.getDistance() >validMoves.get(i).getDistance()) {
				minPoint=validMoves.get(i);
			}
			else if(maxPoint.getDistance() != 0 &&maxPoint.getDistance() < validMoves.get(i).getDistance()) {
				maxPoint=validMoves.get(i);
			}
		}
		
		return minPoint;
		
	}
	public static Point DFS(ArrayList<Point> validMoves, Point apple, Point prev,int timer) {
		Point minPoint = new Point(0,0);
		Point maxPoint = new Point(0,0);
		for (int i=0; i<validMoves.size(); i++) {
			if (minPoint.getDistance() == 0 && minPoint.getDistance()== 0) {
				minPoint =validMoves.get(i);
				maxPoint =validMoves.get(i);
				
			}
			else if(minPoint.getDistance() != 0 &&minPoint.getDistance() >validMoves.get(i).getDistance()) {
				minPoint=validMoves.get(i);
			}
			else if(maxPoint.getDistance() != 0 &&maxPoint.getDistance() < validMoves.get(i).getDistance()) {
				maxPoint=validMoves.get(i);
			}
		}
		
		return maxPoint;
		
	}
	
	public static Point safeNextMove(ArrayList<Point> validMoves) {
		Point minPoint = new Point(0,0);
		Point maxPoint = new Point(0,0);
		for (int i=0; i<validMoves.size(); i++) {
			if (minPoint.getDanger() == 0 && minPoint.getDanger()==0) {
				minPoint =validMoves.get(i);
				maxPoint =validMoves.get(i);
				
			}
			else if(minPoint.getDanger() != 0 &&minPoint.getDanger()> validMoves.get(i).getDanger()) {
				minPoint=validMoves.get(i); 
			}
			else if(maxPoint.getDanger() != 0 &&maxPoint.getDanger() <= validMoves.get(i).getDanger()) {
				maxPoint=validMoves.get(i);
			}
		}
		return minPoint;
		
		
	}

	public static void drawLine(String pointOne, String pointTwo, int snakeNum, int[][] playArea) {
		String[] start = pointOne.split(",");
		String[] end = pointTwo.split(",");

		int startX = Integer.parseInt(start[0]);
		int startY = Integer.parseInt(start[1]);
		int endX = Integer.parseInt(end[0]);
		int endY = Integer.parseInt(end[1]);

		int minX = Math.min(startX, endX);
		int maxX = Math.max(startX, endX);
		int minY = Math.min(startY, endY);
		int maxY = Math.max(startY, endY);

		int countX = minX;
		while (countX <= maxX) {
			playArea[minY][countX] = snakeNum;
			countX += 1;
		}
		int countY = minY;
		while (countY <= maxY) {
			playArea[countY][minX] = snakeNum;
			countY += 1;
		}

	}

	public static void printBoard(int[][] playArea) {
		for (int i = 0; i < playArea.length; i++) {
			for (int j = 0; j < playArea[i].length; j++) {
				System.err.print(playArea[i][j]);
				System.err.print(" ");
			}
			System.err.println(" ");
		}
	}
    public static boolean isAppleFresh(Point curr, Point prev, int timer) {
    	boolean bool = false;
    	if (curr != prev) { 
    		bool = true;
    	}
    	else if (curr == prev && timer < 5) {
    		bool = true;
    	}
    	return bool;
    }
	public static void main(String args[]) throws IOException {
		MyAgent agent = new MyAgent();
		MyAgent.start(agent, args);
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String initString = br.readLine();
			String[] temp = initString.split(" ");
			int nSnakes = Integer.parseInt(temp[0]);
			int width = Integer.parseInt(temp[1]);
			int height = Integer.parseInt(temp[2]);
			// read in obstacles and do something with them!
			int nObstacles = 3;
			String [] obstacleArray = new String [3];
			for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
				String obs = br.readLine();
				obstacleArray[obstacle] = obs;
				

				/// do something with obs
			}
			int timer  = 0;
			Point prev = new Point (-1,-1);
			while (true) {
				timer = timer + 1;
				String line = br.readLine();

				if (line.contains("Game Over")) {
					break;
				}
				int[][] playArea = new int[height][width];
				drawObstacles(obstacleArray[0],5, playArea);
				drawObstacles(obstacleArray[1],5, playArea);
				drawObstacles(obstacleArray[2],5, playArea);

				String apple = line;
				String[] appleArray = apple.split(" ");
				Point Apple = new Point(Integer.parseInt(appleArray[0]), Integer.parseInt(appleArray[1]));
				

				// do stuff with apples

				int headX=0, headY=0;
				Point head =new Point(-1,-1);
				int mySnakeNum = Integer.parseInt(br.readLine());
				for (int i = 0; i < nSnakes; i++) {
					String snakeLine = br.readLine();
					drawSnake(snakeLine, 8, playArea);
					if (i == mySnakeNum) {
						// hey! That's me :)
						drawSnake(snakeLine, 1, playArea);
						String[] desc = snakeLine.split(" ");
						head = createPoint(desc[3]);
						headX = head.getX();
						headY = head.getY();
						

					}
					
					// do stuff with other snakes
				}
				ArrayList<Point>validMoves= new ArrayList<Point>();
				
				// up
				Point north = new Point (headX, headY-1);
				north.setDanger(playArea);
				if(isMoveValid(north,playArea)) {
					validMoves.add(north);
					north.manhattanDistance(Apple);
					
				}
				//down
				Point south = new Point (headX, headY+1);
				south.setDanger(playArea);
				if(isMoveValid(south,playArea)) {
					validMoves.add(south);
					south.manhattanDistance(Apple);
				}
				//left
				Point east = new Point (headX+1, headY);
				east.setDanger(playArea);
				if(isMoveValid(east,playArea)) {
					validMoves.add(east);
					east.manhattanDistance(Apple);
				}
				//right
				Point west = new Point (headX-1, headY);
				west.setDanger(playArea);
				if(isMoveValid(west,playArea)) {
					validMoves.add(west);
					west.manhattanDistance(Apple);
				}
				
				Point tempPoint =new Point(-1,-1);
				head.manhattanDistance(Apple);
				double distance =head.getDistance() ;
				if(distance <50) {
					tempPoint =nextMove(validMoves, Apple,prev,timer);
	            }
	            ArrayList<Point> arr = new ArrayList<Point>();
	            arr.add(DFS(validMoves, Apple,prev,timer));
	            arr.add(safeNextMove(validMoves));
	            if(distance >=50) {
	            	tempPoint = arr.get(1);
	            }
	                	
				int move =2;
				if (tempPoint == north) {
					move = 0;
				}
				else if (tempPoint == east) {
					move = 3;
					
				}
				else if (tempPoint == south) {
					move = 1;
				}
				
				else if (tempPoint == west) {
					move = 2;
				}
				// finished reading, calculate move:
				System.out.println(move);
				if(Apple != prev) {
					timer=0;
				}
				prev = Apple;
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
