package BurnsKyle_LiangTony_Maze;

import java.util.*;

public class Maze {

   private char[][] maze; // container for the maze
   private boolean[][] tfmaze; // a boolean 2D array that help generating and traversing the maze later

   private int size; // the size of the maze, size * size is the total number of cells
   private int arrayLength; // the length of the maze container
   private Random myRandGen; // a random object for randomly generating the maze

   // Constructor
   //
	public Maze(int size, int seed) {
      this.size = size;
      myRandGen=new java.util.Random(seed);
      arrayLength = 2 * size + 1;
      maze = new char[arrayLength][arrayLength];
	}
   //==========================================================================================================================
	// Getters and setters
   //==========================================================================================================================
   // Returns the maze as a 2D char array
   public char[][] getMaze()
   {
      return maze;
   }

   // Returns the length of the 2D array maze
   public int getArrayLength() {
      return arrayLength;
   }

   // Returns the size of the maze,
   public int getSize() {
      return size;
   }

   // Returns the 2D boolean maze
   public boolean[][] gettfmaze(){
      return tfmaze;
   }
   //==========================================================================================================================
   // Public Methods
   //==========================================================================================================================
  // this method fill out the instance variable maze with walls and blank cell
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
//=============================================================================================================================
	// This method randomly removes walls in the instance variable maze
   public void removeWalls() {
      initializeTfmaze();
		Stack<Cell> cellStack = new Stack<>(); // cellStack for DFS traversing
		int totalCells = size*size; // total cells of the maze
		Cell currentCell = new Cell(1,1); // current cell
		int visitedCells = 1; // number of visited cells
		tfmaze[1][1] = true; // set the first cell status as visited
      while(visitedCells<totalCells) {

         ArrayList<Cell> neighbors = findNeighbors(currentCell);
         if (neighbors.size() > 0) {
            Cell randomNeighbor = neighbors.get((int) (myRandom() * neighbors.size()));
            tfmaze[randomNeighbor.getY()][randomNeighbor.getX()] = true;
            int wallx = (randomNeighbor.getX() + currentCell.getX()) / 2;
            int wally = (randomNeighbor.getY() + currentCell.getY()) / 2;
            maze[wally][wallx] = ' '; // break the walls
            cellStack.push(currentCell);
            currentCell = randomNeighbor;
            visitedCells += 1;
         } else {
            currentCell = cellStack.pop();
         }

      }
      //printMaze(maze);
	}
   //==========================================================================================================================
   // Traverses the maze by using BFS, printing out the traversing steps and the shortest route
   public void bfsTraversing () {
	   char[][] mazeCopy = copy2dArray(maze); // make a deep copy of maze to fill the distance when traversing
	   char[][] routeMaze = copy2dArray(maze); // make a deep copy of maze to fill the shortest route
      initializeTfmaze(); // set the status of all cell to unvisited

	   Cell end = new Cell(arrayLength-2, arrayLength-2);

      Queue<Cell> queue = new ArrayDeque<>(); // queue for BDF traversing
      Cell head = new Cell(1,1); // head ceel
      queue.offer(head);
      int counter = 0;

      while (!queue.isEmpty()) {
         Cell cur = queue.poll();
         int curX = cur.getX();
         int curY = cur.getY();
         if (curX == arrayLength - 2 && curY == arrayLength - 2) {
            // if the cur is the end cell, end the the traversing process
            end = cur;
            mazeCopy[curY][curX] = (char) (counter % 10 + 48);
            break;
         }

         ArrayList<Cell> visitableNeighbors = findVisitableNeighbors(tfmaze,mazeCopy,cur);
         // all visitable neighbors of cur

         for (Cell c: visitableNeighbors) {
            queue.offer(c);
         }

         tfmaze[curY][curX] = true;
         mazeCopy[curY][curX] = (char) (counter % 10 + 48);
         counter ++;
      }
      System.out.println("traversal using BFS: ");
      printMaze(mazeCopy);
      fillInHashTag(end,routeMaze);
      System.out.println("\n"+"the shortest route generated by BFS: ");
      printMaze(routeMaze);
   }
//==========================================================================================================================
   // Traverses the maze by using DFS, printing out the traversing steps and the shortest route

   public void dfsTraversing () {
      char[][] mazeCopy = copy2dArray(maze);// make a deep copy of maze to fill the distance when traversing
      char[][] routeMaze = copy2dArray(maze);// make a deep copy of maze to fill the shortest route
      initializeTfmaze();

      ArrayList<Cell> endWrapper = new ArrayList<>();
      // a wrapper to backtrack the end cell in recursion functions



      ArrayList<Cell> cellArrayList = new ArrayList<>();
      // a list to contain all cells

      for (int x = 1; x < arrayLength; x += 2) {
         for (int y =1; y < arrayLength; y += 2) {
            cellArrayList.add(new Cell(x,y));
         }
      }

      int counter = 0;
      for (Cell c: cellArrayList) {
         if (!tfmaze[c.getY()][c.getX()]) {
            if (dfsVisit(mazeCopy,c,counter,endWrapper)) break;
            //dfsVisit will return true if it finds the destination
         }
      }
      System.out.println("\n" + "traversal using DFS: ");

      printMaze(mazeCopy);

      Cell end = endWrapper.get(0);

      fillInHashTag(end,routeMaze);

      System.out.println("\n"+"the shortest route generated by DFS: ");
      printMaze(routeMaze);

   }


