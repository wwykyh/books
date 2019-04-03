package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TComment;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.service.BookManagerService;
import com.dragon.book.service.UserBorrowService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName UserBorrowController
 * @Author baijinxing
 * @Date 2019/3/15
 */



@Controller
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
    public String EvaluationInfo(Integer id,Model model){
        List<CommentInfo> tCommentInfo = userBorrowService.selUserComment(id);
        HistoryInfo history = bookService.selectHistoryById(id);
        model.addAttribute("tCommentInfo",tCommentInfo);
        model.addAttribute("history",history);
        return "manager/commentInfo";
    }

    //用户评论处理
    @RequestMapping("/commentService")
    @ResponseBody
    public String userEvaluation(HttpServletRequest request){
        TComment tComment = new TComment();
        //获取评论的各项数据
        String userId= request.getParameter("userId");
        Integer userid = Integer.valueOf(userId);
        String userComment= request.getParameter("userComment");
        String bookId = request.getParameter("bookId");
        //把数据塞进去
        tComment.setIsbn(bookId);
        tComment.setNr(userComment);
        tComment.setUserId(userid);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss  ");
        tComment.setPjrq(dateFormat.format(new Date()));
        String status="0";
        int i =userBorrowService.addUserComment(tComment);
        if(i>=0){
            status = "1";
        }
        return status  ;
    }

    @RequestMapping("/delCommentByid")
    @ResponseBody
    public  String delComment(String id){

        String status="0";
        boolean i =bookService.delComment(id);
        if (i==true){
            status="1";
        }
        return status  ;
    }
}