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
