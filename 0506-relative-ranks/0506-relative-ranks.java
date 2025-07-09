import java.util.*;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];

        // Create a copy of the original array to sort
        int[] sorted = Arrays.copyOf(score, n);
        Arrays.sort(sorted);

        // Map each score to its rank
        Map<Integer, String> rankMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rank = n - i; // Since we sorted ascendingly
            if (rank == 1) {
                rankMap.put(sorted[i], "Gold Medal");
            } else if (rank == 2) {
                rankMap.put(sorted[i], "Silver Medal");
            } else if (rank == 3) {
                rankMap.put(sorted[i], "Bronze Medal");
            } else {
                rankMap.put(sorted[i], String.valueOf(rank));
            }
        }

        // Construct the result based on the original order
        for (int i = 0; i < n; i++) {
            result[i] = rankMap.get(score[i]);
        }

        return result;
    }
}
