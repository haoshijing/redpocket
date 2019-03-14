package com.xiaozhu.repocket.controller.request.login;

import lombok.Data;

@Data
public class UserUpdatePwdRequest {
    private String newPwd;
    private String oldPwd;
}
