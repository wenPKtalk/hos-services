package com.wensir.bigdata.hos.core.user.service.impl;

import com.google.common.base.Strings;
import com.wensir.bigdata.hos.core.HosCoreUtil;
import com.wensir.bigdata.hos.core.user.dao.UserInfoMapper;
import com.wensir.bigdata.hos.core.user.model.UserInfo;
import com.wensir.bigdata.hos.core.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements IUserInfoService {
    private long LONG_REFRESH_TIME = 4670409600000L;
    private int LONG_EXPIRE_TIME = 36500;

    @Autowired
    UserInfoMapper userInfoMapper;
/*
    @Autowired
    TokenInfoMapper tokenInfoMapper;*/

    @Override
    public boolean addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
        return true;
    }

    @Override
    public boolean updateUserInfo(String userId, String password, String detail) {
        userInfoMapper
                .updateUserInfo(userId,
                        Strings.isNullOrEmpty(password) ? null : HosCoreUtil.getMD5Str(password),
                        Strings.emptyToNull(detail));
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        userInfoMapper.deleteUser(userId);
        //todo delete token
//        tokenInfoMapper.deleteToken(userId);
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userInfoMapper.getUserInfo(userId);
    }

    @Override
    public UserInfo checkPassword(String userName, String password) {
        return userInfoMapper.checkPassword(userName, password);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        return userInfoMapper.getUserInfoByName(userName);
    }

}
