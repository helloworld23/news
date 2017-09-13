package com.news.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by zhongziming on 2017/5/5.
 *
 * @describe 文章类
 */
@Entity
public class Article {
    private int id;
    private String title;//标题
    private String category;//类别
    private String content;//内容
    private String author;//作者
    private String createTime;//创建时间
    private String createTime2;//页面显示创建时间
    private String lastModifyTime2;//页面显示最近修改时间
    private String lastModifyTime;//最近修改时间
    private String thumbnail;//缩略文
    private int status = 2;//审核状态，0审核不通过，1审核通过，2未审核
    private String imgLocation;
    private List<Comment> comments;
    private Hits hits;
    public Article(){}
    public void setLastModifyTime2(String lastModifyTime2){
        this.lastModifyTime2 = lastModifyTime2;
    }
    @Transient
    public String getLastModifyTime2(){
        return this.lastModifyTime2;
    }
    public void setCreateTime2(String createTime2){
        this.createTime2 = createTime2;
    }
    @Transient
    public String getCreateTime2(){
        return this.createTime2;
    }
    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
    public String getLastModifyTime() {

        return lastModifyTime;
    }
    public void setHits(Hits hits) {
        this.hits = hits;
    }
    @OneToOne(mappedBy = "article",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Hits getHits() {
        return hits;
    }

    @OneToMany(mappedBy = "article",fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Comment> getComments(){
        return comments;
    }
    public void setComments(List<Comment> comments){
        this.comments = comments;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {

        return id;
    }

    public void setId(int id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }
}
