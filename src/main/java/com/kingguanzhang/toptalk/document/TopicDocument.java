package com.kingguanzhang.toptalk.document;

import com.kingguanzhang.toptalk.entity.Category;
import com.kingguanzhang.toptalk.entity.User;

import java.util.Date;
import java.util.List;

//@Document(indexName = "topicdoc",type = "normal")
public class TopicDocument {

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
    private List<String> imgAddrList; //这是方便储存分隔后的字符串的list,用于存在topic实体里发送给前端,不与数据库映射;
    private Category category;
    private User author;//废弃多对多及一对多级联功能后保留了此属性;

    @Override
    public String toString() {
        return "TopicDocument{" +
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getCoverImgAddr() {
        return coverImgAddr;
    }

    public void setCoverImgAddr(String coverImgAddr) {
        this.coverImgAddr = coverImgAddr;
    }

    public String getContentImgsAddr() {
        return contentImgsAddr;
    }

    public void setContentImgsAddr(String contentImgsAddr) {
        this.contentImgsAddr = contentImgsAddr;
    }

    public String getZipAddr() {
        return zipAddr;
    }

    public void setZipAddr(String zipAddr) {
        this.zipAddr = zipAddr;
    }

    public List<String> getImgAddrList() {
        return imgAddrList;
    }

    public void setImgAddrList(List<String> imgAddrList) {
        this.imgAddrList = imgAddrList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
