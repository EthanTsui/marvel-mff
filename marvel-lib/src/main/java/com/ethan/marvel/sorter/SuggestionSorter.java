/**
 * 
 */
package com.ethan.marvel.sorter;

import java.util.Comparator;

import com.ethan.marvel.HeroTeam;

/**
 * @author GBTW0011
 * created date: 2015年5月22日
 */
public class SuggestionSorter implements Comparator<HeroTeam> {
    
    HeroTeam mustTeam =null;
    
    
    @Override
    public int compare(HeroTeam o1, HeroTeam o2) {

        return matchTeam(o1,o2)+
                (o2.getPairs() - o1.getPairs()) * 10000
                + (int) ((o2.getAverageProb() - o1.getAverageProb()) * 1000f);
    }

    
    public int matchTeam(HeroTeam o1, HeroTeam o2) {
        if(mustTeam==null) {
            return 0;
        }
        else {
            return (o2.getOverlap(mustTeam)*1000000)-(o1.getOverlap(mustTeam)*1000000);
        }
    }

    public HeroTeam getMustTeam() {
        return mustTeam;
    }


    public SuggestionSorter setMustTeam(HeroTeam mustTeam) {
        this.mustTeam = mustTeam;
        return this;
    }

}