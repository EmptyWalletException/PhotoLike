package com.kingguanzhang.toptalk.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String content;
    private Date creatTime;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Story story;

    @ManyToOne(optional = false)
    private Topic topic;

    private Long supcommentId;//上级评论的ID;

    /**
     * 对自身的一对多关系,通常出现在有下级菜单或下级评论的情况下;建议使用第三张表关联来注入下级评论;这里使用自身映射来注入;
     */
    @OneToMany(mappedBy = "supcommentId",cascade = CascadeType.ALL)
    private List<Comment> subComments;//下级评论;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getAuthor() {
        return author;
    }
    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getSupcommentId() {
        return supcommentId;
    }

    public void setSupcommentId(Long supcommentId) {
        this.supcommentId = supcommentId;
    }

    public Story getStory() {
        return story;
    }
    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setStory(Story story) {
        this.story = story;
    }

    public Topic getTopic() {
        return topic;
    }
    @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Comment> subComments) {
        this.subComments = subComments;
    }
}
