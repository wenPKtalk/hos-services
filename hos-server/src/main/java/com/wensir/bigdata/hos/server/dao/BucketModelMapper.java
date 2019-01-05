package com.wensir.bigdata.hos.server.dao;

import com.wensir.bigdata.hos.server.BucketModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * <br>
 * 〈Mapper层〉
 *
 * @author wensir
 * @create 2019/1/4
 * @since 1.0.0
 */
@Mapper
public interface BucketModelMapper {

    //增加bucket
    void addBucket(@Param("bucketModel") BucketModel bucketModel);

    int updateBucket(@Param("bucketName") String bucketName, @Param("detail") String detail);

    int deleteBucket(@Param("bucketName") String bucketName);

    @ResultMap("BucketResultMap")
    BucketModel getBucket(@Param("bucketId") String bucketId);

    @ResultMap("BucketResultMap")
    BucketModel getBucketByName(@Param("bucketName") String bucketName);

    //查询用户创建的bucket
    @ResultMap("BucketResultMap")
    List<BucketModel> getBucketByCreator(@Param("creator") String creator);
    //根据token来查看该权限下的bucket
    @ResultMap("BucketResultMap")
    List<BucketModel> getUserAuthorizedBuckets(@Param("token") String token);

}