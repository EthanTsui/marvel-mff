package com.ethan.marvel.usercards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ethan Yin-Hao Tsui on 2018/4/19.
 */
public class UserCardOptimizer {
    List<UserCard> cards = null;

    List<UserCollection> collections = new ArrayList<UserCollection>();

    private String skillId = "9_26";

    List<IFilter> filters = new ArrayList<IFilter>();

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public UserCardOptimizer addFilter(IFilter filter) {
        filters.add(filter);
        return this;
    }

    public List<UserCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<UserCollection> collections) {
        this.collections = collections;
    }


    private static final String[] SKILLS = {"9_26", "9_25", "103", "19", "11", "4", "10_27", "10_28", "6", "7", "8"};


    public UserCardOptimizer(List<UserCard> cards) {
        this.cards = cards;
    }

    public void run() {

        Combination2 combination = new Combination2();
        combination.generate(cards.toArray(new UserCard[cards.size()]));


        collections = combination.getCollections();


        Collections.sort(collections, new CollectionComparator(skillId));

        List<UserCollection> results = new ArrayList<UserCollection>();

        int counter=0;
        int i=0;
        while(counter<50 && i<collections.size()) {
            UserCollection collection = collections.get(i);
            i++;

            boolean accepted = true;
            for(IFilter filter:filters) {
                if(!filter.accept(collection)) {
                    accepted=false;
                    break;
                }
            }

            if(accepted) {
                results.add(collection);
                counter++;
            }
        }

        collections=results;

    }

    public void output() {
        for(UserCollection collection:collections) {
            for(int i=1;i<=5;i++) {
                System.out.print(collection.getSlotCard(i).getCardUId()+"\t"+collection.getSlotCard(i).getCardId()+"\t");
            }

            for(String str:SKILLS) {
                System.out.print(collection.getSkill(str)+"\t");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        UserCardDAO dao = new UserCardDAO();
        try {
            List<UserCard> cards = dao.getUserCards("I_DONT_WANT_TO_SHOW_YOU");

            UserCardOptimizer opt = new UserCardOptimizer(cards);

            opt.run();

            opt.output();

        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (dao != null) {
                try {
                    dao.closeConnection();
                } catch (Exception ignore) {
                }
            }
        }
    }
}
