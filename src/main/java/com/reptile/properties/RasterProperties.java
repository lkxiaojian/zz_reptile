package com.reptile.properties;


import com.reptile.config.BaseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RasterProperties extends BaseProperties {

    @Value("${rasterProp.path}")
    private String rasterPropPath;
    /*
    {
        try {
            InputStream fis = new FileInputStream(rasterPropPath);
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @PostConstruct
    public void init() {
        init(rasterPropPath);
    }

}
