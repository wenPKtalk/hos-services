package com.wensir.bigdata.hos.core;

import com.wensir.bigdata.hos.core.user.model.SystemRole;
import com.wensir.bigdata.hos.core.user.model.UserInfo;
import com.wensir.bigdata.hos.core.user.service.IUserInfoService;
import com.wensir.bigdata.hos.mybatis.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

public class UserServiceTest extends BaseTest {
    @Autowired
    @Qualifier("userServiceImpl")
    private IUserInfoService iUserInfoService;

    public void addUserTest() {
        UserInfo userInfo = new UserInfo("tom", "123456", "test", SystemRole.USER);
        iUserInfoService.addUser(userInfo);
    }
}
