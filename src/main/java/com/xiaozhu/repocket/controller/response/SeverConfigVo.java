package com.xiaozhu.repocket.controller.response;

import lombok.Data;

@Data
public class SeverConfigVo {
    private String address;
    private String version;
    private String updateMessage;
    private String notice;
    private String scrollMessage;
    private String androidUpdateUrl;
    private String iOSUpdateUrl;
    private String soundUpLoadUrl;
    private String soundDownLoadUrl;
    private String wXShareUrl;
    private String resourceDownLoadUrl;
    private Integer createDefaultMoney;
    private Integer onlineCount;
    private Integer registerCount;
}