   //==========================================================================================================================
   // Utility method
   //==========================================================================================================================
   // this method prints out the parameter 2D array
   public static void printMaze(char[][] maze) {
      for (int i = 0; i < maze.length; i++) {
         for (int j = 0; j < maze.length; j++) {
            System.out.print(maze[i][j]);
         }
         System.out.println();
      }
   }
   //==========================================================================================================================
   // Private Methods
   //==========================================================================================================================
   // this Method returns a randomly generated double value
   private double myRandom() {
      return myRandGen.nextDouble(); //random in 0-1
   }
   //==========================================================================================================================
   // this method is a recursion helper function for DFS
   // returns true if the destination cell is found, return false other wise
   // the destination cell will be stored in the endWrapper eventually
   // which will be used for finding the shortest route
   private boolean dfsVisit(char[][] mazeCopy, Cell cur, int counter, ArrayList<Cell> endWrapper) {
      mazeCopy[cur.getY()][cur.getX()] =  (char) (counter % 10 + 48);
      tfmaze[cur.getY()][cur.getX()] = true;
      counter++;

      if (cur.getY() == arrayLength - 2 && cur.getX() == arrayLength -2) {
         Cell end = cur;
         endWrapper.add(end);
         //printMaze(mazeCopy);
         return true;
      }

      ArrayList<Cell> neighbors = findVisitableNeighbors(tfmaze,mazeCopy,cur);
      for (Cell c: neighbors) {
         if (!tfmaze[c.getY()][c.getX()]) {
            if(dfsVisit(mazeCopy,c,counter,endWrapper)) {
               return true;
            }
         }
      }

      //printMaze(mazeCopy);
      return false;
   }

   //==========================================================================================================================
   // this method initializes the instance variable tfmaze with all false
   private void initializeTfmaze () {
      tfmaze = new boolean[arrayLength][arrayLength];
      for(int y = 1; y < arrayLength; y += 2) {
         for (int x = 1; x < arrayLength; x +=2) {
            tfmaze[y][x] = false;
         }
      }
   }
   //==========================================================================================================================
   // This method returns an ArrayList containing all the visitable neighbors of the current node
   private ArrayList<Cell> findVisitableNeighbors(boolean[][] tfmaze, char[][] mazeCopy, Cell cur) {
      ArrayList<Cell> visitableNeighbors = new ArrayList<>();
      int curX = cur.getX();
      int curY = cur.getY();
      //look down
      if (curY + 2 < arrayLength && mazeCopy[curY + 1][curX] == ' ' && !tfmaze[curY + 2][curX]) {
         Cell nextCell = new Cell(cur.getX(), cur.getY() + 2);
         nextCell.setParent(cur);
         visitableNeighbors.add(nextCell);
      }
      //look right
      if (curX + 2 < arrayLength && mazeCopy[curY][curX+ 1] == ' ' && !tfmaze[curY][curX + 2]) {
         Cell nextCell = new Cell(cur.getX() + 2, cur.getY());
         nextCell.setParent(cur);
         visitableNeighbors.add(nextCell);
      }
      //look up
      if (curY - 2 > 0 && mazeCopy[curY - 1][curX] == ' ' && !tfmaze[curY - 2][curX]) {
         Cell nextCell = new Cell(cur.getX(), cur.getY() - 2);
         nextCell.setParent(cur);
         visitableNeighbors.add(nextCell);
      }
      //look left
      if (curX - 2 > 0 && mazeCopy[curY][curX - 1] == ' ' && !tfmaze[curY][curX - 2]) {
         Cell nextCell = new Cell(cur.getX() - 2, cur.getY());
         nextCell.setParent(cur);

         visitableNeighbors.add(nextCell);
      }


      return visitableNeighbors;
   }

   //==========================================================================================================================
   // This method returns a ArrayList that contains all unvisited neighbors from the current cell.

   private ArrayList<Cell> findNeighbors (Cell currentCell) {
      ArrayList<Cell> neighbors = new ArrayList<>();
      int curx = currentCell.getX();
      int cury = currentCell.getY();
      //look left
      if(  curx - 2 > 0  && !tfmaze[cury][curx-2] ) {
         Cell leftNeighbor = new Cell(curx-2, cury);
         neighbors.add(leftNeighbor);
      }
      //look right
      if( curx + 2 < arrayLength  && !tfmaze[cury][curx+2]) {
         Cell rightNeighbor = new Cell(curx+2, cury);
         neighbors.add(rightNeighbor);
      }
      //look up
      if(cury - 2 > 0  && !tfmaze[cury - 2][curx]) {
         Cell upNeighbor = new Cell(curx, cury - 2);
         neighbors.add(upNeighbor);
      }
      //loop down
      if( cury + 2 < arrayLength && !tfmaze[cury + 2][curx]) {
         Cell downNeighbor = new Cell(curx, cury + 2);
         neighbors.add(downNeighbor);
      }
      return neighbors;
   }


   //==========================================================================================================================
   // these method returns a deep copy of the given array
   private char[][] copy2dArray(char[][] copyThis) {
      char[][] newMaze = new char[arrayLength][];
      for(int i=0;i<arrayLength;i++) {
         newMaze[i] = maze[i].clone();
      }
      return newMaze;
   }

   //==========================================================================================================================
   // fill the 2D char Array with hash tag based on the end Cell
   private void fillInHashTag (Cell end, char[][]routeMaze) {
      Cell pointer = end;
      while (pointer != null) {
         routeMaze[pointer.getY()][pointer.getX()] = '#';

         pointer = pointer.getParent();
      }
   }
   //==========================================================================================================================

}
