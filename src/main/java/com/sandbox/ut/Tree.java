package com.sandbox.ut;

public interface Tree {
    Tree grow(Seed seed, boolean isRotten) throws RottenSeedException;

    String getName();

    void pluckFruits();

    <T> T cal(T first, T second);
}
