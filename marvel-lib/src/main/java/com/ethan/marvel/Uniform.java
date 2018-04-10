/**
 * 
 */
package com.ethan.marvel;

import java.util.*;

/**
 * @author Ethan Tsui
 * created date: 2017年1月16日
 */
public class Uniform {
    private Map<Integer, Uniform> requiredUniforms = new TreeMap<Integer, Uniform>();
    private List<Uniform> beRequiredUniforms = new ArrayList<Uniform>();
    private String uniformId;
    private Hero hero = null;
    private String herotype="";
    private int imgidx = 0;

    /**
     * @return the uniformId
     */
    public String getUniformId() {
        return uniformId;
    }

    /**
     * @return the hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * @param uniformId the uniformId to set
     */
    public void setUniformId(String uniformId) {
        this.uniformId = uniformId;
    }

    /**
     * @param hero the hero to set
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    
    public void addRequiredUniform(int grade, Uniform u) {
        requiredUniforms.put(grade, u);
        u.addBeRequiredUniform(this);
    }

    
    public void addBeRequiredUniform(Uniform u) {
        beRequiredUniforms.add(u);
    }
    /**
     * @return the requiredUniforms
     */
    public List<Uniform> getBeRequiredUniforms() {
        return beRequiredUniforms;
    }

    
    public int getNumberOfRequired() {
        return requiredUniforms.size();
    }
    
    public int getNumberOfBeRequired() {
        return beRequiredUniforms.size();
    }

    /**
     * @return the imgidx
     */
    public int getImgidx() {
        return imgidx;
    }

    /**
     * @param imgidx the imgidx to set
     */
    public void setImgidx(int imgidx) {
        this.imgidx = imgidx;
    }

    /**
     * @return the requiredUniforms
     */
    public Map<Integer, Uniform> getRequiredUniforms() {
        return requiredUniforms;
    }

    /**
     * @return the herotype
     */
    public String getHerotype() {
        return herotype;
    }

    /**
     * @param herotype the herotype to set
     */
    public void setHerotype(String herotype) {
        this.herotype = herotype;
    }
    
    
}
