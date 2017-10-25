package com.sandbox.utils;

import com.sandbox.utils.pojo.Pojo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by qianjie on 3/21/17.
 */
public class LinkedListUtils {
    public static void addOrMerge(LinkedList<Pojo> pojos, Pojo pojo) {
        if(pojos.isEmpty()){
            pojos.add(pojo);
            return;
        }
        ListIterator<Pojo> itr = pojos.listIterator();
        boolean sameX = pojos.contains(pojo);
        while (itr.hasNext()) {
            Pojo current = itr.next();
            String currentX = current.getX();
            int currentY = current.getY();
            String x = pojo.getX();
            int y = pojo.getY();
            if (x.equals(currentX)) {
                if (y > currentY) {
                    itr.remove();
                    addOrMerge(pojos,pojo);
                    break;
                } else
                    break;
            } else if (sameX) {
                continue;  // continue and ignore the Y until find the same X
            } else if (y < currentY) {
                if (itr.hasNext() == false) {
                    itr.add(pojo);
                } else {
                    continue;
                }
            } else {
                //insert the pojo before the current node
                itr.set(pojo);
                itr.add(current);
                break;
            }
        }
    }

    public static void main(String[] args) {

        Pojo p1 = new Pojo("one", 30);
        Pojo p2 = new Pojo("two", 20);
        Pojo p3 = new Pojo("three", 10);
        List<Pojo> pojoList1 = new ArrayList<Pojo>();
        pojoList1.add(p1);
        pojoList1.add(p2);
        pojoList1.add(p3);
        int topN = pojoList1.size();

        Pojo p4 = new Pojo("four", 30);
        Pojo p5 = new Pojo("five", 20);
        Pojo p6 = new Pojo("six", 10);
        List<Pojo> pojoList2 = new ArrayList<Pojo>();
        pojoList2.add(p4);
        pojoList2.add(p5);
        pojoList2.add(p6);

        Pojo p7 = new Pojo("one", 31);
        Pojo p8 = new Pojo("two1", 299);
        Pojo p9 = new Pojo("three", 1);
        List<Pojo> pojoList3 = new ArrayList<Pojo>();
        pojoList3.add(p7);
        pojoList3.add(p8);
        pojoList3.add(p9);

        LinkedList<Pojo> pojos = new LinkedList<Pojo>();
        pojos.add(p1);
        pojos.add(p2);
        pojos.add(p3);

        for (Pojo pojo : pojoList2) {
            addOrMerge(pojos,pojo);
        }

        for (Pojo pojo : pojoList3) {
            addOrMerge(pojos,pojo);
        }

        pojos.forEach(pojo -> System.out.println(pojo));
    }
}
