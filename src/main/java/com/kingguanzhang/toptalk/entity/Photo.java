package com.kingguanzhang.toptalk.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String imgAddr;
    private Integer rank;//用于排序;

    @ManyToOne(optional = false)
    private Topic topic;

    @ManyToOne(optional = false)
    private Event event;

    @ManyToOne(optional = false)
    private Story story;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Topic getTopic() {
        return topic;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Event getEvent() {
        return event;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setEvent(Event event) {
        this.event = event;
    }

    public Story getStory() {
        return story;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setStory(Story story) {
        this.story = story;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }
}
