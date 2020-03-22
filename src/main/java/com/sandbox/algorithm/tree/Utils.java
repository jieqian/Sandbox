package com.sandbox.algorithm.tree;

public class Utils {
    public static TreeNode buildBST(){
        TreeNode thirdLessL = new TreeNode(1);
        TreeNode thirdLessR = new TreeNode(3);
        TreeNode secondLess = new TreeNode(2, thirdLessL, thirdLessR);
        TreeNode thirdGreatL = new TreeNode(5);
        TreeNode thirdGreatR = new TreeNode(7);
        TreeNode secondGreat = new TreeNode(6, thirdGreatL, thirdGreatR);
        return new TreeNode(4, secondLess, secondGreat);
    }
}
