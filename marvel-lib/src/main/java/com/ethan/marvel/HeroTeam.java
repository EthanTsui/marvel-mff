/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author GBTW0011 created date: 2015年5月22日
 */
public class HeroTeam {
    String id = "";
    List<Hero> heroes = new ArrayList<Hero>();
    HashMap<String, Unit> abilities = new HashMap<String, Unit>();
    HashMap<String, Unit> leaderAbilities = null;
    HashMap<String, HashMap<String, Unit>> cacheHeroDetail = new HashMap<String, HashMap<String, Unit>>();

    public HeroTeam putAbility(String key, Unit obj) {
        abilities.put(key, obj);
        return this;
    }

    public HeroTeam addAbility(String key, Unit obj) {
        if (abilities.containsKey(key)) {
            Unit unit = abilities.get(key);
            Unit newUnit = new Unit();
            newUnit.setUnit(unit.getUnit());
            float value = unit.getValue() + obj.getValue();
            newUnit.setValue(value);
            abilities.put(key, newUnit);

        } else {
            abilities.put(key, obj);
        }
        return this;
    }

    public HeroTeam addHero(Hero hero) {
        heroes.add(hero);
        return this;
    }

    public int getPairs() {
        int count = 0;

        for (Hero hero : heroes) {
            for (Hero helper : heroes) {
                if (hero.getHeroId() == helper.getHeroId()) {
                    continue;
                }

                if (hero.containsHelper(helper.getHeroId())) {
                    count++;
                }

            }
        }

        return count;
    }

    public float getAverageProb() {
        float sum = 0.0f;
        int counter = 0;

        for (Hero hero : heroes) {
            for (Hero helper : heroes) {
                if (hero.getHeroId() == helper.getHeroId()) {
                    continue;
                }

                if (hero.containsHelper(helper.getHeroId())) {
                    sum += hero.getHelper(helper.getHeroId()).getProb();
                    counter++;
                }

            }
        }

        return sum / (float) counter;
    }

    public List<Hero> getHeros() {
        return heroes;
    }

    public HeroTeam setHeros(List<Hero> heros) {
        this.heroes = heros;
        return this;
    }

    public int getOverlap(HeroTeam suggestion) {
        int counter = 0;
        for (Hero hero : heroes) {
            for (Hero hero2 : suggestion.getHeros()) {
                if (hero.getHeroId() == hero2.getHeroId()) {
                    counter++;
                }
            }
        }
        return counter;

    }

    public String getNameList(String sep) {
        StringBuilder sb = new StringBuilder();
        if (heroes.size() > 0) {
            sb.append(LanguageHelper.getInstance().getHeroName(heroes.get(0).getHeroId()));
        }

        for (int i = 1; i < heroes.size(); i++) {
            sb.append(sep);
            sb.append(LanguageHelper.getInstance().getHeroName(heroes.get(i).getHeroId()));
        }
        return sb.toString();
    }

    public String getHeroidList(String sep) {
        StringBuilder sb = new StringBuilder();
        if (heroes.size() > 0) {
            sb.append(Integer.toString(heroes.get(0).getHeroId()));
        }

        for (int i = 1; i < heroes.size(); i++) {
            sb.append(sep);
            sb.append(Integer.toString(heroes.get(i).getHeroId()));
        }
        return sb.toString();
    }

    public HashMap<String, Unit> getAbilities() {
        return abilities;
    }

    public HeroTeam setAbilities(HashMap<String, Unit> abilities) {
        this.abilities = abilities;
        return this;
    }

    public String getAbilityString(String language) {
        StringBuilder sb = new StringBuilder(120);
        for (String key : abilities.keySet()) {
            sb.append(LanguageHelper.getInstance().getAbilityName(language, key));
            sb.append(": ");
            sb.append(abilities.get(key).toString());
            sb.append(", ");
        }
        if (heroes.get(0).getAbilities().containsKey("c")) {
            sb.append(heroes.get(0).getAbility("c"));
        }

        return sb.toString();
    }

