package com.sandbox.algorithm.tree;

import lombok.Data;

@Data
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }


}
