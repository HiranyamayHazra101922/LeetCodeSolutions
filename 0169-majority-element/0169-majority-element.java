class Solution {
    public int majorityElement(int[] a) {
         HashMap<Integer, Integer> mp = new HashMap<>();
       
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            mp.put(a[i], mp.getOrDefault(a[i], 0) + 1);
        }
        for (HashMap.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() > a.length / 2) {
                ans = entry.getKey();
            }
        }
        return ans;
    }
}