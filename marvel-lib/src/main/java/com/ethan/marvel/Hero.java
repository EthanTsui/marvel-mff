/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.ethan.marvel.sorter.AbilitySorter;
import com.ethan.marvel.sorter.MustHeroAttackSorter;

/**
 * @author GBTW0011 created date: 2015年5月21日
 */
public class Hero {
    int heroId = 0;
    HashMap<Integer, Striker> strikers = new HashMap<Integer, Striker>();
    HashMap<String, Object> abilities = new HashMap<String, Object>();
    HashMap<String, List<HeroTeam>> teamupSuggestion = new HashMap<String, List<HeroTeam>>();
    String subtype="500";

    public Hero putAbility(String key, Object obj) {
        abilities.put(key, obj);
        return this;
    }
    
    public Hero addAbility(String key, Object obj) {
        if(abilities.containsKey(key)) {
            Unit nu = (Unit) obj;
            
            Unit nu2 = (Unit) abilities.get(key);
            
            nu2.setValue(nu2.getValue()+nu.getValue());
            
            
            
        }
        abilities.put(key, obj);
        return this;
    }
    

    public Object getAbility(String key) {
        return abilities.get(key);
    }

    public boolean isHeroType(String type) {
        return abilities.get("t").equals(type) || (abilities.containsKey("tu") && abilities.get("tu").equals(type));
    }

    public String getHeroType() {
        return (String) abilities.get("t");
    }

    public String getAttackType() {
        return (String) abilities.get("a");
    }

    public String getScope() {
        return (String) abilities.get("s");
    }

    public Hero addHelper(Striker helper) {
        strikers.put(helper.getHero().getHeroId(), helper);
        return this;
    }

    public int getHeroId() {
        return heroId;
    }

    public Hero setHeroId(int heroId) {
        this.heroId = heroId;
        return this;
    }

    public HashMap<Integer, Striker> getHelpers() {
        return strikers;
    }

    public Hero setHelpers(HashMap<Integer, Striker> helpers) {
        this.strikers = helpers;
        return this;
    }

    public Striker getHelper(int helperId) {
        return strikers.get(helperId);
    }

    public boolean containsHelper(int helperId) {
        return strikers.containsKey(helperId);
    }

    public float getAverageHelperPercentage() {
        float sum = 0f;
        int counter = 0;
        for (Striker helper : strikers.values()) {
            sum += helper.getProb();
            counter++;
        }

        return sum / (float) counter;

    }

    public int getHelperSize() {
        return strikers.size();
    }

    public String printData() {
        StringBuilder sb = new StringBuilder(60);
        sb.append(Integer.toString(heroId));
        for (String key : abilities.keySet()) {
            sb.append(", ");
            sb.append(key);
            sb.append(":");
            sb.append(abilities.get(key).toString());
        }

        return sb.toString();
    }

    public String getAbilityString(String language) {
        StringBuilder sb = new StringBuilder(120);
        for (String key : abilities.keySet()) {
            if (!AbilityHelper.getInstance().isUnitType(key)) {
                continue;
            }
            sb.append(LanguageHelper.getInstance().getAbilityName(language, key));
            sb.append(": ");
            sb.append(abilities.get(key).toString());
            sb.append(", ");
        }
        if (getAbilities().containsKey("c")) {
            sb.append(getAbility("c"));
        }
        else if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 2);
        }
        return sb.toString();
    }

    public HashMap<String, Object> getAbilities() {
        return abilities;
    }

    public Hero setAbilities(HashMap<String, Object> abilities) {
        this.abilities = abilities;
        return this;
    }

    public List<Hero> getHelpWhos() {
        List<Hero> who = new ArrayList<Hero>();
        List<Hero> allheroes = HeroDB.getInstance().getHeros();
        for (Hero h : allheroes) {
            if (h.getHelpers().containsKey(this.getHeroId())) {
                who.add(h);
            }
        }

        return who;
    }

    public float getAverageRatioOfBeingHelper() {
        List<Hero> who = getHelpWhos();
        float sum = 0f;
        for (Hero h : who) {
            Striker s = h.getHelper(this.getHeroId());
            sum += s.getProb();
        }

        return sum / (float) who.size();

    }

    public List<HeroTeam> getTeamupSuggestion(String abilityKey) {
        if (!teamupSuggestion.containsKey(abilityKey)) {
            HeroTeam must = new HeroTeam();
            must.addHero(HeroDB.getInstance().lookupHero(getHeroId()));
            List<HeroTeam> teamup = TeamUpHelper.getInstance().teamUpSuggest(must);

            Comparator<HeroTeam> sorter = new MustHeroAttackSorter(6, must);

            if ("atk".equals(abilityKey)) {
                sorter = new MustHeroAttackSorter(6, must);
            } else { // default
                sorter = new AbilitySorter(abilityKey, 6, must);
            }

            Collections.sort(teamup, sorter);

            List<HeroTeam> teamupResult = new ArrayList<HeroTeam>();

            int count = 0;
            for (HeroTeam team : teamup) {
                if (team.getAbilities().size() == 0) {
                    continue;

                }
                teamupResult.add(team);
                count++;
                if (count >= 40) {
                    break;
                }
            }
            teamupSuggestion.put(abilityKey, teamupResult);

        }

        return teamupSuggestion.get(abilityKey);
    }

    /**
     * @return the subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @param subtype the subtype to set
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

}