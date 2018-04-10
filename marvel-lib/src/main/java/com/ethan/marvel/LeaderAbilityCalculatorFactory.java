/**
 * 
 */
package com.ethan.marvel;

/**
 * @author GBTW0011 created date: 2015年7月8日
 */
public class LeaderAbilityCalculatorFactory {
    private static LeaderAbilityCalculatorFactory instance = null;
    private static final Integer LOCK = new Integer(0);

    /**
     * 
     */
    private LeaderAbilityCalculatorFactory() {
        // TODO Auto-generated constructor stub
    }

    public static LeaderAbilityCalculatorFactory getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LeaderAbilityCalculatorFactory();
                }
            }
        }
        return instance;
    }

    public void assign(Hero hero) {
        if (hero.getAbility("impl") == null) {

        } else {

        }
    }

}
