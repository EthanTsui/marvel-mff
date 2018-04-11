/**
 * 
 */
package com.ethan.marvel;

/**
 * @author GBTW0011
 * created date: 2016年7月15日
 */
public class CardAbility {
    String abilityId;
    String abilityDescId;
    String autoAbilityRate;
    String autoAbilityId;
    String autoValue;
    String autoTime;
    
    public CardAbility() {
        
    }

    /**
     * @return the abilityId
     */
    public String getAbilityId() {
        return abilityId;
    }

    /**
     * @return the abilityDescId
     */
    public String getAbilityDescId() {
        return abilityDescId;
    }

    /**
     * @return the autoAbilityRate
     */
    public String getAutoAbilityRate() {
        return autoAbilityRate;
    }

    /**
     * @return the autoAbilityId
     */
    public String getAutoAbilityId() {
        return autoAbilityId;
    }

    /**
     * @return the autoValue
     */
    public String getAutoValue() {
        return autoValue;
    }

    /**
     * @return the autoTime
     */
    public String getAutoTime() {
        return autoTime;
    }

    /**
     * @param abilityId the abilityId to set
     */
    public void setAbilityId(String abilityId) {
        this.abilityId = abilityId;

    }

    /**
     * @param abilityDescId the abilityDescId to set
     */
    public void setAbilityDescId(String abilityDescId) {
        this.abilityDescId = abilityDescId;

    }

    /**
     * @param autoAbilityRate the autoAbilityRate to set
     */
    public void setAutoAbilityRate(String autoAbilityRate) {
        this.autoAbilityRate = autoAbilityRate;

    }

    /**
     * @param autoAbilityId the autoAbilityId to set
     */
    public void setAutoAbilityId(String autoAbilityId) {
        this.autoAbilityId = autoAbilityId;

    }

    /**
     * @param autoValue the autoValue to set
     */
    public void setAutoValue(String autoValue) {
        this.autoValue = autoValue;

    }

    /**
     * @param autoTime the autoTime to set
     */
    public void setAutoTime(String autoTime) {
        this.autoTime = autoTime;

    }



    public String getDescription(String lang) {
        StringBuilder sb = new StringBuilder(100);
        if (abilityId.equals("0")) {
            sb.append(LocalizationHelper.getInstance()
                    .getText(lang, "AUTO_ABILITY_DESC_" + getAbilityDescId())
                    .replace("{0}", getAutoAbilityRate()) + ":"
                    + LocalizationHelper.getInstance().getText(lang, "ABILITY_" + getAutoAbilityId())
                    +" "+getAutoValue()+"%"
                    + " "+LocalizationHelper.getInstance().getText(lang, "ABILITY_DESC_TIME").replace("{0}", getAutoTime()));
        } else {
            sb.append(LocalizationHelper.getInstance().getText(lang, "ABILITY_" + abilityId));
        }
        return sb.toString();
    }


    public String getFullId() {
        if (abilityId.equals("0")) {
            return "0:"+autoAbilityId+"_"+getAbilityDescId() +"_"+getAutoAbilityRate()+"_"+getAutoValue()+"_"+getAutoTime();
        }
        else {
            return abilityId;
        }
    }
}