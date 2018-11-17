package BurnsKyle_LiangTony_Maze;

public class Cell
{
   private int x; // the x coordinate of cell

   private int y; // the y coordinate of cell
   private Cell parent; // the parent of the cell



   public Cell(int x, int y)
   {
      this.x = x;
      this.y = y;
      parent = null;
   }

   public int getX()
   {
      return x;
   }

   public int getY()
   {
      return y;
   }

   public Cell getParent()
   {
      return parent;
   }

   public void setParent(Cell parent)
   {
      this.parent = parent;
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
