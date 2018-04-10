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

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011 created date: 2015年5月21日
 */
public class HeroDB {
    private static HeroDB instance = null;
    private static final Integer LOCK = new Integer(0);

    List<Hero> heros = new ArrayList<Hero>();

    /**
     * 
     */
    private HeroDB() {
        loadHero();
    }

    public static HeroDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new HeroDB();
                }
            }
        }
        return instance;
    }

    private void loadHero() {
        try {

            
//            BufferedReader br = new BufferedReader(new FileReader("/data/marvel/hero.ability.txt"));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                line = line.trim();
//                if (line.length() < 3 || line.startsWith("#")) {
//                    continue;
//                }
//                
//
//            }

         // load hero abilities
            (new MyFileReader()).readLine(new File("/data/marvel/hero.ability.txt"), new ILineProcessor() {

                @Override
                public void processLine(String line) {
                    String[] s1 = line.split(":");
                    int id = Integer.parseInt(s1[0]);
                    Hero hero = new Hero();
                    hero.setHeroId(id);
                    heros.add(hero);
                    
//                    System.out.println("id: "+id);
                    
                    StringTokenizer st = new StringTokenizer(s1[1], ",");
                    while (st.hasMoreTokens()) {
                        String[] s2 = st.nextToken().split("=");
                        String aName = s2[0];
                        String aValue = s2[1];
//                        System.out.println("name: "+aName+"\t"+aValue);
                        
                        if (AbilityHelper.getInstance().isUnitType(aName)) {
//                            System.out.println("unit type: "+aName);
                            
                            Unit unit = AbilityHelper.getInstance().parseToUnit(aValue);
                            hero.putAbility(aName, unit);
                        } else {
                            hero.putAbility(aName, aValue);
                        }

                    }
                    
                }
                
            });
            
            // load hero and his strikers
            (new MyFileReader()).readLine(new File("/data/marvel/hero.striker.list.txt"), new ILineProcessor() {
                @Override
                public void processLine(String line) {
                    StringTokenizer st = new StringTokenizer(line, ",");
                    
                    int id = Integer.parseInt(st.nextToken());
//                    System.out.println("id: "+id);
                    Hero hero = lookupHero(id);
                    
                    while (st.hasMoreTokens()) {
                        String token=st.nextToken();
//                        System.out.println(token);
                        String[] s2 =token.split("=");
//                        System.out.println(s2[0]);
                        Hero h = lookupHero(Integer.parseInt(s2[0]));
                        String[] s3=s2[1].split(":");
                        
                        float prob = Float.parseFloat(s3[0]) / 100f;
                        String autotype=s3[1];
                        int cooltime=Integer.parseInt(s3[2]);
                        Striker striker=new Striker(h, prob);
                        striker.setAutoType(autotype);
                        striker.setCoolTime(cooltime);
                        
                        hero.addHelper(striker);

                    }
                }
            });
            
 
            
            // load hero information
            DataLoader heromaploader = new DataLoader();
            heromaploader.loadData(new File("/data/marvel/heroidmapping.txt"), "UTF-8", "\t", 0);
           
            DataLoader heroloader = new DataLoader();
            heroloader.loadData(new File("/data/marvel/mffdata/HERO_LIST.csv.txt.decode.txt"), "UTF-8", "\t", 0);
           
            System.out.println("cc: "+heromaploader.size());
            heromaploader.first();
            while(heromaploader.next()) {
                String hgroupid=heromaploader.getData("HERO_GROUP_ID");
                
                System.out.println("hg: "+hgroupid+"\theroid: "+heromaploader.getData("HERO_ID"));
                
                heroloader.first();
                while(heroloader.next()) {
                    if(hgroupid.equals(heroloader.getData("HERO_GROUP_ID"))) {
                        String herotype=heroloader.getData("CHARACTER");
                        this.lookupHero(Integer.parseInt(heromaploader.getData("HERO_ID"))).setSubtype(herotype);
                        
                        System.out.println(heromaploader.getData("HERO_ID")+"\t"+herotype);
                        break;
                    }
                }
                

            }
            
            
            // print data

//            for (Hero h : heros) {
//                if (h.isHeroType("u")) {
//                    System.out.println(h.printData());
//                }
//            }

        } catch (Exception err) {

        }
    }

    public Hero lookupHero(int heroId) {
        return heros.get(heroId - 1);
    }

    public List<Hero> getHeros() {

        return heros;
    }

    public HeroDB setHeros(List<Hero> heros) {
        this.heros = heros;
        return this;
    }

    public static void main(String[] args) {
        test();

    }
    
    public static void test() {
        List<Hero> heroes = HeroDB.getInstance().getHeros();
        
        for(Hero h:heroes) {
//            System.out.println(h.getHeroId()+", "+h.getHelpers().size());
            if(h.getHelpers().containsKey(17)) {
                System.out.println(LanguageHelper.getInstance().getHeroName("zhtw",h.getHeroId()));
            }
        }
        
//        List<Integer> heroList = new ArrayList<Integer>();
//        heroList.add(2);
//        heroList.add(13);
//        heroList.add(30);
//        heroList.add(31);
//        heroList.add(34);
//        heroList.add(1);
//        heroList.add(3);
//        heroList.add(37);
//        heroList.add(9);
//        
//        
//        HeroTeam must = new HeroTeam();
//        must.addHero(HeroDB.getInstance().lookupHero(2));
//        List<HeroTeam> teams = new Helper().list3(heroList, must);
//        for (HeroTeam team : teams) {
//
//            System.out.println(team.getNameList(", "));
//            System.out.println(team.getLeaderAbilityString(5));
//        }
    }

    public List<Hero> listByHeroType(String t) {
        List<Hero> heroes = new ArrayList<Hero>();
        for(Hero h:heros) {
//            if(h.isHeroType(t)) {
            if(t.equals(h.getHeroType())) {
                heroes.add(h);
            }
        }
        return heroes;
    }
    
    public List<Hero> listHeroByAttackType(String type) {
        List<Hero> heroes = new ArrayList<Hero>();
        for(Hero h:heros) {
//            if(h.isHeroType(t)) {
            if(type.equals(h.getAttackType())) {
                heroes.add(h);
            }
        }
        return heroes;
    }
    
    
    
}
