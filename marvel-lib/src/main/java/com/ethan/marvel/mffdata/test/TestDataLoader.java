/**
 * 
 */
package com.ethan.marvel.mffdata.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.ethan.marvel.dataprocessing.Ref;
import com.ethan.marvel.mffdata.DataLoader;
import com.google.gson.Gson;

/**
 * @author GBTW0011 created date: 2015年7月15日
 */
public class TestDataLoader {
    DecimalFormat df2 = new DecimalFormat("##0.0");

    /**
     * 
     */
    public TestDataLoader() {
        // TODO Auto-generated constructor stub
    }

    public static void r99(int i,int j) {
        System.out.println(i+" * "+ j + " = "+(i*j++));

        if(j>=10) {
            j=1;
            i++;
        }
        if(i>=10)  {
            return;
        }
        r99(i,j);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        TestDataLoader tdl = new TestDataLoader();
       
        //r99(1,1);
        
//        for (int i = 1, j = 0; i <= 9 && j <= 9; System.out.println((i = (j == 9) ? i + 1 : i) + " * "
//                + (j = (j == 9 ? 1 : j + 1)) + " = " + (i * j))) { }

        // double a = 1d;
        // double b = 4d;
        // double c = 0.25d;
        // double d = a/b;
        // System.out.println("d: "+d);
        // System.out.println("d==c "+(d==c));
        // System.out.println("d==c "+Double.compare(d,c));
//         tdl.convertTeamSet();
         tdl.convertStriker();
//         tdl.mergeLocalization();
//         tdl.compareLocalization();
        // tdl.testReadCard();
         
//         tdl.readRandomPick();
        
//        tdl.checkNewFile();
         
//        tdl.loadQuiz();
        
        
//        tdl.testIfNewUniform();

    }
    
