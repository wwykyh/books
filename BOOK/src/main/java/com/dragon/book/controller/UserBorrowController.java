package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TComment;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.service.BookManagerService;
import com.dragon.book.service.UserBorrowService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName UserBorrowController
 * @Author baijinxing
 * @Date 2019/3/15
 */



@Controller
@RequestMapping("/userBorrow")
public class UserBorrowController {
    @Autowired
    private BookManagerService bookService ;

    @Autowired
    private UserBorrowService userBorrowService;
    /**
     * 借阅历史
     * */
    @RequestMapping("/user_historyPage")
    @ResponseBody
    public String HistoryPageManager(PageBean pageBean){
        PageBean commentInfo = bookService.selectHistoryInfo(pageBean);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    //返回借阅界面
    @RequestMapping("/userBorrowHistory")
    public String showBorrowHistoryPage(){
        return "manager/userBorrowHistory";
    }

    //评价对话框
    @RequestMapping("/commentInfo")
    public String EvaluationInfo(String id,Model model){
        List<TComment> tComment = userBorrowService.selUserComment(id);
        model.addAttribute("tComment",tComment) ;
        return "manager/commentInfo";
    }

    //用户评论处理s
    @RequestMapping("/commentService")
    public String userEvaluation(TComment tComment){
        System.out.println(tComment);
        String status="0";
        int i =userBorrowService.addUserComment(tComment);
        if(i>=0){
            status = "1";
        }
        return status  ;
    }
}