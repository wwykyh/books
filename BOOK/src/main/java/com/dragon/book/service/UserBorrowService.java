package com.dragon.book.service;

import com.dragon.book.model.TComment;

import java.util.List;

public interface UserBorrowService {

    public int addUserComment(TComment tComment);

    List<TComment> selUserComment(String user_id);

}
