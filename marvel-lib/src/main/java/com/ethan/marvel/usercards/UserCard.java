/**
 * 
 */
package com.ethan.marvel.usercards;

import java.util.Date;

/**
 * @author GBTW0011
 * created date: 2018年4月3日
 */
public class UserCard {
    private final static float[] LEVEL_RANK = {7.5f, 8f, 8.5f, 9f, 9.5f, 10f, 11f};
    private final static float[] OPTION_RATIO= {0f, 0f, 5.1f, 5.4f, 5.7f, 6f};
    protected String tokenId;
    protected String cardUId;
    protected int cardId;
    protected int level=1;
    protected String[] options = new String[6];
    
    protected Date insertSt;
    protected Date updateSt;

    /**
     * @return the tokenId
     */
    public String getTokenId() {
        return tokenId;
    }
    /**
     * @return the cardUId
     */
    public String getCardUId() {
        return cardUId;
    }
    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }
    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
    /**
     * @return the option1
     */
    public String getOption1() {
        return options[0];
    }
    /**
     * @return the option2
     */
    public String getOption2() {
        return options[1];
    }
    /**
     * @return the option3
     */
    public String getOption3() {
        return options[2];
    }
    /**
     * @return the option4
     */
    public String getOption4() {
        return options[3];
    }
    /**
     * @return the option5
     */
    public String getOption5() {
        return options[4];
    }
    /**
     * @return the option6
     */
    public String getOption6() {
        return options[5];
    }
    /**
     * @return the insertSt
     */
    public Date getInsertSt() {
        return insertSt;
    }
    /**
     * @return the updateSt
     */
    public Date getUpdateSt() {
        return updateSt;
    }
    /**
     * @param tokenId the tokenId to set
     */
    public UserCard setTokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }
    /**
     * @param cardUId the cardUId to set
     */
    public UserCard setCardUId(String cardUId) {
        this.cardUId = cardUId;
        return this;
    }
    /**
     * @param cardId the cardId to set
     */
    public UserCard setCardId(int cardId) {
        this.cardId = cardId;
        return this;
    }
    /**
     * @param level the level to set
     */
    public UserCard setLevel(int level) {
        this.level = level;
        return this;
    }
    /**
     * @param option1 the option1 to set
     */
    public UserCard setOption1(String option1) {
        this.options[0] = option1;
        return this;
    }
    /**
     * @param option2 the option2 to set
     */
    public UserCard setOption2(String option2) {
        this.options[1] = option2;
        return this;
    }
    /**
     * @param option3 the option3 to set
     */
    public UserCard setOption3(String option3) {
        this.options[2] = option3;
        return this;
    }
    /**
     * @param option4 the option4 to set
     */
    public UserCard setOption4(String option4) {
        this.options[3] = option4;
        return this;
    }
    /**
     * @param option5 the option5 to set
     */
    public UserCard setOption5(String option5) {
        this.options[4] = option5;
        return this;
    }
    /**
     * @param option6 the option6 to set
     */
    public UserCard setOption6(String option6) {
        this.options[5] = option6;
        return this;
    }
    
    
    public String getOptions(int optionNo) {
        return options[optionNo-1];
    }
    
    public UserCard setOptions(int optionNo, String option) {
        this.options[optionNo-1]=option;
        return this;
    }
    
    public float getOptionsRatio(int optionNo) {
        if(optionNo<=2) {
            return LEVEL_RANK[level-1];
        }
        else {
            return OPTION_RATIO[optionNo-1];
        }
    }

    public float getTotalRatioBySkillId(String skillId) {
        float sum=0.0f;
        for(int i=1;i<=6;i++) {
            String id=options[i-1];
            if(id==null) {
                continue;
            }
            if(id.equals(skillId)) {
                sum+=getOptionsRatio(i);
            }


        }
        return sum;
    }

    /**
     * @param insertSt the insertSt to set
     */
    public UserCard setInsertSt(Date insertSt) {
        this.insertSt = insertSt;
        return this;
    }
    /**
     * @param updateSt the updateSt to set
     */
    public UserCard setUpdateSt(Date updateSt) {
        this.updateSt = updateSt;
        return this;
    }
    

}
