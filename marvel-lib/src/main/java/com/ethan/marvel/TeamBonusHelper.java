/**
 * 
 */
package com.ethan.marvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author GBTW0011 created date: 2015年7月3日
 */
public class TeamBonusHelper {
    private static TeamBonusHelper instance = null;
    private static final Integer LOCK = new Integer(0);
    List<HeroTeam> teamBonus = new ArrayList<HeroTeam>();
    /**
     * 
     */
    private TeamBonusHelper() {
        init();
    }

    public static TeamBonusHelper getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new TeamBonusHelper();
                }
            }
        }
        return instance;
    }

    private void init() {
        try {
            (new MyFileReader()).readLine(new File("/data/marvel/team.bonus.txt"), new ILineProcessor() {
                @Override
                public void processLine(String line) {
                    StringTokenizer st = new StringTokenizer(line, ",");
                    HeroTeam team = null;
                    while (st.hasMoreTokens()) {
                        String v = st.nextToken();
                        if(v.startsWith("id=")) {
                            team = new HeroTeam();
                            team.setId(v.substring(3));
                            teamBonus.add(team);
                        }
                        else if (v.startsWith("h=")) {
                            
                            String[] str = v.substring(2).split(":");
                            for (String s : str) {
                                Hero hero = HeroDB.getInstance().lookupHero(Integer.parseInt(s));
                                team.addHero(hero);
                            }

                            

                        } else {
                            String[] str = v.split("=");

                            Unit unit = AbilityHelper.getInstance().parseToUnit(str[1]);
                            team.putAbility(str[0], unit);
                        }

                    }
                }
            });
            
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void determineTeamBonus(HeroTeam team) {
        for (HeroTeam hTeam : teamBonus) {
            if(hTeam.getOverlap(team)==hTeam.getHeros().size()) {
                for(String key:hTeam.getAbilities().keySet()) {
                    Unit unit = hTeam.getAbilities().get(key);
                    
                    team.addAbility(key, unit);
                }
            }
        }
    }
    
    public List<HeroTeam> queryHeroBelongTo(int heroId) {
        List<HeroTeam> result = new ArrayList<HeroTeam>();
        
        for(HeroTeam team:teamBonus) {
            for(Hero h:team.getHeros()) {
                if(h.getHeroId()==heroId) {
                    result.add(team);
                    break;
                }
            }
        }
        return result;
    }
    
    
    public static void main(String[]  args) {
        TeamBonusHelper.getInstance();
        
        
        
    }

}
