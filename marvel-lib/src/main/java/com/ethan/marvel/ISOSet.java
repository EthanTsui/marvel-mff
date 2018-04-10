/**
 * 
 */
package com.ethan.marvel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author GBTW0011
 * created date: 2015年7月27日
 */
public class ISOSet {
    private int ISOSetId = 0;
    private List<Integer> isoList =new ArrayList<Integer>();
    private String autoType;
    private float autoAbilityRate = 0f;
    private float coolTime = 0f;
    HashMap<String, Unit> abilities = new HashMap<String, Unit>();
    private String conditionAbilityId = "";
    private Unit conditionUnit;
    private float conditionTime =0f;
    
    /**
     * 
     */
    public ISOSet() {
        
    }

    public void addISO(int isoId) {
        isoList.add(isoId);
    }

    public String getAutoType() {
        return autoType;
    }

    public ISOSet setAutoType(String autoType) {
        this.autoType = autoType;
        return this;
    }

    public HashMap<String, Unit> getAbilities() {
        return abilities;
    }

    public ISOSet setAbilities(HashMap<String, Unit> abilities) {
        this.abilities = abilities;
        return this;
    }
    
    public ISOSet addAbility(String name, Unit unit) {
        abilities.put(name, unit);
        return this;
    }

    public int getISOSetId() {
        return ISOSetId;
    }

    public ISOSet setISOSetId(int iSOSetId) {
        ISOSetId = iSOSetId;
        return this;
    }

    public float getAutoAbilityRate() {
        return autoAbilityRate;
    }

    public ISOSet setAutoAbilityRate(float autoAbilityRate) {
        this.autoAbilityRate = autoAbilityRate;
        return this;
    }

    public List<Integer> getIsoList() {
        return isoList;
    }

    public ISOSet setIsoList(List<Integer> isoList) {
        this.isoList = isoList;
        return this;
    }

    public float getCoolTime() {
        return coolTime;
    }

    public ISOSet setCoolTime(float coolTime) {
        this.coolTime = coolTime;
        return this;
    }

    public String getConditionAbilityId() {
        return conditionAbilityId;
    }

    public ISOSet setConditionAbilityId(String conditionAbilityId) {
        this.conditionAbilityId = conditionAbilityId;
        return this;
    }

    public Unit getConditionUnit() {
        return conditionUnit;
    }

    public ISOSet setConditionUnit(Unit conditionUnit) {
        this.conditionUnit = conditionUnit;
        return this;
    }

    public float getConditionTime() {
        return conditionTime;
    }

    public ISOSet setConditionTime(float conditionTime) {
        this.conditionTime = conditionTime;
        return this;
    }
    
    
}
