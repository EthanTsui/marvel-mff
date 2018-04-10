/**
 * 
 */
package com.ethan.marvel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.ethan.marvel.sorter.AttackSorter;

/**
 * @author GBTW0011 created date: 2015年5月15日
 */
public class Helper {
    private String language = "en";
    DecimalFormat df2 = new DecimalFormat("##0.0");

    /**
     * 
     */
    public Helper() {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Helper h = new Helper();
        h.run();

    }

    public void run() {
        printHeroStatistic();

        List<Integer> heroList = new ArrayList<Integer>();

        for (int i = 1; i <= 50; i++) {
            heroList.add(i);
        }

        // heroList.add(30);
        // heroList.add(36);
        // heroList.add(3);
        // heroList.add(12);
        // heroList.add(16);
        // heroList.add(17);
        // heroList.add(22);
        // heroList.add(23);
        // heroList.add(38);
        // heroList.add(42);
        // heroList.add(49);
        // heroList.add(50);

        HeroTeam must = new HeroTeam();
        must.addHero(HeroDB.getInstance().lookupHero(30));
        // must.addHero(HeroDB.getInstance().lookupHero(4)).addHero(HeroDB.getInstance().lookupHero(5));//
        // .addHero(lookupHero(33));
        // listResult(heroList);

        // Suggestion s = new Suggestion();
        // s.addHero(heros.get(1));
        // s.addHero(heros.get(2));
        // s.addHero(heros.get(8));
        // System.out.println(s.getNameList(",")+"\t"+s.getAverageProb());

        // listAll3();
        List<HeroTeam> suggestions = list3(heroList, must, -1);

        Collections.sort(suggestions, new AttackSorter());

        System.out.println("total combination: " + suggestions.size());
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println(suggestions.get(i).getNameList(",") + "\t"
                    + df2.format(suggestions.get(i).averageHeroAttack(5)) + "%\t"
                    + suggestions.get(i).getPairs() + "\t"
                    + df2.format(suggestions.get(i).getAverageProb() * 100f) + "%\t"
                    + suggestions.get(i).getLeaderAbilityString("en",5));

            if (i > 100) {
                break;
            }
        }
        // listHelpEachOther();
        // printHeroAndHelper();

    }

    public void listAll3() {
        System.out.println(" ====   3 pairs ===== ");
        List<HeroTeam> suggestions = new ArrayList<HeroTeam>();
        List<Hero> heros = HeroDB.getInstance().getHeros();
        for (int i = 0, size = heros.size(); i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    List<Integer> herolist = new ArrayList<Integer>();
                    herolist.add(heros.get(i).getHeroId());
                    herolist.add(heros.get(j).getHeroId());
                    herolist.add(heros.get(k).getHeroId());
                    if (countPair(herolist) == 6) {
                        HeroTeam suggestion = new HeroTeam();
                        suggestion.addHero(heros.get(i));
                        suggestion.addHero(heros.get(j));
                        suggestion.addHero(heros.get(k));
                        suggestions.add(suggestion);
                        // System.out.println(suggestion.getNameList(",")+"\t"+df2.format(suggestion.getAverageProb()*100f)+"%");
                        // System.out.println(heros.get(i).getHeroName()+"\t"+heros.get(j).getHeroName()+"\t"+heros.get(k).getHeroName());
                    }

                }
            }
        }

    }

    public Hero getHero(int heroid) {
        return HeroDB.getInstance().lookupHero(heroid);
    }

    public List<HeroTeam> list3(List<Integer> heroList, HeroTeam must) {
        return list3(heroList, must, -1);
    }

    public List<HeroTeam> list3(List<Integer> heroList, HeroTeam must, int leaderId) {
        // System.out.println(" ====   3 pairs ===== ");
        List<HeroTeam> suggestions = new ArrayList<HeroTeam>();
        for (int i = 0, size = heroList.size(); i < size; i++) {
            if (leaderId > 0 && heroList.get(i) != leaderId) {
                continue;
            }
            for (int j = 0; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    if (i == j || j == k || j == k || k == i) {
                        continue;
                    }
                    HeroTeam suggestion = new HeroTeam();
                    suggestion.addHero(HeroDB.getInstance().lookupHero(heroList.get(i)));
                    suggestion.addHero(HeroDB.getInstance().lookupHero(heroList.get(j)));
                    suggestion.addHero(HeroDB.getInstance().lookupHero(heroList.get(k)));

                    if (must != null && must.getHeros().size() > 0
                            && suggestion.getOverlap(must) < must.getHeros().size()) {
                        continue;
                    }

                    TeamBonusHelper.getInstance().determineTeamBonus(suggestion);
                    // if(must!=null &&
                    // suggestion.getOverlap(must)==must.getHeros().size()) {
                    suggestions.add(suggestion);
                    // }

                }
            }
        }

        return suggestions;
    }

    public void printHeroAndHelper() {
        List<Hero> heros = HeroDB.getInstance().getHeros();
        for (Hero hero : heros) {
            for (Striker striker : hero.getHelpers().values()) {
                System.out.println(LanguageHelper.getInstance().getHeroName(hero.getHeroId()) + "\t"
                        + LanguageHelper.getInstance().getHeroName(striker.getHero().getHeroId()) + "\t"
                        + df2.format(striker.getProb() * 100f) + "%");
            }
        }
    }

    HashMap<Integer, HeroPair> heroPairs = new HashMap<Integer, HeroPair>();

    public void listHelpEachOther() {
        List<Hero> heros = HeroDB.getInstance().getHeros();
        for (Hero hero : heros) {
            HeroPair heroPair = new HeroPair();
            heroPair.setHero(hero);
            for (Striker helper : hero.getHelpers().values()) {
                if (helper.getHero().containsHelper(hero.getHeroId())) {
                    System.out.println(hero.getHeroId() + "\t" + helper.getHero().getHeroId() + "\t"
                            + df2.format(helper.getProb() * 100f) + "%\t"
                            + df2.format(helper.getHero().getHelper(hero.getHeroId()).getProb() * 100f) + "%");

                    putPair(hero, helper.getHero());
                }
            }
        }

        System.out.println(" -- - - -  -- - - - - - - - -  ");
        List<HeroTeam> suggestions = new ArrayList<HeroTeam>();

        for (HeroPair hpair : heroPairs.values()) {
            Hero[] heroarray = new Hero[hpair.getHeros().size()];
            hpair.getHeros().values().toArray(heroarray);
            for (int i = 0; i < heroarray.length; i++) {
                if (heroPairs.containsKey(heroarray[i].getHeroId())) {
                    for (int j = i + 1; j < heroarray.length; j++) {
                        if (heroPairs.containsKey(heroarray[j].getHeroId())) {
                            HeroPair hp = heroPairs.get(heroarray[j].getHeroId());
                            if (hp.hasHero(hpair.getHero().getHeroId())) {
                                // System.out.println(hpair.getHero().getHeroName()
                                // + "\t" + heros[i].getHeroName() + "\t"
                                // + heros[j].getHeroName());
                                HeroTeam sug = new HeroTeam();
                                sug.addHero(hpair.getHero());
                                sug.addHero(heroarray[i]);
                                sug.addHero(heroarray[j]);

                                boolean found = false;
                                for (int x = 0; x < suggestions.size(); x++) {
                                    if (suggestions.get(i).getOverlap(sug) == 3) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    suggestions.add(sug);
                                }
                            }

                        }
                    }
                }

            }
        }

        for (HeroTeam suggestion : suggestions) {
            for (Hero hero : suggestion.getHeros()) {
                System.out.print(hero.getHeroId() + "\t");
            }
            System.out.println();
        }

    }

    private void putPair(Hero hero1, Hero hero2) {
        if (!heroPairs.containsKey(hero1.getHeroId())) {
            HeroPair hp = new HeroPair();
            hp.setHero(hero1);
            hp.getHeros().put(hero2.getHeroId(), hero2);
            heroPairs.put(hero1.getHeroId(), hp);
        } else {
            if (!heroPairs.get(hero1.getHeroId()).hasHero(hero2.getHeroId())) {
                heroPairs.get(hero1.getHeroId()).getHeros().put(hero2.getHeroId(), hero2);
            }
        }

        if (!heroPairs.containsKey(hero2.getHeroId())) {
            HeroPair hp = new HeroPair();
            hp.setHero(hero2);
            hp.getHeros().put(hero1.getHeroId(), hero1);
            heroPairs.put(hero2.getHeroId(), hp);
        } else {
            if (!heroPairs.get(hero2.getHeroId()).hasHero(hero1.getHeroId())) {
                heroPairs.get(hero2.getHeroId()).getHeros().put(hero1.getHeroId(), hero1);
            }
        }

    }

    public void printHeroStatistic() {
        System.out.println("hero name\t# of helpers\tavg. prob.\t# of as stricker\tavg. prob. as stricker");
        List<Hero> heros = HeroDB.getInstance().getHeros();
        for (Hero hero : heros) {
            float sum = 0.0f;
            int counter = 0;
            for (Hero hero2 : heros) {
                if (hero.getHeroId() == hero2.getHeroId()) {
                    continue;
                }
                if (hero2.containsHelper(hero.getHeroId())) {
                    sum += hero2.getHelper(hero.getHeroId()).getProb();
                    counter++;
                }

            }
            System.out.println(hero.getHeroId() + "\t" + hero.getHelperSize() + "\t"
                    + df2.format(hero.getAverageHelperPercentage() * 100) + "%" + "\t" + counter + "\t"
                    + df2.format((sum / (float) counter) * 100f) + "%");
        }

    }

    public void listResult(List<Integer> heroList) {
        System.out.println("= = = = = = = = = = = = = = = =");
        for (int x : heroList) {

            System.out.println(HeroDB.getInstance().lookupHero(x).getHeroId() + "\t"
                    + HeroDB.getInstance().lookupHero(x).getHelperSize() + "\t"
                    + df2.format(HeroDB.getInstance().lookupHero(x).getAverageHelperPercentage() * 100) + "%");
        }

        System.out.println("- - - - - - - - - - - - - - - -");
        System.out.println("英雄\t\t幫手\t\t出現機率 (出現機率-此英雄幫手的平均出現機率)");
        for (int i = 0, size = heroList.size(); i < size; i++) {
            Hero hero = HeroDB.getInstance().lookupHero(heroList.get(i));
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }

                if (hero.containsHelper(heroList.get(j))) {
                    Striker helper = hero.getHelper(heroList.get(j));
                    System.out.println(hero.getHeroId() + "\t\t" + helper.getHero().getHeroId() + "\t\t"
                            + ((int) (helper.getProb() * 100)) + "% ("
                            + df2.format((helper.getProb() - hero.getAverageHelperPercentage()) * 100) + "%)");

                }

            }
        }

    }

    public int countPair(List<Integer> heroList) {
        int counter = 0;
        for (int i = 0, size = heroList.size(); i < size; i++) {
            Hero hero = HeroDB.getInstance().lookupHero(heroList.get(i));
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }

                if (hero.containsHelper(heroList.get(j))) {
                    counter++;

                }

            }
        }
        return counter;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