    public void readRandomPick() {
        DataLoader tw = new DataLoader();
        tw.loadData(new File("/data/marvel/MFF Data/Localization_zh.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        DataLoader rand = new DataLoader();
        rand.loadData(new File("/data/marvel/MFF Data/RANDOM_PICK.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        Map<String, String> aa = new HashMap<String, String>();
        
        while(rand.next()) {
            if(!rand.getData("PICK_ID").equals("1135")) {
                continue;
            }
            
            String item = rand.getData("RESULT_VALUE");
            System.out.println(tw.getData("ITEM_"+item, 1));
            
            String cid = item.substring(6,8);
            String lvl=item.substring(8);
            
            if(aa.containsKey(cid)) {
                aa.put(cid, aa.get(cid)+", "+lvl);
            }
            else {
                aa.put(cid, lvl);
            }
            
            
        }
        
        for(String a:aa.keySet()) {
            System.out.println(a+"\t"+aa.get(a));
        }
        
        for(String a:aa.keySet()) {
            System.out.println("<img src='cc_"+a+".png' width='200'>");
        }
        
    }
    
    public void checkNewFile() {
        //"/data/MFF_Dump/MFF_Dump_Data_V3.2.0_20170801/text_decode_utf8/"
        File newVersion = new File("/data/MFF_Dump/MFF_Dump_Data_V4.0.1/text_decode_utf8/");
        String[] nvFiles = newVersion.list();
        
        File oldVersion = new File("/data/MFF_Dump/MFF_Dump_Data_V3.9.0/text_decode_utf8/");
        String[] ovFiles = oldVersion.list();
        
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sbsize = new StringBuilder();
        
        System.out.println("\tNEW\tOLD");
        
        for(String nvfile:nvFiles) {
            boolean found=false;
            
            System.out.print(nvfile+"\t");
            
            File nfile = new File(newVersion.getAbsolutePath()+"/"+nvfile);
            System.out.print(nfile.length()+"\t");
            
            for(String ovfile:ovFiles) {
                if(nvfile.equals(ovfile)) {
                    File ofile = new File(oldVersion.getAbsolutePath()+"/"+nvfile);
                    System.out.print(ofile.length()+"\t");
                    
                    
                    found=true;
                    
                    if(nfile.length()>ofile.length()) {
                        sbsize.append(nvfile+"\n");
                    }
                    
                    
                    break;
                }
            }
            
            if(!found) {
                sb.append(nvfile+"\n");
            }
            
            System.out.println();
        }
        
        System.out.println("====== new files ======");
        System.out.println(sb.toString());
        
        System.out.println("====== increase size files ======");
        System.out.println(sbsize.toString());
                
    }
    

    public void convertTeamSet() {
        // DataLoader loader = new DataLoader();
        // loader.loadData(new
        // File("/data/marvel/MFF Data/ABILITY_LIST.csv.txt.decode.txt"),
        // "UTF-16LE", "\t", 0);
        // System.out.println(loader.getData("10", 2));
        // System.out.println(loader.getData("9014", 3));
        //
        // DataLoader locationzhLoader = new DataLoader();
        // locationzhLoader.loadData(new
        // File("/data/marvel/MFF Data/Localization_zh.txt"), "UTF-8", "\t", 0);
        // System.out.println(locationzhLoader.getData("SKILL_100100501",
        // "TEXT"));
        // System.out.println(locationzhLoader.getData("STAGE_NAME_30404", 1));
        //
        // DataLoader stageloader = new DataLoader();
        // stageloader.loadData(new
        // File("/data/marvel/MFF Data/STAGE_CLEAR_REWARD.csv.txt.decode.txt"),
        // "UTF-16LE", "\t",
        // 0);
        //
        // try {
        // BufferedWriter bw = new BufferedWriter(new
        // FileWriter("/data/marvel/output/stage_clear_reward.txt"));
        // for (int i = 0, size = stageloader.size(); i < size; i++) {
        // String[] data = stageloader.getData(i);
        // bw.write(data[0]);
        // bw.write("\t");
        // if (locationzhLoader.getData("STAGE_NAME_" + data[0], 1) == null) {
        // bw.write("");
        // } else {
        // bw.write(locationzhLoader.getData("STAGE_NAME_" + data[0], 1));
        // }
        // for (int j = 1, ds = data.length; j < ds; j++) {
        // bw.write("\t");
        // bw.write(data[j]);
        // }
        //
        // bw.write("\n");
        // }
        // bw.close();
        //
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        DataLoader heroIdMaploader = new DataLoader();
        heroIdMaploader.loadData(new File("/data/marvel/heroidmapping.txt"), "UTF-8", "\t", 0);

        DataLoader abilitymaploader = new DataLoader();
        abilitymaploader.loadData(new File("/data/marvel/abilitymapping.txt"), "UTF-8", "\t", 0);

        DataLoader teamSetloader = new DataLoader();
        teamSetloader.loadData(new File("/data/marvel/MFF Data/TEAM_SET_BUFF.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        for (int i = 0, size = teamSetloader.size(); i < size; i++) {
            if (teamSetloader.getData(i, "TYPE").equals("1")) {
                System.out.print("id=" + teamSetloader.getData(i, "ID") + ",");
                System.out.print("h=");
                System.out.print(heroIdMaploader.getData(teamSetloader.getData(i, "VALUE_1"), 1));
                System.out.print(":");
                System.out.print(heroIdMaploader.getData(teamSetloader.getData(i, "VALUE_2"), 1));
                if (!teamSetloader.getData(i, "VALUE_3").equals("0")) {
                    System.out.print(":");
                    System.out.print(heroIdMaploader.getData(teamSetloader.getData(i, "VALUE_3"), 1));
                }

                System.out.print("," + abilitymaploader.getData(teamSetloader.getData(i, "ABILITY_TYPE_1"), 1) + "=");

                // System.out.print("s:"+teamSetloader.getData(i,
                // "ABILITY_TYPE_1")+"->"+teamSetloader.getData(i,
                // "ABILITY_VALUE_1"));
                float v1 = Float.parseFloat(teamSetloader.getData(i, "ABILITY_VALUE_1")) / 100f;
                System.out.print(df2.format(v1) + "%");

                System.out.print("," + abilitymaploader.getData(teamSetloader.getData(i, "ABILITY_TYPE_2"), 1) + "=");
                float v2 = Float.parseFloat(teamSetloader.getData(i, "ABILITY_VALUE_2")) / 100f;
                System.out.print(df2.format(v2) + "%");

                if (!teamSetloader.getData(i, "ABILITY_TYPE_3").equals("0")) {
                    System.out.print("," + abilitymaploader.getData(teamSetloader.getData(i, "ABILITY_TYPE_3"), 1)
                            + "=");
                    float v3 = Float.parseFloat(teamSetloader.getData(i, "ABILITY_VALUE_3")) / 100f;
                    System.out.print(df2.format(v3) + "%");
                }

                // debug only
                // if(teamSetloader.getData(i, "ID").equals("292")) {
                // System.out.println("->"+teamSetloader.getData(i,
                // "ABILITY_TYPE_2")+"<-");
                // }
                // debug only

            }

            System.out.println();

        }

        // for(int i=0,size=abilitymaploader.size();i<size;i++) {
        // System.out.println(abilitymaploader.getData(i,
        // 0)+"\t"+abilitymaploader.getData(i, 1));
        //
        // }

    }

    public void convertStriker() {
        // haha 3
        DataLoader heroIdMaploader = new DataLoader();
        heroIdMaploader.loadData(new File("/data/marvel/heroidmapping.txt"), "UTF-8", "\t", 0);

        DataLoader strikerloader = new DataLoader();
        strikerloader.loadData(new File("/data/marvel/MFF Data/STRIKER.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        String lastid = "";
        for (int i = 0, size = strikerloader.size(); i < size; i++) {
            String sid = strikerloader.getData(i, "HERO_GROUP_ID");
            if (!sid.equals(lastid)) {
                System.out.println();
                System.out.print(heroIdMaploader.getData(sid, 1));
                lastid = sid;
            }

            if(heroIdMaploader.getData(strikerloader.getData(i, "TARGET_HERO"), 1)
                    !=null) {
                System.out.print("," + heroIdMaploader.getData(strikerloader.getData(i, "TARGET_HERO"), 1) + "="
                        + strikerloader.getData(i, "AUTO_ABILITY_RATE").substring(0, 2) + ":"
                        + strikerloader.getData(i, "AUTO_ABILITY_ID") + ":" + strikerloader.getData(i, "COOLTIME"));
            }
            
            

        }

    }

    public void loadHeroStatus() {
        DataLoader heroIdMaploader = new DataLoader();
        heroIdMaploader.loadData(new File("/data/marvel/heroidmapping.txt"), "UTF-8", "\t", 0);

    }

    public void mergeLocalization() {
        DataLoader en = new DataLoader();
        en.loadData(new File("/data/marvel/MFF Data/Localization_en.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader tw = new DataLoader();
        tw.loadData(new File("/data/marvel/MFF Data/Localization_zh.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader jp = new DataLoader();
        jp.loadData(new File("/data/marvel/MFF Data/Localization_ja.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader ko = new DataLoader();
        ko.loadData(new File("/data/marvel/MFF Data/Localization_ko.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/data/marvel/MFF Data/Localization_en_tw_jp_ko.txt"));

            bw.append("KEY\tTEXT_EN\tTEXT_TW\tTEXT_JP\tTEXT_KO\n");

            while (en.next()) {
                String key = en.getData("KEY");
                System.out.println("Key: " + key);
                bw.append(key);
                bw.append("\t");
                bw.append(en.getData(key, "TEXT"));
                bw.append("\t");
                bw.append(tw.getData(key, "TEXT"));
                bw.append("\t");
                bw.append(jp.getData(key, "TEXT"));
                bw.append("\t");
                bw.append(ko.getData(key, "TEXT"));
                bw.append("\n");

            }

            bw.close();

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void compareLocalization() {
        DataLoader tw = new DataLoader();
        tw.loadData(
                new File(
                        "/data/MFF_Dump/MFF_Dump_Data_V4.0.1/localization_zh_text_decode_utf8/Localization_zh.csv.txt.decode.txt"),
                "UTF-8", "\t", 0);

        DataLoader twold = new DataLoader();
        twold.loadData(
                new File(
                        "/data/MFF_Dump/MFF_Dump_Data_V3.9.0/localization_zh_text_decode_utf8/Localization_zh.csv.txt.decode.txt"),
                "UTF-8", "\t", 0);

        while (tw.next()) {
            String twkey = tw.getData("KEY");
            // System.out.println("key: " + twkey);
            if (!twold.containsKey(twkey)) {
                System.out.println(twkey + "\t" + tw.getData(twkey, "TEXT"));
            }
        }

    }

    private String prefixZero(int n, int digits) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder(digits + 1);
        for (int i = 0; i < digits - s.length(); i++) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    public void testReadCard() {
        DataLoader card = new DataLoader();
        card.loadData(new File("/data/marvel/MFF Data/CARD_QUALITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader rnd = new DataLoader();
        rnd.loadData(new File("/data/marvel/MFF Data/RANDOM_OPTION.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader tw = new DataLoader();
        tw.loadData(new File("/data/marvel/MFF Data/Localization_zh.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader abi = new DataLoader();
        abi.loadData(new File("/data/marvel/MFF Data/ACTION_ABILITY.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        for (int i = 1; i <= 45; i++) {
            String item = "3" + prefixZero(i, 7) + "6";
            card.first();

            while (card.next()) {
                String s = card.getData(0);
                if (s.equals(item)) {
                    System.out.println(tw.getData("ITEM_" + item, "TEXT") + "\t"
                            + getAbility(rnd, card.getData("GRADE_1_OPTION"), tw, abi) + "\t"
                            + getAbility(rnd, card.getData("GRADE_2_OPTION"), tw, abi) + "\t"
                            + getAbility(rnd, card.getData("GRADE_3_OPTION"), tw, abi) + "\t"
                            + getAbility(rnd, card.getData("GRADE_4_OPTION"), tw, abi) + "\t"
                            + getAbility(rnd, card.getData("GRADE_5_OPTION"), tw, abi) + "\t"
                            + getAbility(rnd, card.getData("GRADE_6_OPTION"), tw, abi));
                    break;
                }
            }

        }
    }

    /**
     * 本來要檢查有沒有新制服躲在資料裡，結果目前沒啥用處
     */
    public void testIfNewUniform() {
        DataLoader uniUpg = new DataLoader();
        uniUpg.loadData(new File("/data/marvel/MFF Data/UNIFORM_UPGRADE.csv.txt.decode.txt"), "UTF-8", "\t", 0);

        DataLoader uni = new DataLoader();
        uni.loadData(new File("/data/marvel/MFF Data/UNIFORM.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        
        while(uni.next()) {
            String uid = uni.getData(0);
            System.out.println(uid);
            
            uniUpg.first();
            boolean found=false;
            while(uniUpg.next()) {
                
                if(uniUpg.getData(0).equals(uid)) {
                    found=true;
                    break;
                }
                
                
            }
            
            if(!found) {
                System.out.println("hahah: "+uid);
            }
            
        }
        
        
        
        

    }
    
    public void loadQuiz() {
        DataLoader quiz = new DataLoader();
        quiz.loadData(new File("/data/marvel/MFF Data/MARVEL_QUIZ.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        DataLoader lo = new DataLoader();
        lo.loadData(new File("/data/marvel/MFF Data/Localization_zh.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        
        
       quiz.first();
       
       while(quiz.next()) {
           String id = quiz.getData("QUIZ_ID");
           String ans = quiz.getData("ANSWER");
           
           
           System.out.println(lo.getData("QUIZ_"+id, "TEXT")+"\t"+lo.getData("QUIZ_"+id+"_ANSWER_"+ans, "TEXT"));
           
       }
        
        

    }
    
    
    private String getAbility(DataLoader rnd, String option, DataLoader loc, DataLoader abi) {
        StringBuilder sb = new StringBuilder();
        rnd.first();
        while (rnd.next()) {
            if (rnd.getData("OPTION_GROUP").equals(option)) {
                if (!rnd.getData("OPTION_TYPE").equals("0")) {
                    sb.append(loc.getData("ABILITY_" + rnd.getData("OPTION_TYPE"), "TEXT") + ", ");
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
                    // "ABILITY_DESC_TIME"
                    sb.append(loc.getData("AUTO_ABILITY_DESC", "TEXT"));
                    sb.append(loc.getData("AUTO_ABILITY_DESC_" + autoAbiDesc, "TEXT").replace("{0}", autoAbiRate) + "，");
                    sb.append(loc.getData("ABILITY_" + abiId, "TEXT"));
                    sb.append(abiValue + "%");
                    sb.append(" " + loc.getData("ABILITY_DESC_TIME", "TEXT").replace("{0}", abiTime) + ", ");

                }
            }
        }

        return sb.toString();
    }
}
