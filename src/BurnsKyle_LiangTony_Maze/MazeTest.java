package BurnsKyle_LiangTony_Maze;

import org.junit.Before;
//import org.junit.Before;
//import org.junit.Test;

public class MazeTest {
   Maze testMaze;

   @Before
   public void setUp() {
       testMaze = new Maze(4,0);
   }



   @org.junit.Test
   public void MazeTest()
   {
      testMaze.generateRoom();
      testMaze.removeWalls();
      Maze.printMaze(testMaze.getMaze());
      testMaze.bfsTraversing();
      testMaze.dfsTraversing();
   }


}