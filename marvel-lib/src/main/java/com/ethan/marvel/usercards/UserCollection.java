package com.ethan.marvel.usercards;

import java.util.*;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/11.
 */
public class UserCollection {

    public static final String SKILL_ID[] = {"9", "26", "25", "9_26", "9_25", "10", "27", "28",
            "10_27", "10_28", "4", "5", "103", "32", "36", "34", "35", "33", "11", "29",
            "6", "7", "19", "8", "20"};

    protected String tokenId;

    protected String collectionUId;

    protected UserCard[] slots = new UserCard[5];

    protected Map<String, Float> skills = new TreeMap<String, Float>();

    protected List<String> triggerBasedSkills = new ArrayList<String>();

    protected Date insertSt;

    protected Date updateSt;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCollectionUId() {
        return collectionUId;
    }

    public void setCollectionUId(String collectionUId) {
        this.collectionUId = collectionUId;
    }

    public UserCard[] getSlots() {
        return slots;
    }

    public UserCard getSlotCard(int slotId) {
        return slots[slotId-1];
    }

    public UserCollection setSlotCard(int slotId, UserCard usercard) {
        slots[slotId-1]=usercard;
        return this;
    }

    public Map<String, Float> getSkills() {
        return skills;
    }

    public UserCollection setSkill(String skillId, float value) {
        skills.put(skillId, value);
        return this;
    }

    public float getSkill(String skillId) {
        if(skills.containsKey(skillId)) {
            return skills.get(skillId);
        }
        return 0.0f;
    }

    public Date getInsertSt() {
        return insertSt;
    }

    public void setInsertSt(Date insertSt) {
        this.insertSt = insertSt;
    }

    public Date getUpdateSt() {
        return updateSt;
    }

    public void setUpdateSt(Date updateSt) {
        this.updateSt = updateSt;
    }


    public List<String> getTriggerBasedSkills() {
        return triggerBasedSkills;
    }

    public void setTriggerBasedSkills(List<String> triggerBasedSkills) {
        this.triggerBasedSkills = triggerBasedSkills;
    }



    public void reCalculateSkills() {
        skills.clear();
        triggerBasedSkills.clear();

        for(UserCard usercard:slots) {
            if(usercard==null) {
                continue;
            }

            for(int i=1;i<=6;i++) {
                String skillId = usercard.getOptions(i);
                if(skillId.startsWith("0:")) {
                    triggerBasedSkills.add(skillId);
                    continue;
                }
                float value=0.0f;
                if(skills.containsKey(skillId)) {
                    value=skills.get(skillId);
                }

                value+=usercard.getOptionsRatio(i);

                skills.put(skillId, value);

            }

        }

        skills.put("9_25", getSkill("9")+getSkill("25"));
        skills.put("9_26", getSkill("9")+getSkill("26"));

        skills.put("10_27", getSkill("10")+getSkill("27"));
        skills.put("10_28", getSkill("10")+getSkill("28"));


    }



}
