class Solution {
    public int thirdMax(int[]a) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        HashSet<Integer> seen = new HashSet<>();

        for (int num : a) {
            if (!seen.contains(num)) {
                seen.add(num);
                min.add(num);

                if (min.size() > 3) {
                    min.poll();
                }
            }
        }

        if (min.size() < 3) {
            int max = Integer.MIN_VALUE;
            for (int num : seen) {
                max = Math.max(max, num);
            }
            return max;
        }
        return min.peek();
    }
}