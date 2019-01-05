package com.wensir.bigdata.hos.server;

import com.sun.media.jfxmediaimpl.HostUtils;
import com.wensir.bigdata.hos.core.HosCoreUtil;
import lombok.Data;

import java.util.Date;

/**
 * <br>
 * 〈BucketModel〉
 *
 * @author wensir
 * @create 2019/1/4
 * @since 1.0.0
 */
@Data
public class BucketModel {

    private String bucketId;
    private String bucketName;
    private String creator;
    private String detail;
    private Date createTime;

    public BucketModel() {
    }

    public BucketModel(String bucketName, String creator, String detail) {
        this.bucketId = HosCoreUtil.getUUIDStr();
        this.bucketName = bucketName;
        this.creator = creator;
        this.detail = detail;
        this.createTime = new Date();
    }
}