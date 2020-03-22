package com.sandbox.algorithm.tree.traverse;

import com.sandbox.algorithm.tree.TreeNode;
import com.sandbox.algorithm.tree.Utils;

import java.util.ArrayList;
import java.util.List;

public class Sandbox {
    public static List<Integer> preTraversePath = new ArrayList<>();
    public static List<Integer> midTraversePath = new ArrayList<>();
    public static List<Integer> postTraversePath = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode treeNode = Utils.buildBST();
        preOrder(treeNode);
        inOrder(treeNode);
        postOrder(treeNode);
        preTraversePath.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();
        midTraversePath.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();
        postTraversePath.stream().forEach(e -> System.out.print(e + " "));
    }

    public static void preOrder(TreeNode treeNode){
        if (treeNode != null){
            preTraversePath.add(treeNode.val);
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }

    public static void inOrder(TreeNode treeNode){
        if (treeNode != null){
            inOrder(treeNode.left);
            midTraversePath.add(treeNode.val);
            inOrder(treeNode.right);
        }
    }

    public static void postOrder(TreeNode treeNode){
        if (treeNode != null){
            postOrder(treeNode.left);
            postOrder(treeNode.right);
            postTraversePath.add(treeNode.val);
        }
    }

}
