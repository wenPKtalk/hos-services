package com.wensir.bigdata.hos.core;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 集中管理所有的配置文件
 * 加载所有的properties键值对
 */
public class HosConfiguration {

    private static HosConfiguration hosConfiguration;

    private static Properties properties;

    static {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        hosConfiguration = new HosConfiguration();
        hosConfiguration.properties = new Properties();
        try {
            //读取所有的propeties
            Resource[] resources = resourcePatternResolver.getResources("classpath:*.properties");
            for (Resource resource : resources) {
                Properties prop = new Properties();
                InputStream inputStream = resource.getInputStream();
                prop.load(inputStream);
                inputStream.close();
                hosConfiguration.properties.putAll(prop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HosConfiguration() {

    }

    public static HosConfiguration getHosConfiguration() {
        return hosConfiguration;
    }

    public String getString(String key) {
        return properties.get(key).toString();
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.get(key).toString());

    }

}
