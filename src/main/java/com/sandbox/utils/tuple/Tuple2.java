package com.sandbox.utils.tuple;

/**
 * Created by qianjie on 8/29/17.
 */
public class Tuple2<E1,E2> extends Tuple {
    private E1 e1;
    private E2 e2;

    public Tuple2(E1 e1, E2 e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 _1(){
        return e1;
    }

    public E2 _2(){
        return e2;
    }

    @Override
    protected int hashCodeInternal() {
        final int prime = 31;
        int result = 1;
        E1 _1 = _1();
        E2 _2 = _2();
        result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
        result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
        return result;
    }

    @Override
    protected boolean elementEquals(Tuple tuple) {
        if(!(tuple instanceof Tuple2))
            return false;

        Tuple2 tuple2 = (Tuple2) tuple;
        if (this.e1.equals(tuple2._1()) && this.e2.equals(tuple2._2()))
            return true;
        else
            return false;
    }
}
