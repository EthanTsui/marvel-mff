/**
 * 
 */
package com.ethan.marvel;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author GBTW0011
 * created date: 2017年5月15日
 */
public class HeroSkill {
    String mffHeroId;
    String uniformId;
    String heroGroupId;
    String heroType;
    int imagePositionId;
    
    Map<Integer, String> skills = new TreeMap<Integer, String>();
    /**
     * @return the mffHeroId
     */
    public String getMffHeroId() {
        return mffHeroId;
    }
    /**
     * @return the skills
     */
    public Map<Integer, String> getSkills() {
        return skills;
    }
    /**
     * @param mffHeroId the mffHeroId to set
     */
    public void setMffHeroId(String mffHeroId) {
        this.mffHeroId = mffHeroId;
    }
    
    public void setSkill(Integer skillLevel, String skillId) {
        skills.put(skillLevel, skillId);
    }
    /**
     * @return the uniformId
     */
    public String getUniformId() {
        return uniformId;
    }
    /**
     * @param uniformId the uniformId to set
     */
    public void setUniformId(String uniformId) {
        this.uniformId = uniformId;
    }
    /**
     * @return the heroGroupId
     */
    public String getHeroGroupId() {
        return heroGroupId;
    }
    /**
     * @param heroGroupId the heroGroupId to set
     */
    public void setHeroGroupId(String heroGroupId) {
        this.heroGroupId = heroGroupId;
    }
    /**
     * @return the imagePositionId
     */
    public int getImagePositionId() {
        return imagePositionId;
    }
    /**
     * @param imagePositionId the imagePositionId to set
     */
    public void setImagePositionId(int imagePositionId) {
        this.imagePositionId = imagePositionId;
    }
    /**
     * @return the heroType
     */
    public String getHeroType() {
        return heroType;
    }
    /**
     * @param heroType the heroType to set
     */
    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }
    

}
