package BurnsKyle_LiangTony_Maze;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

   private char[][] maze;
   private boolean[][] tfmaze;

   private int size;
   private int arrayLength;
   private Random myRandGen;

	public Maze(int size, int seed) {
		//Created constructor
      this.size = size;
      myRandGen=new java.util.Random(seed);
      arrayLength = 2 * size + 1;
      maze = new char[arrayLength][arrayLength];
      tfmaze = new boolean[arrayLength][arrayLength];
      for(int y = 1; y < arrayLength; y += 2) {
         for (int x = 1; x < arrayLength; x +=2) {
            tfmaze[y][x] = false;
         }
      }





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
//=======================================================================================================================================
	public void removeWalls() {
		Stack<Cell> cellStack = new Stack<>();
		int totalCells = size*size;
		Cell currentCell = new Cell(1,1);

		int visitedCells = 1;

      while(visitedCells<totalCells) {

         //System.out.println("current cell is " + currentCell);
         ArrayList<Cell> neighbors = findNeighbors(currentCell);

         //printCellStack(cellStack);
         if (neighbors.size() > 0) {
            Cell randomNeighbor = neighbors.get((int) (myrandom() * neighbors.size()));
            //System.out.println("ranN is " + randomNeighbor);
            tfmaze[randomNeighbor.getY()][randomNeighbor.getX()] = true;
            int wallx = (randomNeighbor.getX() + currentCell.getX()) / 2;
            int wally = (randomNeighbor.getY() + currentCell.getY()) / 2;

            maze[wally][wallx] = ' ';

            cellStack.push(currentCell);
            currentCell = randomNeighbor;
            visitedCells += 1;
         } else {
            currentCell = cellStack.pop();
         }

      }
	}

   //=======================================================================================================================================
   public void printMaze() {
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze.length; j++) {
            System.out.print(this.maze[i][j]);
         }
         System.out.println();
      }
   }
   //=======================================================================================================================================
   //=======================================================================================================================================
   //=======================================================================================================================================
   //=======================================================================================================================================
	private double myrandom() {
		return myRandGen.nextDouble(); //random in 0-1
	}

   //=======================================================================================================================================
//	private void printNeighbors (ArrayList<int[]> neighbors) {
//      for (int[] cell: neighbors) {
//         System.out.print(Arrays.toString(cell));
//      }
//   }
   //=======================================================================================================================================
   private ArrayList<Cell> findNeighbors (Cell currentCell) {
      ArrayList<Cell> neighbors = new ArrayList<>();
      int curx = currentCell.getX();
      int cury = currentCell.getY();


      if(  curx - 2 > 0  && !tfmaze[cury][curx-2] ) {
         //System.out.println("left");
         Cell leftNeighbor = new Cell(curx-2, cury);
         neighbors.add(leftNeighbor);

      }


      if( curx + 2 < arrayLength  && !tfmaze[cury][curx+2]) {
         //System.out.println("right");
         Cell rightNeighbor = new Cell(curx+2, cury);
         neighbors.add(rightNeighbor);
      }
      //&& maze[cury - 1][curx] =='-'
      if(cury-2 > 0  && !tfmaze[cury - 2][curx] ) {
         //System.out.println("up");
         Cell upNeighbor = new Cell(curx, cury - 2);
         neighbors.add(upNeighbor);
      }

      if( cury + 2 < arrayLength && !tfmaze[cury + 2][curx]  ) {
         //System.out.println("down");

         Cell downNeighbor = new Cell(curx, cury + 2);
         neighbors.add(downNeighbor);

      }
      //printNeighbors(neighbors);

      return neighbors;
   }

}
