package com.wensir.bigdata.hos.server;

import com.wensir.bigdata.hos.common.BucketModel;
import com.wensir.bigdata.hos.core.auth.model.ServiceAuth;
import com.wensir.bigdata.hos.core.auth.service.IAuthService;
import com.wensir.bigdata.hos.core.user.model.UserInfo;
import com.wensir.bigdata.hos.server.dao.BucketModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <br>
 * 〈BucketService业务逻辑类〉
 *
 * @author wensir
 * @create 2019/1/4
 * @since 1.0.0
 */
@Service
@Component
@Transactional
public class BucketServiceImpl implements IBucketService {
    @Autowired
    BucketModelMapper bucketModelMapper;

    @Autowired
    @Qualifier("authServiceImpl")
    IAuthService authService;

    @Override
    public boolean addBucket(UserInfo userInfo, String bucketName, String detail) {

        BucketModel bucketModel = new BucketModel(bucketName, userInfo.getUserName(), detail);
        bucketModelMapper.addBucket(bucketModel);
        //为bucket和user添加权限
        ServiceAuth serviceAuth = new ServiceAuth();
        serviceAuth.setAuthTime(new Date());
        serviceAuth.setBucketName(bucketName);
        serviceAuth.setTargetToken(userInfo.getUserId());
        authService.addAuth(serviceAuth);
        return true;
    }

    @Override
    public boolean deleteBucket(String bucketName) {
        bucketModelMapper.deleteBucket(bucketName);
        authService.deleteAuthByBucket(bucketName);
        return true;
    }

    @Override
    public boolean updateBucket(String bucketName, String detail) {
        bucketModelMapper.updateBucket(bucketName, detail);
        return true;
    }

    @Override
    public BucketModel getBucketById(String bucketId) {
        return bucketModelMapper.getBucket(bucketId);
    }

    @Override
    public BucketModel getBucketByName(String bucketName) {
        return bucketModelMapper.getBucketByName(bucketName);
    }

    @Override
    public List<BucketModel> getUserBuckets(String token) {
        return bucketModelMapper.getUserAuthorizedBuckets(token);
    }
}