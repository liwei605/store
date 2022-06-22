package com.cqu.store.vo;

import java.io.Serializable;

public class FavoriteVO implements Serializable {
    private Integer fid;
    private  Integer uid;
    private  Integer pid;
    private String title;//…Ã∆∑title
    private Long price;
    private String image;

    @Override
    public String toString() {
        return "FavoriteVO{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FavoriteVO(Integer fid, Integer uid, Integer pid, String title, Long price, String image) {

        this.fid = fid;
        this.uid = uid;
        this.pid = pid;
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public FavoriteVO() {

    }
}
