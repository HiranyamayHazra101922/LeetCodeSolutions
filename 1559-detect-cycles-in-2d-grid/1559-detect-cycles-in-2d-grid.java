class Solution {
    int m, n;
    boolean[][] visited;
    
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] grid, int x, int y, int px, int py) {
        visited[x][y] = true;
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            // boundary check
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            
            // only same character
            if (grid[nx][ny] != grid[x][y]) continue;
            
            // skip parent
            if (nx == px && ny == py) continue;
            
            // if already visited → cycle found
            if (visited[nx][ny]) return true;
            
            // DFS
            if (dfs(grid, nx, ny, x, y)) return true;
        }
        
        return false;
    }
}