package com.kingguanzhang.toptalk.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private Integer rank;//用于排序;
    /**
     * 多对多采用第三张表来关联,这里就采用自动生成第三张表的方式来配置
     */
    @ManyToMany
    @Cascade(value = CascadeType.SAVE_UPDATE)//分类删除时不删除关联的专栏
    @JoinTable(name = "category_topic",
            joinColumns = {@JoinColumn(name = "category_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}//另一个表Column与第三张表中的外键对应关系
    )
    private List<Topic> topics;//多对多;

    //特别注意,如果多对多关系对象转为JSON数据的时候，为了防止出现无限循环包含对方，需要在多的一方的引用少的一方对象的set方法（Set对象的set方法）上写上注解@JsonBackReference
    //在此类中属于少的一方,所以不需要标注@JsonBackReference
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Topic> getTopics() {
        return topics;
    }


}
