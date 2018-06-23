package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;
    private Date creatTime;
    private long collectNumber;
    private long commentNumber;

    @OneToMany(mappedBy = "story",cascade = CascadeType.ALL) //    @JoinColumn(name = "story_id")
    private List<Photo> photos;//一对多;

    @ManyToOne(optional = false)
    private User author;//废弃多对多及一对多级联功能后保留了此属性;

    /**
     * 关于一对多与多对一;
     *story表相对于user表是主控方，在主控方story类只需写@ManyToOne即可，其下面一行的属性名为被控表user中mappedBy中的值。
     */
   /* @OneToMany(mappedBy = "story",cascade = CascadeType.ALL)//    @JoinColumn(name = "story_id")
    private List<Comment> comments;//一对多关系

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
                joinColumns = {@JoinColumn(name="story_id")},//本类Category所对应的表与中间表的外键对应关系
                inverseJoinColumns = {@JoinColumn(name = "user_id")}//另一个表Column与第三张表中的外键对应关系
                 )
    List<User> users;//多对多;收藏了此故事的用户;*/


   /*  set  get  */


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /*public User getAuthor() {
        return author;
    }
    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setAuthor(User author) {
        this.author = author;
    }



    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(long collectNumber) {
        this.collectNumber = collectNumber;
    }

    public long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(long commentNumber) {
        this.commentNumber = commentNumber;
    }
}
