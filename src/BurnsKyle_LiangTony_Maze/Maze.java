package BurnsKyle_LiangTony_Maze;

public class Maze {

   private char[][] maze;
   private int size;
   private int arrayLength;

	public Maze(int size) {
		//Created constructor
      this.size = size;

      maze = new char[2 * size + 1][2 * size + 1];
      this.arrayLength = maze[0].length;
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

	public void printMaze() {
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze.length; j++) {
            System.out.print(this.maze[i][j]);
         }
         System.out.println();
      }
   }


}
