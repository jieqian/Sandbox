package com.sandbox.utils.pojo;

import java.util.List;

/**
 * Created by qianjie on 3/21/17.
 */
public class Series {
    String name;
    List<String> columns;
    List<List<Object>> values;

    public Series(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<List<Object>> getValues() {
        return values;
    }

    public void setValues(List<List<Object>> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", values=" + values +
                '}';
    }
}
