package com.dragon.book.pojo;
/**
 *借阅量自定义的实体类
 * baijinxing
 */

public class LineChart {
    private int num;
    private String jyrq;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getJyrq() {
        return jyrq;
    }

    public void setJyrq(String jyrq) {
        this.jyrq = jyrq;
    }

    @Override
    public String toString() {
        return "LineChart{" +
                "num=" + num +
                ", jyrq='" + jyrq + '\'' +
                '}';
    }
}
