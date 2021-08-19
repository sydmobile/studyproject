package com.study.entity;

import java.io.Serializable;

/**
 * 说明：微信公众号文章对象
 * <p>
 * date: 2020/1/6 11:56
 *
 * @author syd
 * @version 1.0
 */
public class WXArticle  {

    // 作者
    private String author;
    // 公众号 id
    private int chapterId;
    // 公众号作者
    private String chapterName;
    // 文章 id
    private int id;
    // 文章链接
    private String link;
    // 发布日期
    private String niceDate;
    // 发布日期
    private long publishTime;
    // title
    private String title;
    // 类型
    private int type;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
