package com.dragon.book.service.impl;

import com.dragon.book.mapper.CommentMapper;
import com.dragon.book.mapper.TCommentMapper;
import com.dragon.book.model.TComment;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.service.UserBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBorrowServiceImpl implements UserBorrowService {

    @Autowired
    private CommentMapper commentMapper;

    //添加评论
    @Override
    public int addUserComment(TComment tComment) {
        return commentMapper.insert(tComment);
    }

    //查找用户的评论
    public List<CommentInfo> selUserComment(Integer bookId){
        List<CommentInfo> commentInfos = commentMapper.selectByUserId(bookId);
        return commentInfos ;
    }
}
