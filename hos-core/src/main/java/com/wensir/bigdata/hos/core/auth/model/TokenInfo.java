package com.wensir.bigdata.hos.core.auth.model;

import com.wensir.bigdata.hos.core.HosCoreUtil;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
public class TokenInfo {
    private String token;
    private int expireTime;
    private Date refreshTime;
    private Date createTime;
    private boolean active;
    private String creator;

    public TokenInfo( String creator) {
        this.token = HosCoreUtil.getUUIDStr();
        this.expireTime = 7;
        Date date = new Date();
        this.refreshTime = date;
        this.createTime = date;
        this.active = true;
        this.creator = creator;
    }
}
