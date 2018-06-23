package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name ;
    private Date time;
    private String money;
    private String location;//活动具体场所
    private String theme;//活动主题;
    private String content;

    @ManyToOne(optional = false)
    private City city;//废弃级联功能时保留了此属性;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    /* @ManyToOne(optional = false)
    private City city;//活动城市地点

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    *//*@JoinColumn(name = "event_id")*//*
    private List<Photo> photos;//包括缩略图和内容图;*/



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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

  /*  public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public City getCity() {
        return city;
    }
    @JsonBackReference
    public void setCity(City city) {
        this.city = city;
    }
*/
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
