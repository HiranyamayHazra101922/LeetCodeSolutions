/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
     public TreeNode help(int[] a, int st, int end) {
        if (st > end) {
            return null;
        }
        int mid = st + (end - st) / 2;
        TreeNode root = new TreeNode(a[mid]);

        root.left = help(a, st, mid - 1);
        root.right = help(a, mid + 1, end);

        return root;
    }
    public TreeNode sortedArrayToBST(int[] a) {
        return help(a , 0 , a.length - 1);
    }
}