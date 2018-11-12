package com.wensir.bigdata.hos.core.user.service;

import com.wensir.bigdata.hos.core.user.model.UserInfo;

public interface IUserInfoService {

    public boolean addUser(UserInfo userInfo);

    public boolean updateUserInfo(String userId, String password, String detail);

    public boolean deleteUser(String userId);

    public UserInfo getUserInfo(String userId);

    public UserInfo checkPassword(String userName, String password);

    public UserInfo getUserInfoByName(String userName);
}
