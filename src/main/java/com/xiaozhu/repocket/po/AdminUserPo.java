package com.xiaozhu.repocket.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String createId;
    private Long lastUpdateTime;
    private String lastOperator;
    private Long lastLoginTime;
}