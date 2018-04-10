/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author GBTW0011 created date: 2016年7月15日
 */
public class Card {
    int cardId;
    String cardItemId;
    String obtainStageId=null;
    String obtainStageType=null;
    
    HashMap<Integer, List<CardAbility>> abilityLevels;
    
    HashMap<Integer, List<String>> pickList=new HashMap<Integer, List<String>>();
    
    
    
    public Card(int cardId) {
        this.cardId = cardId;
        abilityLevels = new HashMap<Integer, List<CardAbility>>();
    }

    public void setAbilityLevel(int level, List<CardAbility> cardList) {
        abilityLevels.put(level, cardList);
    }

    public List<CardAbility> getLevelAbility(int level) {
        return abilityLevels.get(level);
    }

    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     *            the cardId to set
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * @return the cardItemId
     */
    public String getCardItemId() {
        return cardItemId;
    }

    /**
     * @param cardItemId
     *            the cardItemId to set
     */
    public void setCardItemId(String cardItemId) {
        this.cardItemId = cardItemId;
    }

    public boolean containsAbilityId(String[] abilities, int underLevel) {

        for (int i = 1; i <= underLevel; i++) {
            List<CardAbility> ablist = abilityLevels.get(i);

            for (String abicheck : abilities) {
                for (CardAbility cabi : ablist) {
                    
                    if (!cabi.getAbilityId().equals("0")) {
                        if (cabi.getAbilityId().equals(abicheck)) {
                            return true;
                        }
                    } else {
                        if (cabi.getAutoAbilityId().equals(abicheck)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public List<String> getAllAbilityId() {
        HashMap<String, String> all = new HashMap<String, String>();
        for (List<CardAbility> ablist : abilityLevels.values()) {
            for (CardAbility cabi : ablist) {
                if (!cabi.getAbilityId().equals("0")) {
                    all.put(cabi.getAbilityId(), cabi.getAbilityId());
                } else {
                    all.put(cabi.getAutoAbilityId(), cabi.getAutoAbilityId());
                }
            }
        }

        return new ArrayList<String>(all.keySet());

    }

    /**
     * @return the obtainStageId
     */
    public String getObtainStageId() {
        return obtainStageId;
    }

    /**
     * @return the obtainStageType
     */
    public String getObtainStageType() {
        return obtainStageType;
    }

    /**
     * @param obtainStageId the obtainStageId to set
     */
    public void setObtainStageId(String obtainStageId) {
        this.obtainStageId = obtainStageId;
    }
    /**
     * @param obtainStageType the obtainStageType to set
     */
    public void setObtainStageType(String obtainStageType) {
        this.obtainStageType = obtainStageType;
    }

    /**
     * @return the pickList
     */
    public HashMap<Integer, List<String>> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(HashMap<Integer, List<String>> pickList) {
        this.pickList = pickList;
    }

}
