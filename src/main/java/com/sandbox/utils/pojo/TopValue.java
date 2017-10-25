package com.sandbox.utils.pojo;

/**
 * Created by qianjie on 3/21/17.
 */

public abstract class TopValue<T extends Number> implements Comparable<TopValue<? extends Number>>{
    long time;
    T value;
    String tag1;
    String tag2;
    public TopValue(){
    }

    public TopValue(long time, T value, String tag1, String tag2){
        this.time = time;
        this.value = value;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public abstract T getValue();
    public abstract void setValue(T value);

    public abstract int innerCompare(TopValue<? extends Number> topValue );

    @Override
    public int compareTo(TopValue<? extends Number> topValue) {
        return innerCompare(topValue);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopValue<?> topValue = (TopValue<?>) o;

//        if (time != topValue.time) return false;
//        if (!value.equals(topValue.value)) return false;
//        if (!tag1.equals(topValue.tag1)) return false;
        return tag1.equals(topValue.tag1);
    }

    @Override
    public int hashCode() {
        int result = (int) (time ^ (time >>> 32));
        result = 31 * result + value.hashCode();
        result = 31 * result + tag1.hashCode();
        result = 31 * result + tag2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TopValue{" +
                "time=" + time +
                ", value=" + value +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                '}';
    }
}
