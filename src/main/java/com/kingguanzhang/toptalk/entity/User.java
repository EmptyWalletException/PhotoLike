package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String account;//邮箱格式;
    private String password;
    private String nickname;
    private String imgAddr;
    private String signature;//签名
    private Date joinTime;
    private Integer gender;
    private String location;//居住地

    /**
     * 关于一对多与多对一;
    *story表相对于user表是主控方，在主控方story类只需写@ManyToOne即可，其下面一行的属性名为被控表user中mappedBy中的值。
     *//*
    @OneToMany(mappedBy = "author")
    private List<Story> createdStroy;//创作的故事

    @OneToMany(mappedBy = "author")
    private List<Essay> createdEssay;//创作的随笔

    @OneToMany(mappedBy = "author")
    private List<Topic> createdTopic;//创作的专栏

    @OneToMany(mappedBy = "author")
    private List<Comment> createdComment;//创作的评论
*/

    /**
     * 多对多采用第三张表来关联,这里就采用自动生成第三张表的方式来配置
    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
            joinColumns = {@JoinColumn(name="user_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "essay_id")}//另一个表Column与第三张表中的外键对应关系
    )
    private List<Essay> essay;//一对多关系

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
            joinColumns = {@JoinColumn(name="user_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}//另一个表Column与第三张表中的外键对应关系
    )
    private List<Topic> Topic;//一对多关系

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
            joinColumns = {@JoinColumn(name="user_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "story_id")}//另一个表Column与第三张表中的外键对应关系
    )
    private List<Story> story;//一对多关系*/


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*public List<Essay> getEssay() {
        return essay;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setEssay(List<Essay> essay) {
        this.essay = essay;
    }*/


   /* public List<Topic> getTopic() {
        return Topic;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setTopic(List<Topic> topic) {
        Topic = topic;
    }

    public List<Story> getStory() {
        return story;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setStory(List<Story> story) {
        this.story = story;
    }

    public List<Story> getCreatedStroy() {
        return createdStroy;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setCreatedStroy(List<Story> createdStroy) {
        this.createdStroy = createdStroy;
    }

    public List<Essay> getCreatedEssay() {
        return createdEssay;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setCreatedEssay(List<Essay> createdEssay) {
        this.createdEssay = createdEssay;
    }

    public List<Topic> getCreatedTopic() {
        return createdTopic;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setCreatedTopic(List<Topic> createdTopic) {
        this.createdTopic = createdTopic;
    }

    public List<Comment> getCreatedComment() {
        return createdComment;
    }

    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在多对多关系中较多的一方的引用对方的set方法上;
    public void setCreatedComment(List<Comment> createdComment) {
        this.createdComment = createdComment;
    }*/
}
