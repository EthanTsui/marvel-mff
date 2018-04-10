/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GBTW0011
 * created date: 2017年8月7日
 */
public class SLFloorReward {
    int floor=0;
    private List<RewardItem> reward1Items = new ArrayList<RewardItem>();
    private List<RewardItem> reward2Items = new ArrayList<RewardItem>();
    
    public class RewardItem {
        private String itemid;
        private int value;
        /**
         * @return the value
         */
        public int getValue() {
            return value;
        }
        /**
         * @param value the value to set
         */
        public void setValue(int value) {
            this.value = value;
        }
        /**
         * @return the itemid
         */
        public String getItemid() {
            return itemid;
        }
        /**
         * @param itemid the itemid to set
         */
        public void setItemid(String itemid) {
            this.itemid = itemid;
        }
        
        
    }

    /**
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return the rewardItems
     */
    public List<RewardItem> getReward1Items() {
        return reward1Items;
    }

    public List<RewardItem> getReward2Items() {
        return reward2Items;
    }
    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void addReward1(String itemid, int value) {
        RewardItem ri = new RewardItem();
        ri.setItemid(itemid);
        ri.setValue(value);
        reward1Items.add(ri);
    }
    
    public void addReward2(String itemid, int value) {
        RewardItem ri = new RewardItem();
        ri.setItemid(itemid);
        ri.setValue(value);
        reward2Items.add(ri);
    }
    
}
