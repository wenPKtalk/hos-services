package com.wensir.bigdata.hos.server;

import com.wensir.bigdata.hos.core.user.model.UserInfo;

import java.util.List;

/**
 * <br>
 * 〈BucketService接口层〉
 *
 * @author wensir
 * @create 2019/1/4
 * @since 1.0.0
 */
public interface IBucketService {
    public boolean addBucket(UserInfo userInfo, String bucketName, String detail);

    public boolean deleteBucket(String bucketName);

    public boolean updateBucket(String bucketName, String detail);

    public BucketModel getBucketById(String bucketId);

    public BucketModel getBucketByName(String bucketName);

    public List<BucketModel> getUserBuckets(String token);

}