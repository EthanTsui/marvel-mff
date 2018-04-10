/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ethan.marvel.sorter.MustHeroAttackSorter;

/**
 * @author GBTW0011 created date: 2015年7月19日
 */
public class TeamUpHelper {
    private static TeamUpHelper instance = null;
    private static final Integer LOCK = new Integer(0);
    private List<Integer> allHero = null;

    /**
     * 
     */
    private TeamUpHelper() {

    }

    public static TeamUpHelper getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new TeamUpHelper();
                }
            }
        }
        return instance;
    }

    public List<HeroTeam> teamUpSuggest(HeroTeam must) {
        Helper helper = new Helper();

        List<Integer> allHero = getAllHero();

        List<HeroTeam> teams = helper.list3(allHero, must);

        return teams;
    }

    public List<Integer> getAllHero() {
        if (allHero == null) {
            allHero = new ArrayList<Integer>();
            for (Hero h : HeroDB.getInstance().getHeros()) {
                allHero.add(h.getHeroId());
            }
        }
        return allHero;
    }

    
    public static void main(String[] args) {
        HeroTeam team = new HeroTeam();
        team.addHero(HeroDB.getInstance().lookupHero(30));
        List<HeroTeam> teams = TeamUpHelper.getInstance().teamUpSuggest(team);
        
        MustHeroAttackSorter sorter = new MustHeroAttackSorter(6, team);
        
        Collections.sort(teams, sorter);
        
        for(int i=0,size=teams.size();i<size;i++) {
            HeroTeam t=teams.get(i);
            System.out.println(t.getNameList(", ")+"\t"+t.getLeaderAbilityString("en", 6));
        }
        
    }
    
}
