package com.sandbox.algorithm.tree.bst;

import com.sandbox.algorithm.tree.TreeNode;

import static com.sandbox.algorithm.tree.Utils.buildBST;

public class Sandbox {

    public static Integer pre = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode treeNode = buildBST();
        System.out.println(isValidBST(treeNode));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        boolean mid = pre >= root.val ? false : true;
        pre = root.val;
        boolean right = isValidBST(root.right);
        return left && mid && right;
    }
}


