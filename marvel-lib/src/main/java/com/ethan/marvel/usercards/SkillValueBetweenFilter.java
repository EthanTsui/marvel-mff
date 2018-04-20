package com.ethan.marvel.usercards;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/21.
 */
public class SkillValueBetweenFilter implements IFilter {
    String skillId = null;
    float lowerBound=0.0f;
    float upperBound=999f;

    public SkillValueBetweenFilter(String skillId, float lowerBound, float upperBound) {
        this.skillId=skillId;
        this.lowerBound=lowerBound;
        this.upperBound=upperBound;
    }


    @Override
    public boolean accept(UserCollection collection) {
        return collection.getSkill(skillId) >= lowerBound && collection.getSkill(skillId) <=upperBound;
    }
}
