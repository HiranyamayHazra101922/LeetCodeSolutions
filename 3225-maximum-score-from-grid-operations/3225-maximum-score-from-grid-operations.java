class Solution {
    private int n;
    private long[][] pref;
    private Long[][][] memo;

    public long maximumScore(int[][] grid) {
        n = grid.length;
        // pref[c][r] stores the sum of grid[0...r-1][c]
        pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[j][i + 1] = pref[j][i] + grid[i][j];
            }
        }

        // State: [current column][height of previous column][is previous height < height before it?]
        memo = new Long[n][n + 1][2];
        
        long maxScore = 0;
        // Try every possible height for the first column
        for (int h = 0; h <= n; h++) {
            maxScore = Math.max(maxScore, solve(1, h, 0));
        }
        return maxScore;
    }

    private long solve(int col, int prevH, int isLess) {
        if (col == n) return 0;
        if (memo[col][prevH][isLess] != null) return memo[col][prevH][isLess];

        long res = 0;

        for (int currH = 0; currH <= n; currH++) {
            long currentScore = 0;
            
            if (currH > prevH) {
                // Column col is taller than col-1. 
                // col-1's white cells between prevH and currH now have a black neighbor to their right.
                // However, if we were already "increasing", those points might have been counted.
                // If isLess == 0 (prevH was >= height at col-2), we gain new points.
                if (isLess == 0) {
                    currentScore = pref[col - 1][currH] - pref[col - 1][prevH];
                }
            } else if (currH < prevH) {
                // Column col is shorter than col-1.
                // col's white cells between currH and prevH now have a black neighbor to their left.
                currentScore = pref[col][prevH] - pref[col][currH];
            }

            // Move to next column. 
            // New isLess is 1 if currH < prevH, else 0.
            res = Math.max(res, currentScore + solve(col + 1, currH, currH < prevH ? 1 : 0));
        }

        return memo[col][prevH][isLess] = res;
    }
}