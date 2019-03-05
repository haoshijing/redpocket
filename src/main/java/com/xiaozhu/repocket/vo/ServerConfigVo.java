package com.xiaozhu.repocket.vo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;

@Data
@ToString
public class ServerConfigVo {
    private String Address;
    private String Version;
    private String updateMessage;
    private String notice;
    private String scrollMessage;
    private String androidUpdateUrl;
    private String iOSUpdateUrl;
    private String soundUpLoadUrl;
    private String soundDownLoadUrl;
    private String wXShareUrl;
    private String resourceDownLoadUrl;
    private Integer CreateDefaultMoney;
    private Integer onlineCount;
    private Integer registerCount;
}
