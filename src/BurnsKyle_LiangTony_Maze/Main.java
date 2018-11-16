package BurnsKyle_LiangTony_Maze;

public class Main
{
   public static void main(String[] args)
   {
      Maze m = new Maze(4, 1);
      m.generateRoom();

      m.removeWalls();
      Maze.printMaze(m.getMaze());
      System.out.println();
      m.bfsTraversing();
      m.dfsTraversing();
   }
}
