class Solution {
    public int secondHighest(String s) {
        TreeSet<Integer> st = new TreeSet<>();

        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                st.add(ch - '0');
            }
        }

        if(st.size() < 2){
            return -1;
        }

        st.pollLast();

        return st.last();
    }
}