/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.HashMap;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2016年7月18日
 */
public class AbilityMappingDB {
    private static AbilityMappingDB instance = null;
    private static final Integer LOCK = new Integer(0);
    private HashMap<String, String> abbrToAbilityId = new HashMap<String, String>();
    private HashMap<String, String> abilityIdToAbbr = new HashMap<String, String>();
    private AbilityMappingDB() {
        init();
    }
    
    public static AbilityMappingDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new AbilityMappingDB();
                }
            }
        }
        return instance;
    }
    
    private void init() {
        DataLoader abimap = new DataLoader();
        abimap.loadData(new File("/data/marvel/abilitymapping.txt"), "UTF-8", "\t", 0);
        while(abimap.next()) {
            String abiid = abimap.getData("Ability_id");
            String abbr = abimap.getData("abbr");
            abbrToAbilityId.put(abbr, abiid);
            abilityIdToAbbr.put(abiid,abbr);
        }
        
        

    }
    
    
    public String abbrByAbilityId(String abiid) {
        return abilityIdToAbbr.get(abiid);
    }
    
    public String abilityIdByAbbr(String abbr) {
        return abbrToAbilityId.get(abbr);
    }
}
