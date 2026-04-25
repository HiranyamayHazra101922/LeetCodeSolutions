import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];
        
        // Step 1: Map 2D points to 1D positions along the perimeter [0, 4*side]
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) pos[i] = x;                         // Bottom edge
            else if (x == side) pos[i] = side + y;          // Right edge
            else if (y == side) pos[i] = 2L * side + (side - x); // Top edge
            else pos[i] = 3L * side + (side - y);          // Left edge
        }
        
        Arrays.sort(pos);
        
        // Step 2: Binary Search on the result
        int low = 1, high = 2 * side;
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(pos, n, k, mid, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean canPlace(long[] pos, int n, int k, int dist, int side) {
        // Because it's circular, the starting point matters. 
        // We only need to test starting points within the first 'gap'.
        // However, a simpler heuristic for competitive programming is testing
        // the first few points or using the doubled-array approach.
        for (int i = 0; i < n; i++) {
            if (pos[i] >= pos[0] + dist) break; // Optimization: don't restart too far
            
            int count = 1;
            long lastPos = pos[i];
            long firstPos = pos[i];
            
            for (int j = i + 1; j < n + i; j++) {
                long currentPos = pos[j % n];
                long actualPos = (j >= n) ? currentPos + 4L * side : currentPos;
                
                if (actualPos - lastPos >= dist) {
                    // Check Manhattan distance constraint for the very last point vs the first point
                    if (count + 1 == k) {
                        if (isManhattanValid(actualPos, firstPos, dist, side)) {
                            return true;
                        }
                    } else {
                        lastPos = actualPos;
                        count++;
                    }
                }
                if (count >= k) return true;
            }
            // If we can't even fit k points starting near the beginning, 
            // we likely can't fit them at all.
            if (i > n / k + 1) break; 
        }
        return false;
    }

    // Helper to verify if Manhattan distance matches or exceeds our 1D distance
    private boolean isManhattanValid(long p1, long p2, int target, int side) {
        // The Manhattan distance between two points on a square boundary is 
        // min(perimeter_dist, 2*side - (abs(x1-x2) if opposite sides...))
        // Actually, in the context of this problem, the 1D distance on the perimeter 
        // effectively tracks the Manhattan distance correctly for the "minimum" 
        // except for points on opposite sides.
        
        // Simplification: Convert 1D back to 2D to verify Manhattan
        int[] pt1 = getCoord(p1, side);
        int[] pt2 = getCoord(p2, side);
        return (Math.abs(pt1[0] - pt2[0]) + Math.abs(pt1[1] - pt2[1])) >= target;
    }

    private int[] getCoord(long p, int side) {
        p %= (4L * side);
        if (p <= side) return new int[]{(int)p, 0};
        if (p <= 2L * side) return new int[]{side, (int)(p - side)};
        if (p <= 3L * side) return new int[]{(int)(3L * side - p), side};
        return new int[]{0, (int)(4L * side - p)};
    }
}