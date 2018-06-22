package com.kingguanzhang.toptalk.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name ;
    private String time;
    private String money;
    private String location;//活动具体场所
    private String topic;//活动主题;
    private String content;

    @ManyToOne(optional = false)
    private City city;//活动城市地点

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    /*@JoinColumn(name = "event_id")*/
    private List<Photo> photos;//包括缩略图和内容图;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public City getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonBackReference
    public void setCity(City city) {
        this.city = city;
    }
}
