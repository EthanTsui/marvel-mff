/**
 * 
 */
package com.ethan.marvel;

import java.util.HashMap;


/**
 * @author GBTW0011
 * created date: 2015年5月22日
 */
public class HeroPair {
    Hero hero;
    HashMap<Integer, Hero> heros = new HashMap<Integer, Hero>();

    public Hero getHero() {
        return hero;
    }

    public HeroPair setHero(Hero hero) {
        this.hero = hero;
        return this;
    }

    public HashMap<Integer, Hero> getHeros() {
        return heros;
    }

    public HeroPair setHeros(HashMap<Integer, Hero> heros) {
        this.heros = heros;
        return this;
    }

    public boolean hasHero(int heroId) {
        return heros.containsKey(heroId);
    }

}