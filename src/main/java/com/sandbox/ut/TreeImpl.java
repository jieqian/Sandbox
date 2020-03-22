package com.sandbox.ut;

public class TreeImpl implements Tree {
    @Override
    public Tree grow(Seed seed, boolean isRotten) throws RottenSeedException {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void pluckFruits() {

    }

    @Override
    public <T> T cal(T first, T second) {
        if (first.equals(second) ){
            return first;
        }
        return second;
    }

    public static void main(String[] args) {
        Tree tree = new TreeImpl();
        System.out.println(tree.cal(1,1));


    }
}
