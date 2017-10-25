package com.sandbox.utils.pojo;

/**
 * Created by qianjie on 3/21/17.
 */
public class TopDoubleValue extends TopValue<Double>{
    public TopDoubleValue(){
        super(0L,0.0d,"","");
    }

    public TopDoubleValue(long time, double value, String tag1, String tag2){
        super(time,value,tag1,tag2);
    }

    @Override
    public Double getValue() {
        return super.value;
    }

    @Override
    public void setValue(Double value) {
        super.value = value;
    }

    @Override
    public int innerCompare(TopValue<? extends Number> topValue) {
        Double value = (Double) topValue.getValue();
        Double currentValue = getValue();
        return currentValue.compareTo(value);
    }

}
