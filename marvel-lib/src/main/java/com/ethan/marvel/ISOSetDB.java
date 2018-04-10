/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011 created date: 2015年7月27日
 */
public class ISOSetDB {
    private static ISOSetDB instance = null;
    private static final Integer LOCK = new Integer(0);
    private HashMap<Integer, ISOSet> isosetMap;

    /**
     * 
     */
    private ISOSetDB() {
        init();
    }

    public static ISOSetDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new ISOSetDB();
                }
            }
        }
        return instance;
    }

    public void init() {
        isosetMap = new HashMap<Integer, ISOSet>();
        loadData();
    }
    
    public List<ISOSet> getList() {
        List<ISOSet> list = new ArrayList<ISOSet>(isosetMap.values());
        Collections.sort(list, new Comparator<ISOSet>() {

            @Override
            public int compare(ISOSet o1, ISOSet o2) {
                return (o2.getIsoList().size()-o1.getIsoList().size())*1000+(o1.getISOSetId()-o2.getISOSetId());
            }
            
        });
        
        
        return list;
    }
    
    public void loadData() {
        try {
            DataLoader abilitymaploader = new DataLoader();
            abilitymaploader.loadData(new File("/data/marvel/abilitymapping.txt"), "UTF-8", "\t", 0);
            
            DataLoader isosetData = new DataLoader();
            isosetData.loadData(new File("/data/marvel/mffdata/ISO8_SET.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            
            DataLoader isosetbuffData = new DataLoader();
            isosetbuffData.loadData(new File("/data/marvel/mffdata/ISO8_SET_BUFF.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            DataLoader abilityGroupData = new DataLoader();
            abilityGroupData.loadData(new File("/data/marvel/mffdata/ACTION_ABILITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            
            
            while(isosetData.next()) {
                ISOSet set = new ISOSet();
                String isoId = isosetData.getData("SET_ID");
                set.setISOSetId(Integer.parseInt(isoId));
                for(int i=1;i<=8;i++) {
                    String s = isosetData.getData("SET_"+i);
                    if(s.equals("0")) {
                        break;
                    }
                    
                    set.addISO(Integer.parseInt(s.substring(s.length()-1)));
                }
                isosetMap.put(set.getISOSetId(), set);
                
            }
            
            int counter=0;
            while(isosetbuffData.next()) {
                counter++;
                if(counter==6) {
                    String buffId = isosetbuffData.getData("BUFF_ID");
                    ISOSet set = isosetMap.get(Integer.parseInt(buffId));
                    set.setAutoType(isosetbuffData.getData("AUTO_ABILITY_ID"));
                    set.setAutoAbilityRate(Float.parseFloat(isosetbuffData.getData("AUTO_ABILITY_RATE"))/100f);
                    set.setCoolTime(Float.parseFloat(isosetbuffData.getData("COOLTIME")));
                    for(int i=1;i<=5;i++) {
                        String s = isosetbuffData.getData("ABILITY_TYPE_"+i);
                        if("0".equals(s)) {
                            break;
                        }
                        
                        String abi = abilitymaploader.getData(s, "abbr");
                        Unit u= new Unit();
                        u.setUnit("%");
                        u.setValue(Float.parseFloat(isosetbuffData.getData("ABILITY_VALUE_"+i))/100f);
                        set.addAbility(abi, u);
                        
                        
                    }
                    
                    String abilityGroupId = isosetbuffData.getData("ABILITY_GROUP_ID");
//                    System.out.println("agid: "+abilityGroupId);
                    String abiid=abilityGroupData.getData(abilityGroupId, "ABILITY_ID");
                    Unit u = new Unit();
                    set.setConditionAbilityId(abiid);
                    set.setConditionTime(Float.parseFloat(abilityGroupData.getData(abilityGroupId, "TIME"))/1000f);
                    u.setUnit("%");
                    u.setValue(Float.parseFloat(abilityGroupData.getData(abilityGroupId, "ACTION_ABILITY_PARAM1"))/100f);
                    set.setConditionUnit(u);
                    
//                    System.out.println(abilityGroupId+", "+abiid+", "+u.getData()+", "+set.getConditionTime());
                    
                    counter=0;
                }
            }
            
            
//            for(ISOSet set:getList()) {
//                System.out.print(LocalizationHelper.getInstance().getText("en", "ISO_SET_NAME_"+set.getISOSetId())+"\t");
//                for(int s:set.getIsoList()) {
//                    System.out.print(s+", ");
//                }
//                System.out.println("\t"+set.getAutoType()+"\t"+set.getAutoAbilityRate());
//                
//                for(String abi:set.getAbilities().keySet()) {
//                    System.out.println(abi+": "+set.getAbilities().get(abi).getData());
//                }
//                
//                
//            }
            
            
            
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        ISOSetDB.getInstance();
    }
}
