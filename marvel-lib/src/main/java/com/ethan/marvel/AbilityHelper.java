/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author GBTW0011
 * created date: 2015年7月2日
 */
public class AbilityHelper {
    private static AbilityHelper instance = null;
    private static final Integer LOCK = new Integer(0);
    List<String> abilityList = new ArrayList<String>();
    HashMap<String, String> abilityMap = new HashMap<String, String>();
    /**
     * 
     */
    private AbilityHelper() {
        init();
    }
    
    public static AbilityHelper getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new AbilityHelper();
                }
            }
        }
        return instance;
    }
    
    private void init() {
        abilityList.add("hp");
        abilityList.add("patk");
        abilityList.add("eatk");
        abilityList.add("aatk");
        abilityList.add("pdef");
        abilityList.add("edef");
        abilityList.add("adef");
        abilityList.add("do");
        abilityList.add("fr");
        abilityList.add("lr");
        abilityList.add("mr");
        abilityList.add("pr");
        abilityList.add("ctr");
        abilityList.add("ctd");
        abilityList.add("rad");
        abilityList.add("sct");
        abilityList.add("id");
        abilityList.add("pdi");
        abilityList.add("cct");
        abilityList.add("id");
        abilityList.add("dp");
        abilityList.add("rcr");
        abilityList.add("ct");
        abilityList.add("alls");
        abilityList.add("as");
        abilityList.add("ms");
        abilityList.add("par");
        abilityList.add("ifd");
        abilityList.add("dmg");
        abilityList.add("esld");
        abilityList.add("ild");
        abilityList.add("icd");
        abilityList.add("iade");
        abilityList.add("ddd");
        abilityList.add("iad");
        
        for(String s:abilityList) {
            abilityMap.put(s, s);
        }
    }
    
    
    public boolean isUnitType(String s) {
        return abilityMap.containsKey(s);
    }
    
    public Unit parseToUnit(String s) {
        Unit unit = new Unit();
        int idx=-1;
        for(int i=0,size=s.length();i<size;i++) {
            if(s.charAt(i)=='-' || s.charAt(i)=='.' || (s.charAt(i)>='0' && s.charAt(i)<='9')) {
                idx++;
            }
        }
        
        unit.setValue(Float.parseFloat(s.substring(0,idx+1)));
        unit.setUnit(s.substring(idx+1));
        
        
        
        return unit;
    }
    
    
}