    public String getLeaderAbilityString(String language, int heroMastered) {

        HashMap<String, Unit> abi = getTeamDetail(heroMastered);
        StringBuilder sb = new StringBuilder(120);
        for (String key : abi.keySet()) {
            sb.append(LanguageHelper.getInstance().getAbilityName(language, key));
            sb.append(": ");
            sb.append(abi.get(key).toString());
            sb.append(", ");
        }

        if (heroes.get(0).getAbilities().containsKey("c")) {
            sb.append(heroes.get(0).getAbility("c"));
        }

        return sb.toString();
    }

    public HashMap<String, Unit> getTeamDetail(int heroMastered) {

        if (leaderAbilities != null) {
            return leaderAbilities;
        }

        HashMap<String, Unit> abi = new HashMap<String, Unit>();
        for (String key : abilities.keySet()) {
            Unit u = abilities.get(key);
            Unit nu = new Unit();
            nu.setValue(u.getValue());
            nu.setUnit(u.getUnit());
            abi.put(key, nu);
        }

        // plus leadership

        Hero leader = heroes.get(0);

        if (leader.getAbilities().containsKey("impl")) {

            applyLeaderAbility(heroMastered, abi, leader);

        } else {
            for (String key : leader.getAbilities().keySet()) {
                if (AbilityHelper.getInstance().isUnitType(key)) {
                    Unit nu = new Unit();

                    Unit u = (Unit) leader.getAbilities().get(key);

                    nu.setValue(u.getValue() * ((float) heroMastered));
                    nu.setUnit(u.getUnit());
                    putAbility(abi, key, nu);
                }
            }
        }

        leaderAbilities = abi;

        return abi;
    }

    public HashMap<String, Unit> getHeroDetail(int heroMastered, Hero queryHero) {
        String cacheKey = heroMastered + "_" + queryHero.getHeroId();
        if (cacheHeroDetail.containsKey(cacheKey)) {
            return cacheHeroDetail.get(cacheKey);
        }

        Hero leader = heroes.get(0);

        HashMap<String, Unit> abi = new HashMap<String, Unit>();

        // put team bonus
        for (String key : abilities.keySet()) {
            Unit u = abilities.get(key);
            if ("aatk".equals(key)) {
                putAbility(abi, "patk", u);
                putAbility(abi, "eatk", u);
            } else if ("adef".equals(key)) {
                putAbility(abi, "pdef", u);
                putAbility(abi, "edef", u);
            } else if ("alls".equals(key)) {
                putAbility(abi, "as", u);
                putAbility(abi, "ms", u);
            }

            else {
                putAbility(abi, key, u);

            }
        }

        if (leader.getAbilities().containsKey("impl")) {

            applyLeaderAbility(heroMastered, abi, leader, queryHero);

        } else if ("all".equals(leader.getScope()) || queryHero.isHeroType(leader.getScope())) {
            for (String key : leader.getAbilities().keySet()) {
                if (AbilityHelper.getInstance().isUnitType(key)) {
                    Unit nu = new Unit();
                    Unit u = (Unit) leader.getAbilities().get(key);
                    nu.setValue(u.getValue() * ((float) heroMastered));
                    nu.setUnit(u.getUnit());

                    if ("aatk".equals(key)) {
                        putAbility(abi, "patk", nu);
                        putAbility(abi, "eatk", nu);
                    } else if ("adef".equals(key)) {
                        putAbility(abi, "pdef", nu);
                        putAbility(abi, "edef", nu);
                    } else if ("alls".equals(key)) {
                        putAbility(abi, "as", nu);
                        putAbility(abi, "ms", nu);
                    }

                    else {
                        putAbility(abi, key, nu);

                    }
                }
            }
        }

        cacheHeroDetail.put(cacheKey, abi);

        return abi;
    }

