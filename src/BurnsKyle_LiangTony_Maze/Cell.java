package BurnsKyle_LiangTony_Maze;

public class Cell
{
   private int x;
   private int y;
   private boolean visited;
   private int distance;



   public Cell(int x, int y)
   {
      this.x = x;
      this.y = y;
      distance = 0;
   }

   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

   public int getDistance()
   {
      return distance;
   }

   public void setDistance(int distance)
   {
      this.distance = distance;
   }

   @Override
   public String toString()
   {
      return "Cell{" +
              "x=" + x +
              ", y=" + y +

              '}';
   }
}
