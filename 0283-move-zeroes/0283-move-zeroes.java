class Solution {
    public void moveZeroes(int[] a) {
         int index = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[index++] = a[i];
            }
        }
        while (index < a.length) {
            a[index++] = 0;
        }

        for (int num : a) {
            System.out.print(num + " ");
        }
    }
}