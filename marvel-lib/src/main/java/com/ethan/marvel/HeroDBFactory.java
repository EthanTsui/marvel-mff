/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 * @author GBTW0011 created date: 2015年5月22日
 */
public class HeroDBFactory {
    private static HeroDBFactory instance = null;
    private static final Integer LOCK = new Integer(0);
    private HashMap<String, HeroDB> heroDBMap = new HashMap<String, HeroDB>();
    
    
    

    /**
     * 
     */
    private HeroDBFactory() {
        init();
    }

    public static HeroDBFactory getInstance3() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new HeroDBFactory();
                }
            }
        }
        return instance;
    }

    public HeroDB getHeroDB(String language) {
        if(heroDBMap.containsKey(language)) {
            return heroDBMap.get(language);
        }
        else {
            return heroDBMap.get("en");
        }
    }
    
    public HashMap<String, HeroDB> getHeroDBMap() {
        return heroDBMap;
    }

    private void init() {
        // "/data/marvel/hero.list.txt"
//        
//        File heroDir = new File("/data/marvel/herolist/");
//        File[] heroLists = heroDir.listFiles(new FilenameFilter() {
//
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.toLowerCase().endsWith(".txt");
//            }
//            
//        });
//        
//        
//        for(File herolist: heroLists) {
//            HeroDB heroDB = new HeroDB();
//            heroDB.loadHero(herolist);
////            System.out.println(herolist.getName()+"\t"+heroDB.getHeros().size()+"t"+herolist.getName().substring(0,herolist.getName().indexOf('.')));
//            heroDBMap.put(herolist.getName().substring(0,herolist.getName().indexOf('.')), heroDB);
//            
//        }
//        
        
    }
    
    
    public synchronized void reloadDB() {
        heroDBMap.clear();
        init();
    }
    
}
