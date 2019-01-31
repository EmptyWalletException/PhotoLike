package com.kingguanzhang.toptalk.entity;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kingguanzhang.toptalk.controller.Category;

@Entity

@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private Date creatTime;
    private Integer status;//稿件状态,为0时代表稿件正在审核,当为1时则稿件正常展示,为2时表示稿件被隐藏,3代表稿件被管理员退回,4代表稿件被放置于回收站;
    private String info;//管理员审核时的留言,用于提示用户此稿件退稿的原因;
    private long collectNumber;
    private long commentNumber;
    private String coverImgAddr;
    private String contentImgsAddr;//这是topic里所有内容图片的地址拼接成的字符串;方便以后数据库中取出后分隔;
    private String zipAddr;//这是新增的内容图片的zip压缩包路径;在页面上提供所有内容图片的打包下载;
    @Transient
    private List<String> imgAddrList; //这是方便储存分隔后的字符串的list,用于存在topic实体里发送给前端,不与数据库映射;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private User author;//废弃多对多及一对多级联功能后保留了此属性;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

   /* @ManyToOne(optional = false)
    private User author;*/

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

    public String getZipAddr() {
        return zipAddr;
    }

    public void setZipAddr(String zipAddr) {
        this.zipAddr = zipAddr;
    }

    /**
     * 多对多采用第三张表来关联,这里就采用自动生成第三张表的方式来配置
     *//*
    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "category_topic",
            joinColumns = {@JoinColumn(name = "topic_id")},//本类Category所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "category_id")}//另一个表Column与第三张表中的外键对应关系
    )
    private List<Category> categorys;//多对多关系;*/

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContentImgsAddr() {
        return contentImgsAddr;
    }

    public void setContentImgsAddr(String contentImgsAddr) {
        this.contentImgsAddr = contentImgsAddr;
    }

    public String getCoverImgAddr() {
        return coverImgAddr;
    }

    public void setCoverImgAddr(String coverImgAddr) {
        this.coverImgAddr = coverImgAddr;
    }

    public List<String> getImgAddrList() {
    	if (null != contentImgsAddr) {
    		String[] contentImgsAddrArry = contentImgsAddr.split(",");
    		List<String> imgAddrList = new ArrayList<>();
    		for (String string : contentImgsAddrArry) {
    			imgAddrList.add(string);
    		}
		}
        return imgAddrList;
    }

    public void setImgAddrList(List<String> imgAddrList) {
        this.imgAddrList = imgAddrList;
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

    /**
     * 在OneToMany关联关系的注解中,joinCloum(name="指定本类主键所对应的外表的外键名字"),
     * mappedBy="在关联的对方类中的关联字段名",如果在此类中设置了mappedBy则表示此类放弃维护;
     * 设置了mappedBy之后就不需要设置joinColumn了,二者只存其一;
     * 需要根据情况看是否放弃维护,也可以手动在删除时设置Photo表中关联字段为null;
     * 在此类中,不应该放弃维护,即:当删除Column时删除关联的Photo;
     * targetEntity通常用于当关联的实体类是一个接口时才设置;
     * 同时,hibernate会根据注解下面的类自动找到数据表,
     * 例如在此类中,List<Photo> 所关联的是photo类中的名为topicId的外键,
     * hibernate会根据List指定的泛型Photo类自动找到数据库中photo表的外键topicId
     */
   /* @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)//mappedBy = "topic_id",
//    @JoinColumn(name = "topic_id")//joinColumn会生在数据库表中生成一个新的列,而mappedBy则不会;
    private List<Photo> photos;//一对多关系*/

   /* @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)//,mappedBy = "topic_id"
//    @JoinColumn(name = "topicId")
    private List<Comment> comments;//评论;一对多关系

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "user_favorite",
            joinColumns = {@JoinColumn(name = "topic_id")},//本类topic所对应的表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "user_id")}//另一个表user与第三张表中的外键对应关系
    )
    List<User> users;//多对多;收藏了此专栏的用户;

    //特别注意,如果多对多关系对象转为JSON数据的时候，为了防止出现无限循环包含对方，需要在多的一方的引用少的一方对象的set方法（Set对象的set方法）上写上注解@JsonBackReference
    //在此类中属于多的一方,所以需要标注@JsonBackReference
    @JsonBackReference
    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }*/



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


   /* public User getAuthor() {
        return author;
    }
   // @JsonBackReference //防止转json的时候出现无线循环包含的情况,只标注在关系中较多的一方的引用对方的set方法上;
    public void setAuthor(User author) {
        this.author = author;
    }*/
/*
    public List<Category> getCategorys() {
        return categorys;
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
  /* public List<Photo> getPhotos() {
       return photos;
   }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }*/

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creatTime=" + creatTime +
                ", status=" + status +
                ", info='" + info + '\'' +
                ", collectNumber=" + collectNumber +
                ", commentNumber=" + commentNumber +
                ", coverImgAddr='" + coverImgAddr + '\'' +
                ", contentImgsAddr='" + contentImgsAddr + '\'' +
                ", zipAddr='" + zipAddr + '\'' +
                ", imgAddrList=" + imgAddrList +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}
