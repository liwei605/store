package com.cqu.store.entity;

public class Favorite extends BaseEntity{
    private  Integer fid;
    private  Integer uid;
    private  Integer pid;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", pid=" + pid +
                '}';
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

    public Favorite() {

    }

    public Favorite(Integer fid, Integer uid, Integer pid) {

        this.fid = fid;
        this.uid = uid;
        this.pid = pid;
    }
}
