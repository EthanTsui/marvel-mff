/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011 created date: 2016年7月15日
 */
public class CardDB {
    private static CardDB instance = null;
    private static final Integer LOCK = new Integer(0);
    private HashMap<Integer, Card> cards;
    private String[] allAbilities = null;

    private CardDB() {
        init();
    }

    public static CardDB getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new CardDB();
                }
            }
        }
        return instance;
    }

    private void init() {
        cards = new HashMap<Integer, Card>();
        loadData();
    }

    public Card getCard(int cardId) {
        return cards.get(cardId);
    }

    public HashMap<Integer, Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    private void loadData() {
        DataLoader card = new DataLoader();
        card.loadData(new File("/data/marvel/mffdata/CARD_QUALITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader rnd = new DataLoader();
        rnd.loadData(new File("/data/marvel/mffdata/RANDOM_OPTION.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader abi = new DataLoader();
        abi.loadData(new File("/data/marvel/mffdata/ACTION_ABILITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader img = new DataLoader();
        img.loadData(new File("/data/marvel/mffdata/IMAGE_PACK.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader obtainDL = new DataLoader();
        obtainDL.loadData(new File("/data/marvel/mffdata/OBTAIN.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader stageDL = new DataLoader();
        stageDL.loadData(new File("/data/marvel/mffdata/STAGE.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader hstageDL = new DataLoader();
        hstageDL.loadData(new File("/data/marvel/mffdata/HIDDEN_STAGE_GROUP.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader rndpick = new DataLoader();
        rndpick.loadData(new File("/data/marvel/mffdata/RANDOM_PICK.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader newcard = new DataLoader();
        newcard.loadData(new File("/data/marvel/new.card.list.txt"), "UTF-8", "\t", 0);

        for (int i = 1; i <= 9999; i++) {
            if(i>=82) {
                break;
            }

            if (!img.containsKey("cc_" + prefixZero(i, 4))) {
                break;
            }

            Card c = new Card(i);

            String item = "3" + prefixZero(i, 7) + "6";
            String item1 = "3" + prefixZero(i, 7) + "1";

            c.setCardItemId(item);

            if (i >= 47) {

                newcard.first();

                while (newcard.next()) {
                    if (Integer.toString(i).equals(newcard.getData("cardid"))) {
                        
//                        System.out.println(i);
                        
                        for(int xx=0;xx<6;xx++) {
                            List<CardAbility> caList = new ArrayList<CardAbility>();
                            
                            for(int cc=1;cc<=5;cc++) {
                                
//                                System.out.println(xx+", "+cc);
                                
                                if(newcard.getData("opt"+cc)==null || newcard.getData("opt"+cc).length()<=0) {
                                    break;
                                }
                                
                                String opt = newcard.getData("opt"+cc);
                                if(opt.startsWith("0:")) {
                                    
                                    String[] spltdata = opt.substring(2).split("_");
                                    CardAbility cardAbility = new CardAbility();
                                    cardAbility.abilityId = "0";
                                    cardAbility.abilityDescId = spltdata[1];
                                    cardAbility.autoAbilityRate = spltdata[2];
                                    cardAbility.autoValue = spltdata[3];
                                    cardAbility.autoAbilityId = spltdata[0];
                                    cardAbility.autoTime = spltdata[4];
                                    
                                    caList.add(cardAbility);                             
                                    
                                    
                                }
                                else {
                                    CardAbility ca = new CardAbility();
                                    ca.abilityId=opt;
                                    caList.add(ca);
                                }
                                
                                
                                
                            }
                            
                            c.setAbilityLevel(xx+1, caList);
                            newcard.next();
                            
                        }

                    }
                    cards.put(i, c);
                }

            } else {

                card.first();

                // get card ability
                while (card.next()) {
                    String s = card.getData(0);
                    if (s.equals(item)) {

                        for (int j = 1; j <= 6; j++) {
                            List<CardAbility> caList = getAbility(rnd, card.getData("GRADE_" + j + "_OPTION"), abi);

                            c.setAbilityLevel(j, caList);

                        }

                        break;
                    }
                }

                // get obtain stage

                // obtainDL.first();
                // String stageid = null;
                //
                // while (obtainDL.next()) {
                // if (obtainDL.getData("ITEM_ID").equals(item1)) {
                // stageid = obtainDL.getData("STAGE_ID");
                // break;
                // }
                // }
                // String stagetype = null;
                // if (stageid != null) {
                // stagetype = stageDL.getData(stageid, "TYPE");
                //
                // hstageDL.first();
                // boolean hsfound = false;
                // while (hstageDL.next()) {
                // if (hstageDL.getData("STAGE_ID").equals(stageid)) {
                // hsfound = true;
                // break;
                // }
                // }
                // if (hsfound) {
                // c.setObtainStageId(stageid);
                // c.setObtainStageType(stagetype);
                // }
                // }
                //
                // // get pick list
                // for (int xr = 1; xr <= 6; xr++) {
                // String item2 = "3" + prefixZero(i, 7) + xr;
                //
                // List<String> picklist = new ArrayList<String>();
                //
                // rndpick.first();
                //
                // while (rndpick.next()) {
                // if (rndpick.getData("RESULT_VALUE").equals(item2)) {
                // picklist.add(rndpick.getData("PICK_ID"));
                // }
                // }
                //
                // c.getPickList().put(xr, picklist);
                // }

            }

            cards.put(i, c);
        }
    }

    private static String prefixZero(int n, int digits) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder(digits + 1);
        for (int i = 0; i < digits - s.length(); i++) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    private List<CardAbility> getAbility(DataLoader rnd, String option, DataLoader abi) {
        List<CardAbility> abilityList = new ArrayList<CardAbility>();
        rnd.first();
        while (rnd.next()) {
            if (rnd.getData("OPTION_GROUP").equals(option)) {
                if (!rnd.getData("OPTION_TYPE").equals("0")) {
                    CardAbility cardAbility = new CardAbility();
                    cardAbility.abilityId = rnd.getData("OPTION_TYPE");
                    abilityList.add(cardAbility);
                } else {
                    String autoAbiDesc = rnd.getData("AUTO_ABILITY_ID");
                    String autoAbiRate = rnd.getData("AUTO_ABILITY_RATE");
                    autoAbiRate = autoAbiRate.substring(0, autoAbiRate.length() - 2);
                    String abiGroup = rnd.getData(8);

                    String abiId = abi.getData(abiGroup, 1);
                    String abiTime = abi.getData(abiGroup, 2);
                    abiTime = abiTime.substring(0, abiTime.length() - 3);
                    String abiValue = abi.getData(abiGroup, "ACTION_ABILITY_PARAM1");
                    abiValue = abiValue.substring(0, abiValue.length() - 2);

                    CardAbility cardAbility = new CardAbility();
                    cardAbility.abilityId = rnd.getData("OPTION_TYPE");
                    cardAbility.abilityDescId = autoAbiDesc;
                    cardAbility.autoAbilityRate = autoAbiRate;
                    cardAbility.autoValue = abiValue;
                    cardAbility.autoAbilityId = abiId;
                    cardAbility.autoTime = abiTime;

                    abilityList.add(cardAbility);
                }
            }
        }

        return abilityList;
    }

    public List<Card> getFilteredCards(String[] abilities, int level) {
        List<Card> newcards = new ArrayList<Card>();
        for (int i = 1, size = cards.size(); i <= size; i++) {
            Card c = cards.get(i);
            if (c.containsAbilityId(abilities, level) || c.getLevelAbility(level) == null
                    || c.getLevelAbility(level).isEmpty()) {
                newcards.add(c);
            }
        }

        return newcards;
    }

    public String[] getAllAbilities() {
        if (allAbilities == null) {
            HashMap<String, String> all = new HashMap<String, String>();

            for (Card c : CardDB.getInstance().getCards().values()) {
                for (String sss : c.getAllAbilityId()) {
                    all.put(sss, sss);
                }
            }
            allAbilities = all.keySet().toArray(new String[all.size()]);
        }

        return allAbilities;
    }

    public static void main(String[] args) {

        HashMap<String, String> all = new HashMap<String, String>();

        System.out.println("size: " + CardDB.getInstance().getCards().size());

        // for (Card c :
        // CardDB.getInstance().getFilteredCards(CardDB.getInstance().getAllAbilities(),
        // 3)) {
        for (Card c : CardDB.getInstance().getCards().values()) {
            for (String sss : c.getAllAbilityId()) {
                all.put(sss, sss);
            }

            // if(!c.containsAbilityId("25",3) && !c.containsAbilityId("9",3)) {
            String[] abilities = { "9", "25", "26" };
            // if (!c.containsAbilityId(abilities, 2)) {
            // continue;
            // }

            // if(c.getCardId()!=44) {
            // continue;
            // }

            System.out.println("" + c.cardId + "\t"
                    + LocalizationHelper.getInstance().getText("en", "ITEM_3" + prefixZero(c.cardId, 7) + "1"));
            for (int i = 1; i <= 6; i++) {
                System.out.print("\t" + i + "\t");
                List<CardAbility> calist = c.abilityLevels.get(i);
                // System.out.print("\t" + c.getObtainStageId() + ": " +
                // c.getObtainStageType() + "\t");
                for (CardAbility ca : calist) {
                    if (ca.abilityId.equals("0")) {
                        System.out.print(ca.autoAbilityId+"_"+ca.getAbilityDescId() +"_"+ca.getAutoAbilityRate()+"_"+ca.getAutoValue()+"_"+ca.getAutoTime()
                                + ":"
                                +ca.getDescription("en"));
                    } else {
                        System.out.print(ca.abilityId + ":"
                                + ca.getDescription("en")+", ");
                    }
                }
                System.out.print("\t");
                // for (String itemid : c.getPickList().get(i)) {
                // System.out.print(itemid + ": " +
                // LocalizationHelper.getInstance().getText("zhtw", "ITEM_" +
                // itemid)
                // + ", ");
                // }

                System.out.println();
            }

        }

        for (String s : all.keySet()) {
            System.out.println(s);
        }

    }

}
