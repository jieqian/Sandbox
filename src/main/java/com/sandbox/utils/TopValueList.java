package com.sandbox.utils;

import com.sandbox.utils.pojo.TopValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by qianjie on 3/22/17.
 */
public class TopValueList<E extends TopValue<? extends Number>> extends LinkedList<E>{

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(e->addOrMerge(e));
        return true;
    }

    @Override
    public boolean add(E e) {
        addOrMerge(e);
        return true;
    }

    private void addOrMerge(E topValue) {
        if(super.isEmpty()){
            super.add(topValue);
            return;
        }
        ListIterator<E> itr = super.listIterator();
        boolean contains = super.contains(topValue);
        while (itr.hasNext()) {
            E current = itr.next();
            String currentTag1 = current.getTag1();
            String tag1 = topValue.getTag1();
            if (tag1.equals(currentTag1)) {
                if (topValue.compareTo(current) > 0) { // if the Value greater than the current's
                    itr.remove(); // remove current
                    addOrMerge(topValue); // add or merge the topValue again
                    break;
                } else
                    break;
            } else if (contains) { // if contains the same tag1
                continue;  // continue and ignore the Value until find the same Tag1
            } else if (topValue.compareTo(current) < 0) { // compare the Value
                if (itr.hasNext() == false) {
                    itr.add(topValue);
                } else {
                    continue;
                }
            } else {
                //insert the topValue before the current node of type TopValue
                itr.set(topValue);
                itr.add(current);
                break;
            }
        }
    }
}
