package com.sandbox.utils.tuple;

/**
 * Created by qianjie on 8/29/17.
 */
public class Tuple4<E1,E2,E3,E4> extends Tuple {
    private E1 e1;
    private E2 e2;
    private E3 e3;
    private E4 e4;

    public Tuple4(E1 e1, E2 e2, E3 e3, E4 e4){
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
    }

    public E1 _1(){
        return e1;
    }

    public E2 _2(){
        return e2;
    }

    public E3 _3(){
        return e3;
    }

    public E4 _4() {
        return e4;
    }

    @Override
    protected int hashCodeInternal() {
        final int prime = 31;
        int result = 1;
        E1 _1 = _1();
        E2 _2 = _2();
        E3 _3 = _3();
        E4 _4 = _4();
        result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
        result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
        result = prime * result + ((_3 == null) ? 0 : _3.hashCode());
        result = prime * result + ((_4 == null) ? 0 : _4.hashCode());
        return result;
    }

    @Override
    protected boolean elementEquals(Tuple tuple) {
        if(!(tuple instanceof Tuple4))
            return false;

        Tuple4 tuple4 = (Tuple4) tuple;
        if (this.e1.equals(tuple4._1())
                && this.e2.equals(tuple4._2())
                && this.e3.equals(tuple4._3())
                && this.e4.equals(tuple4._4()))
            return true;
        else
            return false;
    }
}
