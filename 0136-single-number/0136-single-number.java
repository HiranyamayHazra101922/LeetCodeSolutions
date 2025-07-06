class Solution {
    public int singleNumber(int[] a) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            mp.put(a[i], mp.getOrDefault(a[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == 1) { // ✅ Check for frequency = 1
               res += (entry.getKey());
            }
        }
        return res;
    }
}