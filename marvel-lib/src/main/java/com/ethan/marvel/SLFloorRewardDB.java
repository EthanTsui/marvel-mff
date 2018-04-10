/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2017年8月7日
 */
public class SLFloorRewardDB {
    private static SLFloorRewardDB instance = null;
    private static final Integer LOCK = new Integer(0);
    private List<SLFloorReward> floors = new ArrayList<SLFloorReward>();
    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalizationHelper.getInstance();
        for(SLFloorReward floor: SLFloorRewardDB.getInstance().getFloors()) {
            System.out.println("F-" + floor.getFloor());
            for(int i=0,size=floor.getReward1Items().size();i<size;i++) {
                System.out.println("\t"+
                        LocalizationHelper.getInstance().getText("zhtw", "ITEM_"+floor.getReward1Items().get(i).getItemid())+
            ": "+floor.getReward1Items().get(i).getValue()+", "
                        +LocalizationHelper.getInstance().getText("zhtw", "ITEM_"+floor.getReward2Items().get(i).getItemid())+": "+
            floor.getReward2Items().get(i).getValue());
            }
        }

    }

    public static SLFloorRewardDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new SLFloorRewardDB();
                }
            }
        }
        return instance;
    }
    
    private SLFloorRewardDB() {
        init();
    }
    
    public void init() {
        DataLoader slfloorloader = new DataLoader();
        slfloorloader.loadData(new File("/data/marvel/mffdata/SHADOWLAND_FLOOR.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        DataLoader slrewardloader = new DataLoader();
        slrewardloader.loadData(new File("/data/marvel/mffdata/SHADOWLAND_REWARD.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        while(slfloorloader.next()) {
            SLFloorReward floor = new SLFloorReward();
            floor.setFloor(Integer.parseInt(slfloorloader.getData("FLOOR_ID")));
            String reward = slfloorloader.getData("REWARD_GROUP");
            
            slrewardloader.first();
            while(slrewardloader.next()) {
                if(slrewardloader.getData("REWARD_GROUP").equals(reward)) {
                    String itemid1 = slrewardloader.getData("REWARD_VALUE_1");
                    if("0".equals(itemid1)) {
                        itemid1="1";
                    }
                    floor.addReward1(itemid1, Integer.parseInt(slrewardloader.getData("REWARD_QTY_1")));
                    
                    String itemid2 = slrewardloader.getData("REWARD_VALUE_2");
                    if("0".equals(itemid2)) {
                        itemid2="1";
                    }
                    floor.addReward2(itemid2, Integer.parseInt(slrewardloader.getData("REWARD_QTY_2")));
                    
                    
                }
            }
            floors.add(floor);
        }
        
        
        
        
    }

    /**
     * @return the floors
     */
    public List<SLFloorReward> getFloors() {
        return floors;
    }


    
    
}