    public void applyLeaderAbility(int heroMastered, HashMap<String, Unit> abi, Hero leader, Hero queryHero) {
        String impl = (String) leader.getAbilities().get("impl");
        if ("THANOS".equals(impl)) {
                // System.out.println(h.getHeroId()+"\t"+h.getSubtype());
                if (queryHero.getSubtype().equals("501")) {
                    Unit nu = new Unit();
                    nu.setUnit("%");
                    // nu.setValue((((float) heroMastered) * 6f)*((float)cc/3f));
                    nu.setValue(((float) heroMastered) * 6f);

                    // System.out.println("CC: "+cc+", " + nu.getValue());

                    Unit nu2 = new Unit();
                    nu2.setUnit("%");
                    // nu2.setValue(((float) heroMastered) * 5f*((float)cc/3f));
                    nu2.setValue(((float) heroMastered) * 5f);

                    abi.put("aatk", nu);
                    abi.put("adef", nu);

                    abi.put("cct", nu2);
                }
        }
        else {
            applyLeaderAbility(heroMastered, abi, leader);
        }
    }

    public void applyLeaderAbility(int heroMastered, HashMap<String, Unit> abi, Hero leader) {
        String impl = (String) leader.getAbilities().get("impl");
        if ("YONDU".equals(impl)) {
            int counter = 0;
            for (Hero h : heroes) {
                if (h.isHeroType("s")) {
                    counter++;
                }
            }
            float[] num = { 0f, 0.7f, 1f, 1.3f };

            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(((float) heroMastered) * num[counter]);

            // apply to all
            putAbility(abi, "ctr", nu);
        } else if ("NEBULA".equals(impl)) {
            int counter = 0;
            for (Hero h : heroes) {
                if (h.isHeroType("c")) {
                    counter++;
                }
            }
            float[] num = { 0f, 0.7f, 1f, 1.3f };

            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(((float) heroMastered) * num[counter]);

            // apply to all
            putAbility(abi, "do", nu);

        } else if ("YELLOWJACKET".equals(impl)) {
            if (heroMastered == 1) {
                Unit u = new Unit();
                u.setUnit("Cooldown time 20 seconds");
                u.setValue(20);
                abi.put("LEADER_INFO", u);
            } else if (heroMastered == 2) {
                Unit u = new Unit();
                u.setUnit("Cooldown Time 18 seconds");
                u.setValue(18);
                abi.put("LEADER_INFO", u);
            } else if (heroMastered == 3) {
                Unit u = new Unit();
                u.setUnit("Cooldown Time 18 seconds");
                u.setValue(18);
                abi.put("LEADER_INFO", u);
                Unit nu = new Unit();
                nu.setUnit("s");
                nu.setValue(3);

                abi.put("par", nu);

            } else if (heroMastered == 4) {
                Unit u = new Unit();
                u.setUnit("Cooldown Time 15 seconds");
                u.setValue(15);
                abi.put("LEADER_INFO", u);
                Unit nu = new Unit();
                nu.setUnit("s");
                nu.setValue(3);

                abi.put("par", nu);

            } else if (heroMastered == 5) {
                Unit u = new Unit();
                u.setUnit("Cooldown Time 15 seconds");
                u.setValue(15);
                abi.put("LEADER_INFO", u);
                Unit nu = new Unit();
                nu.setUnit("s");
                nu.setValue(4);

                abi.put("par", nu);

            } else if (heroMastered >= 6) {
                Unit u = new Unit();
                u.setUnit("Cooldown Time 12 seconds");
                u.setValue(15);
                abi.put("LEADER_INFO", u);
                Unit nu = new Unit();
                nu.setUnit("s");
                nu.setValue(4);

                abi.put("par", nu);
            }

        } else if ("DRAX".equals(impl)) {
            int counter = 0;
            for (Hero h : heroes) {
                if (h.isHeroType("c")) {
                    counter++;
                }
            }
            float[] draxhp = { 0f, 3.5f, 5f, 7.5f };

            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(((float) heroMastered) * draxhp[counter]);

            // apply to all
            putAbility(abi, "hp", nu);
        } else if ("THANOS".equals(impl)) {

            int cc = 0;
            for (Hero h : this.heroes) {
                // System.out.println(h.getHeroId()+"\t"+h.getSubtype());
                if (h.getSubtype().equals("501")) {
                    cc++;

                }
            }

            Unit nu = new Unit();
            nu.setUnit("%");
            // nu.setValue((((float) heroMastered) * 6f)*((float)cc/3f));
            nu.setValue(((float) heroMastered) * 6f);

            // System.out.println("CC: "+cc+", " + nu.getValue());

            Unit nu2 = new Unit();
            nu2.setUnit("%");
            // nu2.setValue(((float) heroMastered) * 5f*((float)cc/3f));
            nu2.setValue(((float) heroMastered) * 5f);

            abi.put("aatk", nu);
            abi.put("adef", nu);

            abi.put("cct", nu2);

        }
        else if("KATE_BISHOP".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            switch(heroMastered) {
            case 1:
            case 2:
            case 3:
            case 4:
                nu.setValue(3f+ ((float) heroMastered-1) * 2f);
                break;
            case 5:
                nu.setValue(12f);
                break;
            case 6:
                nu.setValue(15f);
                break;
            }
            abi.put("ctr", nu);
            
        }
        else if("ROBBIE".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(10f+ ((float) heroMastered-1) * 8f);
            
            abi.put("id", nu);
            
        }
        else if("SPIDER2099".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(10f+ ((float) heroMastered-1) * 8f);
            
            abi.put("id", nu);
            
        }
        else if("TITANIA".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(10f+ ((float) heroMastered-1) * 8f);
            
            abi.put("patk", nu);
            
        }
        else if("CABLE".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(10f+ ((float) heroMastered-1) * 8f);
            
            abi.put("id", nu);
            
        }
        else if("BLUE_MARVEL".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(10f+ ((float) heroMastered-1) * 8f);
            
            abi.put("adef", nu);
            
        }
        else if("ADAM_WARLOCK".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(35f+ ((float) heroMastered-1) * 5f);
            
            abi.put("edef", nu);
            
        }
        else if("KILLMONGER".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(25f+ ((float) heroMastered-1) * 5f);
            
            abi.put("pdef", nu);
            
        }
        else if("ULYSSES_KLAUE".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(3f+ ((float) heroMastered-1) * 2f);
            
            abi.put("ctr", nu);
            
        }
        else if("MAGIK".equals(impl)) {
            Unit nu = new Unit();
            nu.setUnit("%");
            nu.setValue(3f+ ((float) heroMastered-1) * 2f);
            
            abi.put("ctr", nu);
            
        }
    }

