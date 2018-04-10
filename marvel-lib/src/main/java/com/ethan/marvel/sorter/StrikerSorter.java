/**
 * 
 */
package com.ethan.marvel.sorter;

import java.util.Comparator;

import com.ethan.marvel.Striker;

/**
 * @author GBTW0011
 * created date: 2015年7月16日
 */
public class StrikerSorter implements Comparator<Striker> {

    /**
     * 
     */
    public StrikerSorter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Striker o1, Striker o2) {
        return (int) (o2.getProb()*1000f-o1.getProb()*1000f);
    }

}
