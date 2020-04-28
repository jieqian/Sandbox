package com.sandbox.algorithm.tree.lowestcommonancestor;

import com.sandbox.algorithm.tree.TreeNode;
import com.sandbox.algorithm.tree.Utils;

public class Sandbox {
    public static void main(String[] args) {
        TreeNode treeNode = Utils.buildBST();
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(7);
       System.out.println(lowestCommonAncestor(treeNode, p, q).val);
//        System.out.println(bstLowestCommonAncestor(treeNode, p, q).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);  // 遍历左子树
        TreeNode right = lowestCommonAncestor(root.right, p, q); // 遍历右子树
//        return left == null ? right : right == null ? left : root;
        if (left == null){
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public static TreeNode bstLowestCommonAncestor (TreeNode root, TreeNode p, TreeNode q){
        TreeNode commonAncestor = null;
        while (root!=null) {
            if (root.val > p.val && root.val > q.val){
                root = root.left;
            } else if (root.val < p.val && root.val < q.val){
                root = root.right;
            }  else {
                commonAncestor = root;
                break;
            }
        }
        if (commonAncestor == null) {
            throw new IllegalStateException("the node is not exist");
        } else {
            return commonAncestor;
        }
    }
}
