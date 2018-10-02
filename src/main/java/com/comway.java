import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by meivelp on 2/10/2018.
 */
public class Comway {
    public static void main(String[] args)
    {
        int M = 200, N = 200;
        int [] [] grid = new int[200][200];

        final String initPosition = "[5,5], [6,5], [7,5], [5,6], [6,6], [7,6]";
        List<String> initList = new ArrayList<>(Arrays.asList(initPosition.split(", ")));

        System.out.println("Original Generation");
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                String position = "[" + i + "," + j + "]";
                if (initList.contains(position)) {
                    grid[i][j] = 1;
                    System.out.print("[" + i + "," + j + "]");
                }
                else
                    grid[i][j] = 0;
            }
        }
        nextGeneration(grid, M, N);
    }

    // Function to identify next generation
    static void nextGeneration(int grid[][], int M, int N)
    {
        int[][] future = new int[M][N];
        int nextGenCount = 100;

        // Loop through every cell
        for (int currCount = 1; currCount <= nextGenCount; currCount++){
            for (int l = 1; l < M - 1; l++)
            {
                for (int m = 1; m < N - 1; m++)
                {
                    // finding no Of Neighbours that are alive
                    int aliveNeighbours = 0;
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            aliveNeighbours += grid[l + i][m + j];

                    // The cell needs to be subtracted from
                    // its neighbours as it was counted before
                    aliveNeighbours -= grid[l][m];

                    // Implementing the Rules of Life

                    // Cell is lonely and dies
                    if ((grid[l][m] == 1) && (aliveNeighbours < 2))
                        future[l][m] = 0;

                        // Cell dies due to over population
                    else if ((grid[l][m] == 1) && (aliveNeighbours > 3))
                        future[l][m] = 0;

                        // A new cell is born
                    else if ((grid[l][m] == 0) && (aliveNeighbours == 3))
                        future[l][m] = 1;

                        // Remains the same
                    else
                        future[l][m] = grid[l][m];
                }
            }

            System.out.println("\n Next Generation :" + currCount);
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if (future[i][j] == 1)
                        System.out.print("[" + i + "," + j + "]");

                }
            }
        }

    }
}
