package com.wensir.bigdata.hos.core.auth.model;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAuth {
    private String bucketName;
    private String targetToken;
    private Date authTime;
}
