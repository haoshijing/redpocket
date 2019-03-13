package com.xiaozhu.repocket.po;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "rmoney_change_records")
@Entity
@ToString
public class RmoneyChangeRecordsPo implements Serializable
{
    @Column(name = "DateTime")
    @Id
    private String DateTime;

    @Column(name = "Guid")
    private Long guid;

    @Column(name = "Nick")
    private String Nick;

    @Column(name = "Reason")
    private String Reason;

    @Column(name = "Changes")
    private Long Changes;

    @Column(name = "Remains")
    private Long Remains;
}
