package com.xiaozhu.repocket.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "rmoney_change_records")
@Entity
@ToString
public class RmoneyChangeRecordsPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DateTime")
    private String dateTime;

    @Column(name = "Guid")
    private Long guid;

    @Column(name = "Nick")
    private String nick;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "Changes")
    private Long changes;

    @Column(name = "Remains")
    private Long remains;
}
