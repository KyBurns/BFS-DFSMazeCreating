package BurnsKyle_LiangTony_Maze;

public class Main
{
   public static void main(String[] args)
   {
      Maze m = new Maze(4, 0);
      m.generateRoom();
      m.printMaze();
      m.removeWalls();
   }
}
