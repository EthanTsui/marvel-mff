/**
 * 
 */
package com.ethan.marvel.sorter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.ethan.marvel.Hero;
import com.ethan.marvel.HeroTeam;
import com.ethan.marvel.Unit;

/**
 * @author GBTW0011
 * created date: 2015年7月19日
 */
public class MustHeroAttackSorter implements Comparator<HeroTeam> {
    private HeroTeam mustHeroes = null;
    private int heroMastered = 5;
    /**
     * 
     */
    public MustHeroAttackSorter(int heromaster, HeroTeam must) {
        heroMastered=heromaster;
        mustHeroes = must;
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
            if(h.getAttackType().equals("p") && u1.containsKey("patk")) {
                sum1+=u1.get("patk").getValue();
            }
            else if(h.getAttackType().equals("e") && u1.containsKey("eatk")) {
                sum1+=u1.get("eatk").getValue();
            }
            
            if(u1.containsKey("aatk")) {
                sum1+=u1.get("aatk").getValue();
            }
            
            
            if(h.getAttackType().equals("p") && u2.containsKey("patk")) {
                sum2+=u2.get("patk").getValue();
            }
            else if(h.getAttackType().equals("e") && u2.containsKey("eatk")) {
                sum2+=u2.get("eatk").getValue();
            }
            if(u2.containsKey("aatk")) {
                sum2+=u2.get("aatk").getValue();
            }
            
        }
        
        v1=sum1/(float)mustHeroes.getHeros().size();
        v2=sum2/(float)mustHeroes.getHeros().size();
        
        
        
        return ((int)(v2*1000f-v1*1000f))*10000 + (o2.getAbilities().size()-o1.getAbilities().size());
    }

}
