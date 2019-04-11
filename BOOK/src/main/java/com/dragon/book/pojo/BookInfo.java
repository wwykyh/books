package com.dragon.book.pojo;

import com.dragon.book.model.TBook;

import java.io.Serializable;

/**
 * @ClassNameBookInfo
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/29
 */
public class BookInfo extends TBook implements Serializable {
    @Override
    public String toString() {
        return "BookInfo{" +
                "id='" + id + '\'' +
                ", sxmc='" + sxmc + '\'' +
                ", uname='" + uname + '\'' +
                ", wz='" + wz + '\'' +
                ", sh=" + sh +
                ", rksj='" + rksj + '\'' +
                ", lxmc='" + lxmc + '\'' +
                ", xm='" + xm + '\'' +
                ", picture='" + picture + '\'' +
                ", jj='" + jj + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public String getJj() {
        return jj;
    }

    @Override
    public void setJj(String jj) {
        this.jj = jj;
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String id;
    private String sxmc;
    private String uname;
    private String wz;
    private Integer sh;
    private String rksj;
    private String lxmc;
    private String xm;
    private String picture;
    private String jj;
    private Integer status;
    private String jyxm;

    public String getJyxm() {
        return jyxm;
    }

    public void setJyxm(String jyxm) {
        this.jyxm = jyxm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSxmc() {
        return sxmc;
    }

    public void setSxmc(String sxmc) {
        this.sxmc = sxmc;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getSh() {
        if (this.sh == 0) {
            return "无损毁";
        } else if (this.sh == 1) {
            return "轻微损毁";
        } else if (this.sh == 2) {
            return "中度损毁";
        } else {
            return "重度损毁";
        }
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }

    public String getRksj() {
        return rksj;
    }

    public void setRksj(String rksj) {
        this.rksj = rksj;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getStatus() {
        String result= "";
        if (this.status!=null){
            if (this.status == 1) {
                result= "在库";
            } else if (this.status == 0) {
                result = "出库";
            }
        }
       return result;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
