/**
 * 
 */
package com.ethan.marvel.sorter;

import java.util.Comparator;
import java.util.HashMap;

import com.ethan.marvel.Hero;
import com.ethan.marvel.HeroTeam;
import com.ethan.marvel.Unit;

/**
 * @author GBTW0011
 * created date: 2015年7月22日
 */
public class AbilitySorter implements Comparator<HeroTeam> {
    private HeroTeam mustHeroes = null;
    private int heroMastered = 5;
    private String abilityKey=null;
    /**
     * 
     */
    public AbilitySorter(String abilityKey, int heromaster, HeroTeam must) {
        heroMastered=heromaster;
        mustHeroes = must;
        this.abilityKey=abilityKey;
    }
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(HeroTeam o1, HeroTeam o2) {
        float v1 =0f;
        float v2 = 0f;
        
        float sum1=0f;
        float sum2=0f;
        
        
        for(Hero h:mustHeroes.getHeros()) {
//            System.out.println(h.getAttackType());
            HashMap<String, Unit> u1 = o1.getHeroDetail(heroMastered, h);
            HashMap<String, Unit> u2 = o2.getHeroDetail(heroMastered, h);
            if(u1.containsKey(abilityKey)) {
                sum1+=u1.get(abilityKey).getValue();
            }

            
            if(u2.containsKey(abilityKey)) {
                sum2+=u2.get(abilityKey).getValue();
            }

        }
        
        v1=sum1/(float)mustHeroes.getHeros().size();
        v2=sum2/(float)mustHeroes.getHeros().size();

        return ((int)(v2*1000f-v1*1000f))*10000 + (o2.getAbilities().size()-o1.getAbilities().size());
    }



}
