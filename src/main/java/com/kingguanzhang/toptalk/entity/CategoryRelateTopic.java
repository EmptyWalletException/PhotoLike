package com.kingguanzhang.toptalk.entity;

import javax.persistence.*;

/**
 * 用于记录分类和专题之间的关系;
 */
@Entity
@Table(name = "category_topic")
public class CategoryRelateTopic {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long categoryId;
    private long topicId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}