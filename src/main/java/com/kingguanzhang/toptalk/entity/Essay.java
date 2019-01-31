package com.kingguanzhang.toptalk.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "essay")
public class Essay {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private  String title;
    private Integer status;//稿件状态,为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
    private String info;//管理员审核时的留言,用于提示用户此稿件退稿的原因;
    private String content;
    private Date creatTime;
    private String imgAddr;
    private long collectNumber;


    @ManyToOne(optional = false)
    private User author;//废弃多对多及一对多级联功能后保留了此属性;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
   /* @ManyToOne(optional = false)
    private User author;

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
            joinColumns = {@JoinColumn(name="essay_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "user_id")}//另一个表Column与第三张表中的外键对应关系
    )
    List<User> users;//多对多;收藏了此随笔的用户;*/

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

  /*  public User getAuthor() {
        return author;
    }
    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/

    public long getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(long collectNumber) {
        this.collectNumber = collectNumber;
    }
}
