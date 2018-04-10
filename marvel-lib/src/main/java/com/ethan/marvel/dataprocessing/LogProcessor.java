/**
 * 
 */
package com.ethan.marvel.dataprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.gson.Gson;

/**
 * @author GBTW0011 created date: 2015年8月4日
 */
public class LogProcessor {

    /**
     * 
     */
    public LogProcessor() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LogProcessor logp = new LogProcessor();
        logp.haha();

    }

    List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

    public void haha() {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar date = Calendar.getInstance();
            date.setTime(format.parse("2018-01-01"));

            HashMap<String, Integer> ref = new HashMap<String, Integer>();
            HashMap<String, Integer> functionCount = new HashMap<String, Integer>();
            Ref root = new Ref("ROOT");
            
            int counter = 0;
            int filecounter=0;
            
            while (true) {
                File f = new File("/data/mffserver_logs/localhost_access_log_json" + format.format(date.getTime()) + ".txt");
                
                if(!f.exists()) {
                    break;
                }
                filecounter++;
                if(filecounter>=30) {
                    break;
                }
                
                
                System.out.println("processing ... "+f.getName());
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = null;
                
                while ((line = br.readLine()) != null) {
                    if (line.contains(".png")) {
                        continue;
                    }

                    // System.out.println(line);
                    // line = new String(line.getBytes(),"UTF-8");
                    Map<String, Object> obj = null;
                    try {
                        obj = new Gson().fromJson(line, Map.class);
                        objs.add(obj);
                    } catch (Exception err) {
                        System.out.println("error: " + line);
                        continue;
                    }
                    // for(String s:obj.keySet()) {
                    // System.out.println(s+": "+obj.get(s));
                    //
                    // }

                    // String s = (String) obj.get("user-agent");
                    String ga = (String) obj.get("gaid");
                    String s = (String) obj.get("ref");

                    if(ga==null || ga.equals("-")) {
                        continue;
                    }
                    
                    if (s != null) {
                        if (!s.startsWith("http://mff.ethanjojo.com/")) {

                            int v = 1;
                            if (ref.containsKey(s)) {
                                v = ref.get(s) + 1;
                            }

                            ref.put(s, v);
                        }
                    }

                    String s2 = (String) obj.get("request");
                    if (s2.contains("ref=")) {

                        int refidx = s2.indexOf("ref=");
                        // System.out.println("refidx: "+refidx +": "+s2);
                        String rs = getRef(s2);
                        // System.out.println(obj.get("logtime")+", "+s2.substring(s2.indexOf('?')+1,s2.indexOf('&'))+", "+rs);

                        StringTokenizer st = new StringTokenizer(rs, "_");
                        if (st.hasMoreTokens()) {
                            counter++;
                            root.add(st);
                        }
                        // System.out.println(rs);
                    }
                    
                    if(s2.contains("?")) {
                        int idx = s2.indexOf("?");
                        String sub2 = s2.substring(0,idx);
                        if(functionCount.containsKey(sub2)) {
                            functionCount.put(sub2, functionCount.get(sub2)+1);
                        }
                        else {
                            functionCount.put(sub2, 1);
                        }
                    }
                    
                    
                    

                }
                br.close();
                date.add(Calendar.DATE, 1);
            }

            System.out.println("counter: " + counter);
            List<String> values = new ArrayList<String>(ref.keySet());
            Collections.sort(values);
            for (String s : values) {
                System.out.println(s + "\t" + ref.get(s));
            }

            System.out.println("========function===========");
            for(String s:functionCount.keySet()) {
                System.out.println(s+"\t"+functionCount.get(s));
            }
            System.out.println("========function===========");
            
            StringBuilder sb = new StringBuilder(1024);
            printRef(root, 0, sb);

            System.out.println(sb.toString());

            
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    private String getRef(String request) {
        int refidx = request.indexOf("ref=") + 4;
        int endidx = refidx + 4;

        for (int size = request.length(); endidx < size; endidx++) {
            if (request.charAt(endidx) == ' ' || request.charAt(endidx) == '&') {
                break;
            }
        }

        return request.substring(refidx, endidx);
    }

    private void printRef(Ref ref, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }

        sb.append(ref.getRefName());
        sb.append("\t");
        sb.append(Integer.toString(ref.getCount()));
        sb.append("\n");

        for (Ref child : ref.getChildren().values()) {
            printRef(child, depth + 1, sb);
        }

    }

    class DataStat {
        String name;
        HashMap<String, Data> map = new HashMap<String, Data>();

        public DataStat(String name) {
            this.name = name;
        }

        public DataStat add(String key) {
            Data data = null;
            if (map.containsKey(key)) {
                data = map.get(key);
            } else {
                data = new Data(key);
            }
            data.increase();
            map.put(key, data);

            return this;

        }

        public HashMap<String, Data> getMap() {
            return map;
        }

        public DataStat setMap(HashMap<String, Data> map) {
            this.map = map;
            return this;
        }

        public String getName() {
            return name;
        }

        public DataStat setName(String name) {
            this.name = name;
            return this;
        }

    }

    class Data {
        String key;
        int counter = 0;

        public Data(String key) {
            this.key = key;
        }

        public void increase() {
            counter++;
        }

        public String getKey() {
            return key;
        }

        public Data setKey(String key) {
            this.key = key;
            return this;
        }

        public int getCounter() {
            return counter;
        }

    }
}
