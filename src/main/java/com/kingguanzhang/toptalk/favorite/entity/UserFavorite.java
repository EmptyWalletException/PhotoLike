package com.kingguanzhang.toptalk.favorite.entity;

import javax.persistence.*;

/**
 * 用于记录用户的收藏;
 */
@Entity
@Table(name = "user_favorite")
public class UserFavorite {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long essayId;
    private long storyId;
    private long topicId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
