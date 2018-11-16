package BurnsKyle_LiangTony_Maze;

import java.util.*;

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
	}

   public char[][] getMaze()
   {
      return maze;
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
      initializeTfmaze();

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



   public static void printMaze(char[][] maze) {
      for (int i = 0; i < maze.length; i++) {
         for (int j = 0; j < maze.length; j++) {
            System.out.print(maze[i][j]);
         }
         System.out.println();
      }
   }

   public void bfsTraversing () {
	   char[][] mazeCopy = this.maze;
      initializeTfmaze();
      Queue<Cell> queue = new ArrayDeque<>();
      Cell head = new Cell(1,1);
      queue.offer(head);
      int counter = 0;
      while (!queue.isEmpty()) {
         Cell cur = queue.poll();
         mazeCopy[cur.getY()][cur.getX()] = (char) (counter % 10 + 48);
         counter ++;
         ArrayList<Cell> visitableNeighbors = new ArrayList<>();
         int curX = cur.getX();
         int curY = cur.getY();
         tfmaze[curY][curX] = true;
         //look up
         if (curY - 2 > 0 && mazeCopy[curY - 1][curX] == ' ' && !tfmaze[curY - 2][curX]) {
            Cell nextCell = new Cell(cur.getX(), cur.getY() - 2);
            visitableNeighbors.add(nextCell);
         }
         //look left
         if (curX - 2 > 0 && mazeCopy[curY][curX - 1] == ' ' && !tfmaze[curY][curX - 2]) {
            Cell nextCell = new Cell(cur.getX() - 2, cur.getY());
            visitableNeighbors.add(nextCell);
         }
         //look down
         if (curY + 2 < arrayLength && mazeCopy[curY + 1][curX] == ' ' && !tfmaze[curY + 2][curX]) {
            Cell nextCell = new Cell(cur.getX(), cur.getY() + 2);
            visitableNeighbors.add(nextCell);
         }
         //look right
         if (curX + 2 < arrayLength && mazeCopy[curY][curX+ 1] == ' ' && !tfmaze[curY][curX + 2]) {
            Cell nextCell = new Cell(cur.getX() + 2, cur.getY());
            visitableNeighbors.add(nextCell);
         }
         for (Cell c: visitableNeighbors) {
            queue.offer(c);
         }
      }
      printMaze(mazeCopy);
   }


   private double myrandom() {
      return myRandGen.nextDouble(); //random in 0-1
   }


   private void initializeTfmaze () {
      tfmaze = new boolean[arrayLength][arrayLength];
      for(int y = 1; y < arrayLength; y += 2) {
         for (int x = 1; x < arrayLength; x +=2) {
            tfmaze[y][x] = false;
         }
      }
   }

   private ArrayList<Cell> findNeighbors (Cell currentCell) {
      ArrayList<Cell> neighbors = new ArrayList<>();
      int curx = currentCell.getX();
      int cury = currentCell.getY();
      if(  curx - 2 > 0  && !tfmaze[cury][curx-2] ) {
         Cell leftNeighbor = new Cell(curx-2, cury);
         neighbors.add(leftNeighbor);
      }

      if( curx + 2 < arrayLength  && !tfmaze[cury][curx+2]) {
         Cell rightNeighbor = new Cell(curx+2, cury);
         neighbors.add(rightNeighbor);
      }
      if(cury-2 > 0  && !tfmaze[cury - 2][curx] ) {
         Cell upNeighbor = new Cell(curx, cury - 2);
         neighbors.add(upNeighbor);
      }
      if( cury + 2 < arrayLength && !tfmaze[cury + 2][curx]  ) {
         Cell downNeighbor = new Cell(curx, cury + 2);
         neighbors.add(downNeighbor);
      }
      return neighbors;
   }
}
