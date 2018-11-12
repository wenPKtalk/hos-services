package com.wensir.bigdata.hos.core.user.model;

import com.wensir.bigdata.hos.core.HosCoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userId;
    private String userName;
    private String password;
    private String detail;
    private SystemRole systemRole;
    private Date createDate;

    public UserInfo(String userName, String password, String detail, SystemRole systemRole) {
        this.userId = HosCoreUtil.getUUIDStr();
        this.userName = userName;
        this.password = HosCoreUtil.getMD5Str(password);
        this.detail = detail;
        this.systemRole = systemRole;
        this.createDate = new Date();
    }
}
