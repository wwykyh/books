package com.dragon.book.service;

import com.dragon.book.model.TComment;
import com.dragon.book.pojo.CommentInfo;

import java.util.List;

public interface UserBorrowService {

    public int addUserComment(TComment tComment);

    List<CommentInfo> selUserComment(Integer borrowId);
    List<CommentInfo> selBookComment(String id);

}
