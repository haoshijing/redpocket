package com.xiaozhu.repocket.service.auth;

import lombok.Data;

@Data
public class AdminAuthInfo {
    private String userName;
    private Integer level;
    private String token;
    private Long lastActiveTime;
}
