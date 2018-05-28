package com.sandbox.ut;

public interface Tree {
    Tree grow(Seed seed, boolean isRotten) throws RottenSeedException;

    String getName();

    void pluckFruits();
}
