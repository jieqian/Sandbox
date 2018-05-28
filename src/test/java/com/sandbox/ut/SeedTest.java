package com.sandbox.ut;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SeedTest {
    public Tree tree;

    @Before
    public void createStub() {
        tree = mock(Tree.class);
    }

    @Test
    public void seedIsRotten() throws Exception {
        Seed seed = new Seed(tree);
        when(tree.grow(seed, true)).thenThrow(new RottenSeedException());
        seed.grow();
        seed.setRotten(true);
        try {
            seed.grow();
        } catch (RottenSeedException e) {
        }
    }

    @Test
    public void stubWithThrowable() {
        doThrow(new NoFruitsFoundException()).when(tree).pluckFruits();
        try{
            tree.pluckFruits();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}