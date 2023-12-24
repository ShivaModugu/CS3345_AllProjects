import java.util.*;

public class MazeTest
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a number for rows: ");
        int r = sc.nextInt();
        System.out.println("Please input a number for columns: ");
        int c = sc.nextInt();
        Maze m = new Maze(r, c);
        m.print();
    }
}