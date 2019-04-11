package com.dragon.book.controller;

import com.dragon.book.model.TComment;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.service.BookManagerService;
import com.dragon.book.service.UserBorrowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 查找书评
 */
@Controller
public class CommentController {

    @Autowired
    private BookManagerService bookService ;
    @Autowired
    private BookManagerService bookServices ;
    @Autowired
    private UserBorrowService userBorrowService;
    @RequestMapping("/getBookComment")
    public String getBookComment(String id,Model model){
        BookInfo bookInfo = bookServices.selectBookInfoById(id);
        String isbn = bookInfo.getIsbn();
        List<CommentInfo> commentInfos =userBorrowService.selBookComment(isbn);

        model.addAttribute("commentInfos",commentInfos);
        model.addAttribute("bookInfo",bookInfo);
        return "/comment/bookComment";
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
    @RequestMapping("/delAllCommentByid")
    @ResponseBody
    public String delAllComment(@Param("check") String[] check){
        String status="0";
        for(int i=0;i<check.length ;i++){
            boolean is =bookService.delComment(check[i]);
            if (is==true){
                status="1";
            }else{
                status="0";
                return status;
            }
        }
        return status;
    }
    //评价对话框
    @RequestMapping("/commentInfo")
    public String EvaluationInfo(Integer id,Model model){
        List<CommentInfo> commentInfos = userBorrowService.selUserComment(id);
        HistoryInfo historyInfo = bookService.selectHistoryById(id);
        model.addAttribute("commentInfos",commentInfos);
        model.addAttribute("historyInfo",historyInfo);
        return "comment/commentInfo";
    }
    @RequestMapping("/getBookCommentByBookId")
    public String getBookCommentByBookId(String id,Model model){
        List<CommentInfo> commentInfos =userBorrowService.selBookComment(id);
        //HistoryInfo historyInfo = bookService.selectHistoryById(id);
        model.addAttribute("commentInfos",commentInfos);
        model.addAttribute("bookIsbn",id);
        //model.addAttribute("historyInfo",historyInfo);
        return "book/book_info";
    }
}
