/**
 * 
 */
package com.ethan.marvel;


/**
 * @author GBTW0011
 * created date: 2015年5月21日
 */
public class Striker {
    Hero hero = null;
    float prob;
    float coolTime;
    String autoType;
    

    public Striker(Hero hero, float prob) {
        this.hero = hero;
        this.prob = prob;
    }

    public float getProb() {
        return prob;
    }

    public Striker setProb(float prob) {
        this.prob = prob;
        return this;
    }

    public Hero getHero() {
        return hero;
    }

    public Striker setHero(Hero hero) {
        this.hero = hero;
        return this;
    }

    public float getCoolTime() {
        return coolTime;
    }

    public Striker setCoolTime(float coolTime) {
        this.coolTime = coolTime;
        return this;
    }

    public String getAutoType() {
        return autoType;
    }

    public Striker setAutoType(String autoType) {
        this.autoType = autoType;
        return this;
    }

}