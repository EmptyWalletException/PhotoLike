package com.kingguanzhang.toptalk.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String content;
    private Date creatTime;
    private long supcommentId;//上级评论的ID;
    private long praiseNumber;//点赞数


    @ManyToOne(optional = false)
    private User author;//废弃多对多及一对多级联功能后保留了此属性;

    public User getAuthor() {
        return author;
    }
    //@JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;这注释掉是因为发现评论的json中author没有生成;
    public void setAuthor(User author) {
        this.author = author;
    }

    public long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    /**
     * 对自身的一对多关系,通常出现在有下级菜单或下级评论的情况下;建议使用第三张表关联来注入下级评论;这里使用自身映射来注入;
     * 最新的设计,废弃此级联,原因在于采用此方法无法在子评论区域分页并排序;建议在页面用js来实现;
     */
   /* @OneToMany(mappedBy = "supcommentId",cascade = CascadeType.ALL)
    private List<Comment> subComments;//下级评论;*/


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

    public long getSupcommentId() {
        return supcommentId;
    }

    public void setSupcommentId(long supcommentId) {
        this.supcommentId = supcommentId;
    }

}
