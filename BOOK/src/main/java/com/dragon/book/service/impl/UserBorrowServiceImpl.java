package com.dragon.book.service.impl;

import com.dragon.book.mapper.TCommentMapper;
import com.dragon.book.model.TComment;
import com.dragon.book.service.UserBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBorrowServiceImpl implements UserBorrowService {

    @Autowired
    private TCommentMapper tCommentMapper;
    //添加评论
    @Override
    public int addUserComment(TComment tComment) {
        return tCommentMapper.insert(tComment);
    }

    //查找用户的评论
    public List<TComment> selUserComment(String user_id){

       return  tCommentMapper.selectByUserId(user_id);
    }
}
