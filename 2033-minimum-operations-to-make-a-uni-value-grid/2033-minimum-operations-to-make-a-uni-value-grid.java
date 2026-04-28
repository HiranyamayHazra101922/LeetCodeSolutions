import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int size = m * n;
        int[] arr = new int[size];
        
        // Step 1: Flatten grid
        int k = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[k++] = val;
            }
        }
        
        // Step 2: Check feasibility
        int base = arr[0];
        for (int val : arr) {
            if ((val - base) % x != 0) {
                return -1;
            }
        }
        
        // Step 3: Sort
        Arrays.sort(arr);
        
        // Step 4: Take median
        int median = arr[size / 2];
        
        // Step 5: Count operations
        int operations = 0;
        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}