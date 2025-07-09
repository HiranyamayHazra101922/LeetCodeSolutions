class Solution {
    public boolean containsDuplicate(int[] a) {
        HashMap<Integer, Integer> mp = new HashMap<>();
       
        for (int i = 0; i < a.length; i++) {
            mp.put(a[i], mp.getOrDefault(a[i], 0) + 1);
        }
        for(int num : mp.values()){
            if(num > 1){
                return true;
            }
        }
        return false;
    }
}