/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.HashMap;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2015年7月28日
 */
public class LocalizationHelper {
    private static LocalizationHelper instance = null;
    private static final Integer LOCK = new Integer(0);
    
    private HashMap<String,DataLoader> localizationMap;
    /**
     * 
     */
    private LocalizationHelper() {
        init();
    }

    public static LocalizationHelper getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LocalizationHelper();
                }
            }
        }
        return instance;
    }
    
    public void init() {
        localizationMap = new HashMap<String,DataLoader>();
        loadData();
    }
    
    private void loadData() {
        DataLoader endata = new DataLoader();
        endata.loadData(new File("/data/marvel/mffdata/Localization_en.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        localizationMap.put("en", endata);
        
        DataLoader twdata = new DataLoader();
        twdata.loadData(new File("/data/marvel/mffdata/Localization_zh.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        localizationMap.put("zhtw", twdata);
        
        DataLoader jpdata = new DataLoader();
        jpdata.loadData(new File("/data/marvel/mffdata/Localization_ja.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        localizationMap.put("jp", jpdata);
        
        DataLoader kodata = new DataLoader();
        kodata.loadData(new File("/data/marvel/mffdata/Localization_ko.csv.txt.decode.txt"), "UTF-8", "\t", 0);
        localizationMap.put("ko", kodata);
    }
    
    public String getText(String language, String key) {
        if(language==null) {
            language="en";
        }
        return localizationMap.get(language).getData(key, "TEXT");
    }
}

