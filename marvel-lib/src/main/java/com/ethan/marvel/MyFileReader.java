/**
 * 
 */
package com.ethan.marvel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Ethan Tsui
 * created date: 2015年7月8日
 */
public class MyFileReader {

    /**
     * 
     */
    public MyFileReader() {
        
    }
    
    
    public void readLine(File file, ILineProcessor lineProcessor) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() < 3 || line.startsWith("#")) {
                continue;
            }
            
            lineProcessor.processLine(line);
            
        }
        br.close();
    }
}
