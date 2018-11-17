package BurnsKyle_LiangTony_Maze;

import org.junit.Before;

//import org.junit.Before;
//import org.junit.Test;

public class MazeTest {
   Maze testMaze;
   Maze testMaze2;
   Maze testMaze3;

   @Before
   public void setUp() {
      testMaze = new Maze(4,0);
      testMaze2 = new Maze(7,0);
      testMaze3 = new Maze(17,0);

   }



   @org.junit.Test
   public void test1()
   {
      testMaze.generateRoom();
      testMaze.removeWalls();
      Maze.printMaze(testMaze.getMaze());
      testMaze.bfsTraversing();
      testMaze.dfsTraversing();
   }


   @org.junit.Test
   public void test2()
   {
      testMaze2.generateRoom();
      testMaze2.removeWalls();
      Maze.printMaze(testMaze2.getMaze());
      testMaze2.bfsTraversing();
      testMaze2.dfsTraversing();
   }


   @org.junit.Test
   public void test3()
   {
      testMaze3.generateRoom();
      testMaze3.removeWalls();
      Maze.printMaze(testMaze3.getMaze());
      testMaze3.bfsTraversing();
      testMaze3.dfsTraversing();
   }


}