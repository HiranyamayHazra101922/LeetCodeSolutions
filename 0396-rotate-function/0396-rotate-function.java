class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long currentF = 0;
        long sum = 0;
        
        // Step 1: Compute F(0) and the total sum of the array
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            currentF += (long) i * nums[i];
        }
        
        long maxF = currentF;
        
        // Step 2: Use the recurrence relation to find F(1)...F(n-1)
        // F(k) = F(k-1) + sum - n * last_element_of_previous_rotation
        for (int i = 1; i < n; i++) {
            // The element that moves from the (n-1) index to the 0 index 
            // in the logical rotation is nums[n - i]
            currentF = currentF + sum - (long) n * nums[n - i];
            maxF = Math.max(maxF, currentF);
        }
        
        return (int) maxF;
    }
}