/**
 * 
 */
package com.ethan.marvel;

/**
 * @author GBTW0011
 * created date: 2015年7月2日
 */
public class Unit {
    private float value=0f;
    private String unit="";
    
    
    /**
     * 
     */
    public Unit() {
        // TODO Auto-generated constructor stub
    }


    public float getValue() {
        return value;
    }


    public Unit setValue(float value) {
        this.value = value;
        return this;
    }


    public String getUnit() {
        return unit;
    }


    public Unit setUnit(String unit) {
        this.unit = unit;
        return this;
    }
    
    @Override
    public String toString() {
        return value+unit;
    }
    
    public Unit add(Unit u) {
        this.setValue(this.getValue()+u.getValue());
        return this;
    }
    
    public String getData() {
        return value+unit;
    }

}
