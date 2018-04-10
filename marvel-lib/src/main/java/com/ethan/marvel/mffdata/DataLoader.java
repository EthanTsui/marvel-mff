/**
 * 
 */
package com.ethan.marvel.mffdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.InputStreamReader;

/**
 * @author GBTW0011 created date: 2015年7月15日
 */
public class DataLoader {
    private int keyIndex = 0;
    private HashMap<String, Integer> fieldIndex = new HashMap<String, Integer>();
    private HashMap<String, Integer> keyDataMap = new HashMap<String, Integer>();
    List<String[]> data = new ArrayList<String[]>();
    private int index=-1;
    /**
     * 
     */
    public DataLoader() {
        // TODO Auto-generated constructor stub
    }

    public void loadData(File file, String delimiter, int keyIndex) {
        loadData(file, "UTF-8", delimiter, keyIndex);
    }
    
    public void loadData(File file, String encoding, String delimiter, int keyIndex) {
        this.keyIndex=keyIndex;
        try {
            BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file),encoding));
            String line = null;

            // first line: field name
            line = br.readLine();
            String[] fieldName = line.split(delimiter);
            for (int i = 0, size = fieldName.length; i < size; i++) {
                fieldIndex.put(fieldName[i], i);
//                System.out.println("->"+fieldName[i]+"<-, "+i);
            }

//            for(String key:fieldIndex.keySet()) {
//                System.out.println("->"+key+"<-, "+fieldIndex.get(key));
//            }
            
            int index = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() < 3 || line.startsWith("#")) {
                    continue;
                }

                // load data
                String[] entry = line.split(delimiter, fieldIndex.size());
                data.add(entry);
                
//                System.out.println(entry[0]+", "+keyName);
//                System.out.println(entry[0].hashCode()+", "+keyName.hashCode());
                
                
                keyDataMap.put(entry[keyIndex], index);
//                keyIndex.put(entry[0], index);
                index++;
            }
            System.out.println(index +" data entries loaded.");
            br.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public String getData(String key, String fieldName) {
        return getData(key, fieldIndex.get(fieldName));
    }

    public String getData(String key, int fieldIndex) {
        if(!keyDataMap.containsKey(key)) {
            return null;
        }
        if(fieldIndex>=data.get(keyDataMap.get(key)).length) {
            return null;
        }
        
        return data.get(keyDataMap.get(key))[fieldIndex];
    }
    
    public String getData(int keyIndex, String fieldName) {
        return getData(keyIndex, fieldIndex.get(fieldName));
    }

    public String getData(int keyIndex, int fieldIndex) {
        if(fieldIndex>=data.get(keyIndex).length) {
            return null;
        }
        return data.get(keyIndex)[fieldIndex];
    }
    
    public String[] getRow(String key) {
        return data.get(keyDataMap.get(key));
    }
    
    public String[] getRow(int index) {
        return data.get(index);
    }
    
    public boolean containsKey(String key) {
        return keyDataMap.containsKey(key);
    }
    
    // iterate
    public int size() {
        return data.size();
    }
    
    public void first() {
        index=-1;
    }
    
    public boolean next() {
        index++;
        return index<size();
    }
    
    public String getData(String fieldName) {
        return getData(fieldIndex.get(fieldName));
    }
    
    public String getData(int fieldIndex) {
        if(fieldIndex>=data.get(index).length) {
            return null;
        }
        return data.get(index)[fieldIndex];
    }
}
