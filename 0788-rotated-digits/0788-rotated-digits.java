class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGood(int num) {
        boolean hasRotatingDigit = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // If it contains 3, 4, or 7, it's invalid
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            
            // Check if it contains at least one digit that changes the number
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasRotatingDigit = true;
            }
            
            num /= 10;
        }
        
        // Must contain at least one 2, 5, 6, or 9 and NO 3, 4, 7
        return hasRotatingDigit;
    }
}