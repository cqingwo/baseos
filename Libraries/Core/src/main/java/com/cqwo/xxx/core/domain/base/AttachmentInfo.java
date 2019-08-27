package com.cqwo.xxx.core.domain.base;

import com.cqwo.xxx.core.helper.DateHelper;
import com.cqwo.xxx.core.helper.UnixTimeHelper;

import javax.persistence.*;
import java.io.Serializable;

//附件表
@Entity
@Table(name = "w_attachment")
public class AttachmentInfo implements Serializable {


    /**
     * 附件id
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachid")
    private Integer attachId = 0;

    /**
     * 用户uid
     **/
    @Column(name = "uid", nullable = false)
    private Integer uid = -1;

    /**
     * 附件地址
     **/
    @Column(name = "url", nullable = false)
    private String url = "";

    /**
     * 标题
     **/
    @Column(name = "title", nullable = false)
    private String title = "";

    /**
     * 创建时间
     **/
    @Column(name = "createtime", nullable = false)
    private Integer createTime = 0;

    public AttachmentInfo() {
    }

    public AttachmentInfo(Integer uid, String url, String title, Integer createTime) {
        this.uid = uid;
        this.url = url;
        this.title = title;
        this.createTime = createTime;
    }

    public AttachmentInfo(String url, int uid, String title) {
        this.uid = uid;
        this.url = url;
        this.title = title;
        this.createTime = UnixTimeHelper.getUnixTimeStamp();
    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AttachmentInfo{" +
                "attachId=" + attachId +
                ", uid=" + uid +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}