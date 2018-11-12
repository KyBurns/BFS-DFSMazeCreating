package BurnsKyle_LiangTony_Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

   private char[][] maze;
   private int size;
   private int arrayLength;
   private Random myRandGen;

	public Maze(int size, int seed) {
		//Created constructor
      this.size = size;
      myRandGen=new java.util.Random(seed);
      arrayLength = 2 * size + 1;
      maze = new char[arrayLength][arrayLength];

	}
	public void generateRoom() {
		//System.out.println(maze[0].length);
	      for (int i = 0; i < arrayLength; i += 2) {
	         for (int j = 0; j < arrayLength; j++) {
	            if (j % 2 == 0) {
	               maze[i][j] = '+';
	            } else {
	               maze[i][j] = '-';
	            }
	         }
	      }
	      for (int i = 1; i < arrayLength; i += 2) {
	         for (int j = 0; j < arrayLength; j++) {
	            if (j % 2 == 0) {
	               maze[i][j] = '|';
	            } else {
	               maze[i][j] = ' ';
	            }
	         }
	      }


	      maze[0][1] = ' ';
	      maze[arrayLength - 1][arrayLength - 2] = ' ';
	}
	public void removeWalls() {
		Stack<int[]> CellStack = new Stack<int[]>();
		int totalCells = size*size;
		int[] currentCell = new int[2];
		currentCell[0] = 1;
		currentCell[1] = 1;
		int visitedCells = 1;
		//while(visitedCells<totalCells) {
			ArrayList<int[]> neighbors = new ArrayList<int[]>();
			if(currentCell[1]-2>0 && maze[currentCell[1]][currentCell[0]-1] =='|') {
				System.out.print("left");
				int[] leftNeighbor = new int[2];
				leftNeighbor[0] = currentCell[0]-2;
				leftNeighbor[1] = currentCell[1];
				neighbors.add(leftNeighbor);
			}
			System.out.println(currentCell[0]+2);
			System.out.println(maze[currentCell[0]+1][currentCell[1]]);
			if(currentCell[1]+2<arrayLength && maze[currentCell[1]][currentCell[0]+1] =='|') {
				System.out.print("right");
				int[] rightNeighbor = new int[2];
				rightNeighbor[0] = currentCell[0]+2;
				rightNeighbor[1] = currentCell[1];
				neighbors.add(rightNeighbor);
			}
			if(currentCell[0]-2>0 && maze[currentCell[1]-1][currentCell[0]] =='-') {
				System.out.print("up");
				int[] upNeighbor = new int[2];
				upNeighbor[0] = currentCell[0];
				upNeighbor[1] = currentCell[1]-2;
				neighbors.add(upNeighbor);
			}
			if(currentCell[0]+2>0 && maze[currentCell[1]+1][currentCell[0]] =='-') {
				System.out.print("down");
				int[] downNeighbor = new int[2];
				downNeighbor[0] = currentCell[0];
				downNeighbor[1] = currentCell[1]+2;
				neighbors.add(downNeighbor);
			}
			System.out.println(neighbors);
		//}
	}

	public void printMaze() {
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze.length; j++) {
            System.out.print(this.maze[i][j]);
         }
         System.out.println();
      }
   }
	
	double myrandom() {
		return myRandGen.nextDouble(); //random in 0-1
	}


}
