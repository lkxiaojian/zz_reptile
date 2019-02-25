package com.reptile.config;


import java.io.*;
import java.util.*;

/**
 * Created by lk on 18/06/20.
 */
public class BaseProperties {
    private Properties prop = new Properties();
    private String propetiesPath;

    public void init(String propetiesPath) {
        this.propetiesPath = propetiesPath;
        try {
            InputStream fis = new FileInputStream(propetiesPath);
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<String> getProperty(Integer visibleNum) {
        List<String> retList = new ArrayList();
        try {
            for (int i = 0; i < visibleNum; i++) {
                if (prop.getProperty(i + "") != null) {
                    //System.out.println("key="+i+",value="+prop.getProperty(i+""));
                    retList.add(prop.getProperty(i + ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }
    public Map<String,String> getPropertyAll(){
        Map<String,String> allProperty = new HashMap<String, String>((Map) prop);
        return  allProperty;
    }

    public void setProp(Map<String, String> map) {
        for (String key : map.keySet()) {
            prop.setProperty(key, map.get(key));
        }
        serializedToDisk();
    }
    public void setProp(String key,String value) {
        prop.setProperty(key, value);
        serializedToDisk();
    }
    public Object removeProp(String key) {
        Object value = null;
        if (key != null) {
            value = prop.remove(key);
        }
        serializedToDisk();
        return value;
    }
    private void serializedToDisk() {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(propetiesPath);
            prop.store(fos, "");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getPropValue(String key) {
        return prop.getProperty(key);
    }

    public int countLine() {
        return prop.size();
    }
}
