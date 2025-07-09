class Solution {
    public int missingNumber(int[] a) {
        int n = a.length;
        int sum = 0;
        int exsum = n * (n + 1) / 2;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return (exsum - sum);
    }
}