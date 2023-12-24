import java.util.*;

public class Maze
{
    class MazeSquare
    {
        public boolean up;
        public boolean down;
        public boolean left;
        public boolean right;

        public MazeSquare()
        {
            down = true;
            right = true;
        }

        public void print()
        {
            //if(down == false) 
            if(down == true)
                System.out.print("_");
            //System.out.println("check1");
            else
                System.out.print(" ");
            if(right == true)
                System.out.print("|");
            //System.out.println("check2");
            else
                System.out.print(" ");
        }
    }

    private MazeSquare[] self;
    private int row;
    private int column;
    private int length;

    public Maze(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.length = row * column;
        this.self = new MazeSquare[this.length];

        int forForLoop = 0;
        while(forForLoop < this.length)
        {
            self[forForLoop] = new MazeSquare();
            forForLoop++;
        }

        DisjSets ds = new DisjSets(length);

        //int count = 1;
        //boolean flag = false;
        //tried a while loop but kept infiniting

        for (int count = 1; count <= length - 1;) {
            for (boolean flag = false; !flag;) {
                int rand;
                int first = (int) (Math.random() * length);
                int second;

                //------------------------------------------------------------------------
                if( first == 0)
                {
                    rand = (int)(Math.random() * 2);
                    if(rand != 0)
                    {
                        second = first + column;
                    }
                    else
                    {
                        second = first + 1;
                    }

                }
                //------------------------------------------------------------------------
                else if(first == length - column)
                {
                    rand = (int)(Math.random() * 2);
                    if(rand !=0)
                    {
                        second = first + 1;
                    }
                    else
                    {
                        //System.out.print("Check");
                        second = first - column;
                    }

                }
                //------------------------------------------------------------------------
                else if(first == length - 1)
                {
                    rand = (int)(Math.random() * 2);
                    if(rand != 0)
                    {
                        second = first - column;
                    }
                    else
                    {
                        second = first - 1;
                    }

                }
                //------------------------------------------------------------------------
                else if(first == column - 1)
                {
                    rand = (int)(Math.random() * 2);
                    if(rand !=0)
                    {
                        second = first + column;
                    }
                    else
                    {
                        second = first - 1;
                    }

                }
                //------------------------------------------------------------------------
                else if(first % column == 0)
                {
                    rand = (int)(Math.random() * 3);
                    if(rand!=0 && rand!=1)
                    {
                        second = first + column;
                    }
                    else if(rand == 0)
                    {
                        second = first - column;
                    }
                    else 
                    {
                        second = first + 1;
                    }

                }
                //------------------------------------------------------------------------
                else if(first % column == column - 1)
                {
                    rand = (int)(Math.random() * 3);
                    if(rand!=0 && rand!=1)
                    {
                        second = first + column;
                    }
                    else if(rand == 0)
                    {
                        second = first - column;
                    }
                    else 
                    {
                        second = first - 1;
                    }
                }
                //------------------------------------------------------------------------
                else if(first > 0 && first < column - 1)
                {
                    rand = (int)(Math.random() * 3);
                    if(rand!=0 && rand!=1)
                    {
                        second = first + column;
                    }
                    else if(rand == 0)
                    {
                        second = first - 1;
                    }
                    else 
                    {
                        second = first + 1;
                    }

                }

                //------------------------------------------------------------------------
                else if(first > length - column && first < length - 1)
                {
                    rand = (int)(Math.random() * 3);
                    if(rand!=0 && rand!=1)
                    {
                        second = first + 1;
                    }
                    else if(rand == 0)
                    {
                        second = first - column;
                    }
                    else 
                    {
                        second = first - 1;
                    }

                }
                //------------------------------------------------------------------------
                else
                {

                    rand = (int)(Math.random() * 4);
                    if((rand != 0) && (rand!= 1) && (rand!=2))
                    {
                        second = first + column;
                    }
                    else if(rand == 0)
                    {
                        second = first - column;
                    }
                    else if(rand == 1)
                    {
                        second = first - 1; 	
                    }
                    else
                    {
                        second = first + 1;
                    }
                }

                //------------------------------------------------------------------------
                int set1 = ds.find(first);
                int set2 = ds.find(second);

                //checking flag
                if(set1 != set2)
                {
                    //System.out.println("Check");
                    ds.union(set1, set2);

                    if(second == first - column)
                    {
                        this.self[second].down = false;
                    }

                    else if(second == first + 1)
                    {
                        this.self[first].right = false;
                    }

                    else if(second == first - 1)
                    {
                        this.self[second].right = false;
                    }

                    else if(second == first + column)
                    {
                        this.self[first].down = false;
                    }

                    flag = true;
                    count++;
                }
            }

        }
    }
    //done 
    public void print()
    {
        System.out.print("  ");

        // Print the horizontal line
        int horizontalCount = 1;
        while (horizontalCount < this.column) {
            System.out.print(" _");
            horizontalCount++;
        }
        System.out.println();

        // Print the grid
        int i = 0;
        while (i < this.length - 1) {
            // Print the left border or space
            if (i % this.column == 0) {
                System.out.print((i == 0) ? " " : "|");
            }

            // Print the content
            this.self[i].print();

            // Print the right border and newline
            if (i % this.column == this.column - 1) {
                System.out.println();
            }

            i++;
        }

    }

}