public class DisjSets
{
    DisjSets( int num )
    {
        solution = new int [num];
        int i =0;

        while(i < solution.length)
        {
            solution[ i ] = -1;
            i++;
        }

    }

    public void union( int num1, int num2 )
    {
        if (solution[num2] < solution[num1]) 
        {
            solution[num1] = num2; // root2 is deeper, make root2 new root
        } 
        else 
        {
            solution[num1] += (solution[num1] == solution[num2]) ? -1 : 0;
            solution[num2] = num1; // Make root1 new root
        }

    }

    public int find( int x )
    {
        if(solution[ x ] >= 0)
        {
            return solution[ x ] = find( solution[ x ] );
        }
        else
        {
            return x;
        }
    }

    private int solution[ ];
}