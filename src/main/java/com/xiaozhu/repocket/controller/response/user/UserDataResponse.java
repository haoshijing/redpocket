package com.xiaozhu.repocket.controller.response.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDataResponse {
    private List<String> roles;
    private String avatar;
    private String name;
    private String introduction;
}
