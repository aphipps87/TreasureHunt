// Alexander Phipps
// CS2 S25 - Section 0002
// 4/4/2025

public class TreasureHunt {

    // recursive backtracking
    public int findMinRiskRecursive(int[][] grid, int row, int col) 
    {
        int n = grid.length;
        int m = grid[0].length;

        // base case - if at the bottom right corner, return risk
        if (row == n - 1 && col == m - 1) 
        {
            return grid[row][col];
        }

        // if we're out of bounds, return a large number [path invalid]
        if (row >= n || col >= m) 
        {
            return Integer.MAX_VALUE;
        }

        // explore the right and down paths
        int right = findMinRiskRecursive(grid, row, col + 1);
        int down = findMinRiskRecursive(grid, row + 1, col);

        // return the current risk, plus the minimum of the two options
        return grid[row][col] + Math.min(right, down);
    }

    // memoization [top down dp]
    public int findMinRiskMemoization(int[][] grid, int row, int col, int[][] memo) 
    {
        int n = grid.length;
        int m = grid[0].length;

        // base case
        if (row == n - 1 && col == m - 1) 
        {
            return grid[row][col];
        }

        if (row >= n || col >= m) 
        {
            return Integer.MAX_VALUE;
        }

        // return cached result if already computed
        if (memo[row][col] != -1) 
        {
            return memo[row][col];
        }

        // compute + cache the result
        int right = findMinRiskMemoization(grid, row, col + 1, memo);
        int down = findMinRiskMemoization(grid, row + 1, col, memo);

        memo[row][col] = grid[row][col] + Math.min(right, down);
        return memo[row][col];
    }

    // tabulation [bottom up dp]
    public int findMinRiskTabulation(int[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];

        // fill the dp table
        for (int row = n - 1; row >= 0; row--) 
        {
            for (int col = m - 1; col >= 0; col--) 
            {
                if (row == n - 1 && col == m - 1) 
                {
                    dp[row][col] = grid[row][col]; // bottom right corner
                } 

                else
                {
                    int right = (col + 1 < m) ? dp[row][col + 1] : Integer.MAX_VALUE;
                    int down = (row + 1 < n) ? dp[row + 1][col] : Integer.MAX_VALUE;
                    dp[row][col] = grid[row][col] + Math.min(right, down);
                }
            }
        }

        return dp[0][0];
    }
}
