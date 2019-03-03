package com.xiaozhu.repocket.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "admin")
@Entity
public class AdminUserPo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private Long createTime;
    private Integer createId;
    private Long lastUpdateTime;
    private Integer lastOperator;
    private Long lastLoginTime;
}