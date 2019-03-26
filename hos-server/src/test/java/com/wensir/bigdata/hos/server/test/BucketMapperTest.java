package com.wensir.bigdata.hos.server.test;


import com.wensir.bigdata.hos.core.auth.model.ServiceAuth;
import com.wensir.bigdata.hos.core.auth.service.IAuthService;
import com.wensir.bigdata.hos.core.user.model.SystemRole;
import com.wensir.bigdata.hos.core.user.model.UserInfo;
import com.wensir.bigdata.hos.core.user.service.IUserInfoService;
import com.wensir.bigdata.hos.server.BucketModel;
import com.wensir.bigdata.hos.server.dao.BucketModelMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.wensir.bigdata.hos.mybatis.test.BaseTest;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;


public class BucketMapperTest extends BaseTest {

    @Autowired
    BucketModelMapper bucketMapper;
    @Autowired
    @Qualifier("authServiceImpl")
    IAuthService authService;
    @Autowired
    @Qualifier("userServiceImpl")
    IUserInfoService userService;

    @Test
    public void addBucket() {
        BucketModel bucketModel = new BucketModel("test1", "jixin", "");
        bucketMapper.addBucket(bucketModel);
        UserInfo userInfo = new UserInfo("jixin", "123456", "", SystemRole.ADMIN);
        userService.addUser(userInfo);
        ServiceAuth serviceAuth = new ServiceAuth();
        serviceAuth.setTargetToken(userInfo.getUserId());
        serviceAuth.setBucketName(bucketModel.getBucketName());
        authService.addAuth(serviceAuth);
        BucketModel bucketModel2 = new BucketModel("test2", "jixin", "");
        bucketMapper.addBucket(bucketModel2);
    }

    @Test
    public void getBucket() {
        BucketModel bucketModel = bucketMapper.getBucketByName("test1");
        System.out.println(bucketModel.getBucketId() + "|" + bucketModel.getBucketName());
    }

    @Test
    public void getUserAuthorizedBuckets() {
        UserInfo userInfo = userService.getUserInfoByName("jixin");
        List<BucketModel> bucketModels = bucketMapper.getUserAuthorizedBuckets(userInfo.getUserId());
        bucketModels.forEach(bucketModel -> {
            System.out.println(bucketModel.getBucketId() + "|" + bucketModel.getBucketName());
        });
    }

    @Test
    public void deleteBucket() {
        UserInfo userInfo = userService.getUserInfoByName("jixin");
        List<BucketModel> bucketModels = bucketMapper.getUserAuthorizedBuckets(userInfo.getUserId());
        bucketModels.forEach(bucketModel -> {
            bucketMapper.deleteBucket(bucketModel.getBucketId());
        });
    }
}
