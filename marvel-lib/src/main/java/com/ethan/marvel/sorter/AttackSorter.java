/**
 * 
 */
package com.ethan.marvel.sorter;

import java.util.Comparator;

import com.ethan.marvel.HeroTeam;
import com.ethan.marvel.Unit;

/**
 * @author GBTW0011
 * created date: 2015年7月13日
 */
public class AttackSorter implements Comparator<HeroTeam> {

    /**
     * 
     */
    public AttackSorter() {
        
    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(HeroTeam o1, HeroTeam o2) {
        int v1=(int)o1.averageHeroAttack(5)*100+ (int) allValue(o1);
        
        int v2=(int)o2.averageHeroAttack(5)*100+(int) allValue(o2);
        return v2-v1;
    }

    
    private float allValue(HeroTeam team) {
        float sum=0f;
        for(Unit u:team.getAbilities().values()) {
            sum+=u.getValue();
        }
        return sum;
    }
}
