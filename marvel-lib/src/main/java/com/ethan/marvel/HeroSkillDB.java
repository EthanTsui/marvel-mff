/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2017年5月15日
 */
public class HeroSkillDB {
    private static HeroSkillDB instance = null;
    private static final Integer LOCK = new Integer(0);

    List<HeroSkill> heroes = new ArrayList<HeroSkill>();
    private Map<String, List<HeroSkill>> heroesByType = new TreeMap<String, List<HeroSkill>>();
    
    
    /**
     * 
     */
    private HeroSkillDB() {
        loadHero();
    }

    private void sort() {
        for(int i=0;i<=3;i++) {
            List<HeroSkill> hks = heroesByType.get(""+i);
            
            Collections.sort(hks, new Comparator<HeroSkill>() {

                @Override
                public int compare(HeroSkill o1, HeroSkill o2) {
                    
                    // @@TODO
                    return 0;
                       
                }
                
            });
            
            
        }
    }
    
    public static HeroSkillDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new HeroSkillDB();
                }
            }
        }
        return instance;
    }
    
    public List<HeroSkill> getAll() {
        return heroes;
    }
    
    public List<HeroSkill> getHeroesByType(String herotype) {
        return heroesByType.get(herotype);
    }
    
    private void loadHero() {
        try {
            
            heroesByType.put("0", new ArrayList<HeroSkill>());
            heroesByType.put("1", new ArrayList<HeroSkill>());
            heroesByType.put("2", new ArrayList<HeroSkill>());
            heroesByType.put("3", new ArrayList<HeroSkill>());
            
            Map<String, String> hgroup = new TreeMap<String, String>();
            
            DataLoader herodl = new DataLoader();
            herodl.loadData(new File("/data/marvel/mffdata/HERO_LIST.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            DataLoader skilldl = new DataLoader();
            skilldl.loadData(new File("/data/marvel/mffdata/ACTION_ABILITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            String heroid = null;
            
            String lastCostume="";
            
            int position=0;
            
            while(herodl.next()) {
                if(!"1".equals(herodl.getData("VISIBLE"))) {
                    continue;
                }
                
                if("1".equals(herodl.getData("GRADE"))) {
                    heroid=herodl.getData("HERO_ID");
                }
                
                
                String thisCostume = herodl.getData("PREFAB");
                if(!lastCostume.equals(thisCostume)) {
                    position++;
                    lastCostume=thisCostume;
                }
                lastCostume=thisCostume;
                
                
                if("6".equals(herodl.getData("GRADE")) && "2".equals(herodl.getData("TIER"))) {
//                    if(hgroup.containsKey(herodl.getData("HERO_GROUP_ID"))) {
//                        continue;
//                    }
//                    hgroup.put(herodl.getData("HERO_GROUP_ID"), herodl.getData("HERO_GROUP_ID"));
//                    
                    
                    
                    
                    
                    
                    
                    HeroSkill heroSkill=new HeroSkill();
                    heroSkill.setMffHeroId(heroid);

                    heroSkill.setUniformId(herodl.getData("HERO_COSTUME_GROUP_ID"));

                    heroSkill.setHeroGroupId(herodl.getData("HERO_GROUP_ID"));
                    
                    heroSkill.setHeroType(herodl.getData("HERO_TYPE"));
                    
                    heroSkill.setImagePositionId(position);
                    
                    
//                    System.out.println("heroid: "+heroid);
                    for(int i=1;i<=7;i++) {
                        //System.out.println("for: "+herodl.getData("SKILL_ID_"+i)+", "+skilldl.getData(herodl.getData("SKILL_ID_"+i)+"1", "ABILITY_ID"));
                        
                        String skill = "";
                        for(int x=1;x<=10;x++) {
                            if(skilldl.getData(herodl.getData("SKILL_ID_"+i)+Integer.toString(x), "ABILITY_ID")!=null) {
                                if(x>=2) {
                                    skill+=",";
                                }
                                skill+=skilldl.getData(herodl.getData("SKILL_ID_"+i)+Integer.toString(x), "ABILITY_ID");
                            }
                        }
                        
                        heroSkill.setSkill(i, skill);
                    }
                    String skill="";
                    for(int x=1;x<=10;x++) {
                        if(skilldl.getData(herodl.getData("SKILL_ID_LEADER")+Integer.toString(x), "ABILITY_ID")!=null) {
                            if(x>=2) {
                                skill+=",";
                            }
                            skill+=skilldl.getData(herodl.getData("SKILL_ID_LEADER")+Integer.toString(x), "ABILITY_ID");
                        }
                    }
                    heroSkill.setSkill(0, skill);
                    
                    heroes.add(heroSkill);
                    
                    heroesByType.get(heroSkill.getHeroType()).add(heroSkill);
                }
                
                
                
            }
            
            
            
        }
        catch(Exception er) {
            
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        
        for(HeroSkill ks:HeroSkillDB.getInstance().getAll()) {
            if("0".equals(ks.getUniformId())) {
                System.out.print(ks.getImagePositionId()+", " + LocalizationHelper.getInstance().getText("zhtw", "HERO_"+ks.getMffHeroId())+"\t");
            
            }
            else {
                System.out.print(ks.getImagePositionId()+", " + LocalizationHelper.getInstance().getText("zhtw", "ITEM_"+ks.getUniformId())+"\t");
            }
            
            
            for(int i=0;i<=7;i++) {
                
//                if(!(i==0||i==4||i==7)) {
//                    continue;
//                }
                
                String skill = ks.getSkills().get(i);
                if(skill.contains(",")) {
                    String[] ss = skill.split(",");
                    for(int x=0,size=ss.length;x<size;x++) {
                        System.out.print(LocalizationHelper.getInstance().getText("zhtw", "ABILITY_"+ss[x])+",");
                        
                    }
                    System.out.print("\t");
                }
                else {
                    System.out.print(LocalizationHelper.getInstance().getText("zhtw", "ABILITY_"+ks.getSkills().get(i))+"\t");
                }
            }
            System.out.println();
        }

    }

}
