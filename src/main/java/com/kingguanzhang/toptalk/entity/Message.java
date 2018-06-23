package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 站内消息,派发给所有用户;
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String content;
    private Date creatTime;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
