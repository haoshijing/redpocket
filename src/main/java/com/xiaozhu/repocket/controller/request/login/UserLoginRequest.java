package com.xiaozhu.repocket.controller.request.login;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String name;
    private String password;
}
