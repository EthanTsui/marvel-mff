package com.ethan.marvel.usercards;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/11.
 */
public class UserCollection {

    private String tokenId;

    private int collectionNo;

    private UserCard[] slots = new UserCard[5];


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public int getCollectionNo() {
        return collectionNo;
    }

    public void setCollectionNo(int collectionNo) {
        this.collectionNo = collectionNo;
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



}
