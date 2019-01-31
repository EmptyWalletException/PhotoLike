package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;

/**
 * 用于记录评论所属的是 随笔 故事 专题 这三个中的哪一个;
 */
@Entity
@Table(name = "comment_est")
public class CommentRelateEST {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long essayId;
    private long storyId;
    private long topicId;
    private long commentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEssayId() {
        return essayId;
    }

    public void setEssayId(long essayId) {
        this.essayId = essayId;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
}
