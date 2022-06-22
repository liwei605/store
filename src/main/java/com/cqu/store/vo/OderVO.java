package com.cqu.store.vo;

import com.cqu.store.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OderVO extends BaseEntity implements Serializable {
    private String O_st_id;
    private String recv_name;
    private String creat_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OderVO)) return false;
        OderVO oderVO = (OderVO) o;
        return Objects.equals(getO_st_id(), oderVO.getO_st_id()) && Objects.equals(getRecv_name(), oderVO.getRecv_name()) && Objects.equals(getCreat_time(), oderVO.getCreat_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getO_st_id(), getRecv_name(), getCreat_time());
    }

    @Override
    public String toString() {
        return "OderVO{" +
                "O_st_id='" + O_st_id + '\'' +
                ", recv_name='" + recv_name + '\'' +
                ", creat_time=" + creat_time +
                '}';
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getRecv_name() {
        return recv_name;
    }

    public void setRecv_name(String recv_name) {
        this.recv_name = recv_name;
    }

    public String getO_st_id() {
        return O_st_id;
    }

    public void setO_st_id(String o_st_id) {
        O_st_id = o_st_id;
    }

}
