package com.ethan.marvel.usercards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/20.
 */
public class Combination2 {

    List<UserCollection> collections = new ArrayList<UserCollection>();

    public List<UserCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<UserCollection> collections) {
        this.collections = collections;
    }

    public void generate(UserCard[] input) {
        combinations(input, 5, 0, new UserCard[5]);
    }

    void combinations(UserCard[] arr, int len, int startPosition, UserCard[] result){
        if (len == 0){


            for(int i=0;i<result.length-1;i++) {
                for(int j=i+1;j<result.length;j++) {
                    if(result[i].getCardId()==result[j].getCardId()) {
                        return;
                    }
                }
            }

            UserCollection collection = new UserCollection();
            for(int i=0;i<result.length;i++) {
                collection.setSlotCard(i+1, result[i]);
            }
            collection.reCalculateSkills();
            collections.add(collection);


            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            combinations(arr, len-1, i+1, result);
        }
    }

}
