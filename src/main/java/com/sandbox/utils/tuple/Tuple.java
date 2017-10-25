package com.sandbox.utils.tuple;

/**
 * Created by qianjie on 8/29/17.
 */
public abstract class Tuple {

    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + this.hashCodeInternal();
        return result;
    }

    abstract protected int hashCodeInternal();

    abstract protected boolean elementEquals(Tuple tuple);

    @Override
    public boolean equals(Object object){
        boolean rv = false;
        if(!(object instanceof Tuple))
            return false;
        if(object == this)
            return true;

        Tuple tuple = (Tuple)object;
        rv = elementEquals(tuple);

        return rv;
    }
}
