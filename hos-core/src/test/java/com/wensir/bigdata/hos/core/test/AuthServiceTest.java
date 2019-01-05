package com.wensir.bigdata.hos.core.test;

import com.wensir.bigdata.hos.core.auth.model.TokenInfo;
import com.wensir.bigdata.hos.core.auth.service.IAuthService;
import com.wensir.bigdata.hos.mybatis.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * <br>
 * 〈AuthService单元测试〉
 *
 * @author wensir
 * @create 2018/11/12
 * @since 1.0.0
 */
public class AuthServiceTest extends BaseTest {

    @Autowired
    @Qualifier("authServiceImpl")
    private IAuthService authService;


    @Test
    public void addToke() {
        TokenInfo tokenInfo = new TokenInfo("tom");
        authService.addToken(tokenInfo);

    }

    @Test
    public void getTokenByUser() {
        List<TokenInfo> tokenInfos = authService.getTokenInfos("tom");
        tokenInfos.forEach(item -> System.out.println(item.getToken()));
    }


}