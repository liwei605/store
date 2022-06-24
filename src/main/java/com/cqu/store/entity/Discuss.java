package com.cqu.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class Discuss  extends BaseEntity implements Serializable {
    private Integer did;
    private Integer uid;
    private Integer pid;
    private String discussion;

    @Override
    public String toString() {
        return "Discuss{" +
                "did=" + did +
                ", uid=" + uid +
                ", pid=" + pid +
                ", discussion='" + discussion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discuss)) return false;
        Discuss discuss = (Discuss) o;
        return Objects.equals(getDid(), discuss.getDid()) &&
                Objects.equals(getUid(), discuss.getUid()) &&
                Objects.equals(getPid(), discuss.getPid()) &&
                Objects.equals(getDiscussion(), discuss.getDiscussion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDid(), getUid(), getPid(), getDiscussion());
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
}
