package com.ethan.marvel.usercards;

import java.util.Comparator;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/20.
 */
public class CollectionComparator implements Comparator<UserCollection> {

    private String skillId = null;
    public CollectionComparator(String skillId) {
        this.skillId=skillId;
    }

    @Override
    public int compare(UserCollection o1, UserCollection o2) {
        return (int) (100*(o2.getSkill(skillId)-o1.getSkill(skillId)));
    }
}
