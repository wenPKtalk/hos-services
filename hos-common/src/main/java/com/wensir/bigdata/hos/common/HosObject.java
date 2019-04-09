package com.wensir.bigdata.hos.common;

import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * <br>
 * 〈Hos对象实体〉
 *
 * @author wensir
 * @create 2019/4/8
 * @since 1.0.0
 */
public class HosObject {

    private ObjectMetaData metaData; //描述信息

    private InputStream content;

    private Response response; //提供给sdk模块使用

    public HosObject() {

    }

    public HosObject(Response response) {
        this.response = response;
    }

    public void close() {
        try {
            if (content != null) {
                this.content.close();
            }
            if (response != null) {
                this.response.close();
            }
        } catch (IOException ioe) {
            //nothing to do
        }
    }

    public ObjectMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(ObjectMetaData metaData) {
        this.metaData = metaData;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

}