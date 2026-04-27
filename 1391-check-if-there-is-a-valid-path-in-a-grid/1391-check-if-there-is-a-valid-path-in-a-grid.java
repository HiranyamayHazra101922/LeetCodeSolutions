import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        // Mapping street type to allowed directions
        Map<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{2,3}); // left, right
        map.put(2, new int[]{0,1}); // up, down
        map.put(3, new int[]{2,1}); // left, down
        map.put(4, new int[]{3,1}); // right, down
        map.put(5, new int[]{2,0}); // left, up
        map.put(6, new int[]{3,0}); // right, up

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int i = curr[0], j = curr[1];

            if (i == m - 1 && j == n - 1) return true;

            for (int d : map.get(grid[i][j])) {
                int ni = i + dirs[d][0];
                int nj = j + dirs[d][1];

                if (ni < 0 || nj < 0 || ni >= m || nj >= n || visited[ni][nj])
                    continue;

                // Check reverse direction
                int rev = (d == 0) ? 1 : (d == 1) ? 0 : (d == 2) ? 3 : 2;

                for (int nd : map.get(grid[ni][nj])) {
                    if (nd == rev) {
                        visited[ni][nj] = true;
                        q.offer(new int[]{ni, nj});
                        break;
                    }
                }
            }
        }

        return false;
    }
}