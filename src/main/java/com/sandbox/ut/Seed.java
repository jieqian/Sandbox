package com.sandbox.ut;

public class Seed {
    private String name;
    private boolean isRotten;
    private Tree tree;

    public Seed(String name) {
        this.name = name;
    }

    public Seed(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }

    public Tree grow() throws RottenSeedException {
        if (tree != null) {
            return tree.grow(this, isRotten);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String nameOfThreTree = (tree == null)  ? name : tree.getName();
        return "Seed(" + nameOfThreTree + ")";
    }

    public boolean isRotten() {
        return isRotten;
    }

    public void setRotten(boolean isRotten) {
        this.isRotten = isRotten;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