    public HashMap<String, Unit> getHeroDetail(int heroMastered, int heroOrder) {
        return getHeroDetail(heroMastered, heroes.get(heroOrder));

    }

    private void putAbility(HashMap<String, Unit> abi, String key, Unit unit) {
        Unit nu = new Unit();
        if (abi.containsKey(key)) {
            Unit u = abi.get(key);
            nu.setValue(u.getValue() + unit.getValue());
            nu.setUnit(u.getUnit());
        } else {
            nu.setValue(unit.getValue());
            nu.setUnit(unit.getUnit());
        }

        abi.put(key, nu);
    }

    public float averageHeroAttack(int heroMastered) {

        float sum = 0f;

        for (int i = 0, size = heroes.size(); i < size; i++) {
            HashMap<String, Unit> abi = getHeroDetail(heroMastered, i);
            Hero qhero = heroes.get(i);
            if ("p".equals(qhero.getAttackType()) && abi.containsKey("patk")) {
                sum += abi.get("patk").getValue();
            } else if ("e".equals(qhero.getAttackType()) && abi.containsKey("eatk")) {
                sum += abi.get("eatk").getValue();
            }
            
            if(abi.containsKey("aatk")) {
                sum += abi.get("aatk").getValue();
            }

        }

        return sum / ((float) heroes.size());
    }

    public String getId() {
        return id;
    }

    public HeroTeam setId(String id) {
        this.id = id;
        return this;
    }

}