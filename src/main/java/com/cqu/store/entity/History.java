package com.cqu.store.entity;

public class History extends BaseEntity{
    private  Integer hid;
    private  Integer uid;
    private  Integer pid;

    @Override
    public String toString() {
        return "History{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", pid=" + pid +
                '}';
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
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

    public History() {

    }

    public History(Integer hid, Integer uid, Integer pid) {

        this.hid = hid;
        this.uid = uid;
        this.pid = pid;
    }
}
