
import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    private int startrow;
    private int startcol;

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
        try {
         Scanner sc1 = new Scanner(new File(filename));
         int row = 0;
         int col = 0;
         if (sc1.hasNextLine()) {
           col = sc1.nextLine().length();
           row = 1;
         }
         while (sc1.hasNextLine()) {
           row += 1;
           sc1.nextLine();
         }
         maze = new char[row][col];
         String placeholder = "";
         Scanner sc2 = new Scanner(new File(filename));

         for (int i =0; i < row; i++) {
           placeholder = sc2.nextLine();
           for (int j = 0; j < col; j++) {
             maze[i][j] = placeholder.charAt(j);
             if (maze[i][j] == 'S') {
               startrow = i;
               startcol = j;
             }
           }
         }
       } catch (FileNotFoundException e) {}
    }
    public String toString() {
      String output = "";
      for (int i= 0; i < maze.length; i++) {
        for (int j = 0; j < maze[0].length; j++) {
          output += maze[i][j];
        }
        output += "\n";
      }
      return output;
    }
    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){

            //find the location of the S.
            //already found

            //erase the S
            // animate = true;
            // System.out.println(startrow);
            // System.out.println(startcol);
            maze[startrow][startcol] = ' ';
            return solve(startrow,startcol,0);
            //and start solving at the location of the s.

            //return solve(???,???);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int count){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }
        if (maze[row][col] == '@' || maze[row][col] == '#' || maze[row][col] == '.') {
          return 0;
        }
        if (maze[row][col] == 'E') {
          return count;
        }
        if (maze[row][col] == ' ') {
          maze[row][col] = '@';
        }
        int right =  solve(row, col + 1, count + 1);
        if (right > 0) {
          return right;
        }
        int left =  solve(row, col - 1, count + 1);
        if (left > 0) {
          return left;
        }
        int top =  solve(row + 1, col, count + 1);
        if (top > 0) {
          return top;
        }
        int bottom =  solve(row - 1, col, count + 1);
        if (bottom > 0) {
          return bottom;
        }
        maze[row][col] = '.';
        //COMPLETE SOLVE

        return -1; //so it compiles
    }


}
