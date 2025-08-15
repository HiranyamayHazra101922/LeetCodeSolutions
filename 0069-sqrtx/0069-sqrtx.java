class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x; // 0 -> 0, 1 -> 1

        int left = 1, right = x / 2;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Use long to prevent overflow when multiplying
            long square = (long) mid * mid;

            if (square == x) {
                return mid; // perfect square
            } else if (square < x) {
                ans = mid; // possible answer, but maybe bigger exists
                left = mid + 1;
            } else {
                right = mid - 1; // too big
            }
        }
        return ans;
    }
}