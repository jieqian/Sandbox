package com.sandbox.utils.tuple;

/**
 * Created by qianjie on 8/29/17.
 */
public class Tuple3<E1,E2,E3> extends Tuple {
    private E1 e1;
    private E2 e2;
    private E3 e3;

    public Tuple3(E1 e1, E2 e2, E3 e3){
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public E1 _1(){
        return e1;
    }

    public E2 _2(){
        return e2;
    }

    public E3 _3() {
        return e3;
    }

    @Override
    protected int hashCodeInternal() {
        final int prime = 31;
        int result = 1;
        E1 _1 = _1();
        E2 _2 = _2();
        E3 _3 = _3();
        result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
        result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
        result = prime * result + ((_3 == null) ? 0 : _3.hashCode());
        return result;
    }

    @Override
    protected boolean elementEquals(Tuple tuple) {
        if(!(tuple instanceof Tuple3))
            return false;

        Tuple3 tuple3 = (Tuple3) tuple;
        if (this.e1.equals(tuple3._1())
                && this.e2.equals(tuple3._2())
                && this.e3.equals(tuple3._3()))
            return true;
        else
            return false;
    }
}
