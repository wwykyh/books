package com.dragon.book.pojo;

import com.dragon.book.model.TComment;

/**
 * @ClassNameCommentInfo
 * @Description TODO
 * @Author liulei
 * @Date 2019/2/12
 */
public class CommentInfo extends TComment {
    private String xm ;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "xm='" + xm + '\'' +
                '}';
    }
}
