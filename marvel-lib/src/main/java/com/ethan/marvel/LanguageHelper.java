/**
 * 
 */
package com.ethan.marvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author GBTW0011 created date: 2015年7月2日
 */
public class LanguageHelper {
    private static LanguageHelper instance = null;
    private static final Integer LOCK = new Integer(0);
    private HashMap<String, HashMap<Integer, String>> heroNameMap = new HashMap<String, HashMap<Integer, String>>();
    private HashMap<String, HashMap<String, String>> abilityMap = new HashMap<String, HashMap<String, String>>();
    private HashMap<String, HashMap<String, String>> interfaceMap = new HashMap<String, HashMap<String, String>>();
    private String language = "en";
    /**
     * 
     */
    private LanguageHelper() {
        init();
    }

    public static LanguageHelper getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LanguageHelper();
                }
            }
        }
        return instance;
    }

    private void init() {
        try {
            loadHeroName();
            
            loadAbility();
            
            loadInterface();
            
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void loadHeroName() throws FileNotFoundException, IOException {
        // load hero
        File heroDir = new File("/data/marvel/herolist/");
        File[] heroLists = heroDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }

        });

        for (File herolist : heroLists) {
            String lang=herolist.getName().substring(0,herolist.getName().indexOf("."));
            
            HashMap<Integer, String> heroName = new HashMap<Integer, String>();
            
            BufferedReader br = new BufferedReader(new FileReader(herolist));
            String line = null;

            // load hero name
            while ((line = br.readLine()) != null) {
                line=line.trim();
                if (line.length() < 2 || line.startsWith("#")) {
                    continue;
                }

                String[] str = line.split("=");
                int heroid=Integer.parseInt(str[0]);
                heroName.put(heroid, str[1]);

            }
            br.close();
            
            heroNameMap.put(lang, heroName);

        }
    }
    
    public void loadAbility() throws FileNotFoundException, IOException {
        // load ability
        File abilityDir = new File("/data/marvel/abilities/");
        File[] abilityLists = abilityDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }

        });

        for (File abilitylist : abilityLists) {
            String lang=abilitylist.getName().substring(0,abilitylist.getName().indexOf("."));
            
            HashMap<String, String> AbilityName = new HashMap<String, String>();
            
            BufferedReader br = new BufferedReader(new FileReader(abilitylist));
            String line = null;

            // load ability name
            while ((line = br.readLine()) != null) {
                line=line.trim();
                if (line.length() < 2 || line.startsWith("#")) {
                    continue;
                }

                String[] str = line.split("=");
               
                AbilityName.put(str[0], str[1]);

            }
            br.close();
            
            abilityMap.put(lang, AbilityName);

        }
    }
    
    public void loadInterface() throws FileNotFoundException, IOException {
        // load ability
        File interfaceDir = new File("/data/marvel/interfaces/");
        File[] interfaceLists = interfaceDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }

        });

        for (File interfacelist : interfaceLists) {
            String lang=interfacelist.getName().substring(0,interfacelist.getName().indexOf("."));
            
            HashMap<String, String> interfaceName = new HashMap<String, String>();
            
            BufferedReader br = new BufferedReader(new FileReader(interfacelist));
            String line = null;

            // load interface translation
            while ((line = br.readLine()) != null) {
                line=line.trim();
                if (line.length() < 3 || line.startsWith("#")) {
                    continue;
                }

                int idx = line.indexOf('=');
                String key=line.substring(0,idx);
                String value=line.substring(idx+1);
               
                interfaceName.put(key,value);

            }
            br.close();
            
            interfaceMap.put(lang, interfaceName);

        }
    }
    

    public String getHeroName(String language, int heroId) {
        if(!heroNameMap.containsKey(language)) {
            language="en";
        }
        
        return heroNameMap.get(language).get(heroId);

    }
    
    public String getHeroName(int heroId) {
        return heroNameMap.get(getLanguage()).get(heroId);
    }
    
    
    public String getAbilityName(String language, String ability) {
        if(!abilityMap.containsKey(language)) {
            language="en";
        }
        
        return abilityMap.get(language).get(ability);
    }

    public String getAbilityName(String ability) {
        return abilityMap.get(getLanguage()).get(ability);
    }
    
    public String getLanguage() {
        return language;
    }

    public LanguageHelper setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getInterfaceName(String language, String interfaceKey) {
        if(!interfaceMap.containsKey(language)) {
            language="en";
        }
        if(!language.equals("en") && !interfaceMap.get(language).containsKey(interfaceKey)) {
            return interfaceMap.get("en").get(interfaceKey);
        }
        return interfaceMap.get(language).get(interfaceKey);
    }
}
