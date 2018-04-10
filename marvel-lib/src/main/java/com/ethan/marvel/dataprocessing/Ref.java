/**
 * 
 */
package com.ethan.marvel.dataprocessing;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author GBTW0011
 * created date: 2015年7月22日
 */
public class Ref {
    private String refName = null;
    private int count=0;
    private HashMap<String, Ref> children = new HashMap<String, Ref>();
    
    /**
     * 
     */
    public Ref(String refname) {
        this.refName=refname;
    }
    
    

    public void add(StringTokenizer st) {
        String str = st.nextToken();
        Ref child = null;
        if(children.containsKey(str)) {
            child = children.get(str);
        }
        else {
            child = new Ref(str);
            children.put(str, child);
        }
        
        count++;
        
        if(st.hasMoreTokens()) {
            child.add(st);
        }
        else {
            child.increase();
        }
        
        
        
    }

    public String getRefName() {
        return refName;
    }

    public Ref setRefName(String refName) {
        this.refName = refName;
        return this;
    }

    public int getCount() {
        return count;
    }

    public HashMap<String, Ref> getChildren() {
        return children;
    }

    public Ref setChildren(HashMap<String, Ref> children) {
        this.children = children;
        return this;
    }
    
    public void increase() {
        count++;
    }
    
    
}
