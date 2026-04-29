class Solution {
    private int n;
    private long[][] pref;
    private Long[][][] memo;

    public long maximumScore(int[][] grid) {
        n = grid.length;
       
        pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                pref[j][i + 1] = pref[j][i] + grid[i][j];
            }
        }

        memo = new Long[n][n + 1][2];
        
        long maxScore = 0;

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
                if (isLess == 0) {
                    currentScore = pref[col - 1][currH] - pref[col - 1][prevH];
                }
            } else if (currH < prevH) {
                currentScore = pref[col][prevH] - pref[col][currH];
            }
            res = Math.max(res, currentScore + solve(col + 1, currH, currH < prevH ? 1 : 0));
        }

        return memo[col][prevH][isLess] = res;
    }
}