package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.PageBean;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.BookService;
import com.dragon.book.service.PublishService;
import com.dragon.book.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName BookManagerController
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/23
 */
@Controller
public class BookManagerController {
    @Autowired
    private TypeService typeService ;
    @Autowired
    private BookService bookService ;
    @Autowired
    private PublishService publishService ;


    @RequestMapping("/book_manager")
    public String showBookManagerPage(){
        return "manager/book_manager";
    }
    @RequestMapping("/book_add")
    public  String showBookAddPage(Model model){
        List<TPublish> publishList = publishService.getPublishList();
        List<TType> typeList = typeService.getTypeList();
        model.addAttribute("publishList",publishList) ;
        model.addAttribute("typeList",typeList) ;
        return "manager/book_add";
    }
    @RequestMapping("/asset_analyze")
    public String showResourceAnalyzePage(){
        return "manager/asset_analyze";
    }
    @RequestMapping("/borrow_history")
    public String showBorrowHistoryPage(){
        return "manager/borrow_history";
    }
    @RequestMapping("/book_analyze")
    public String showBookAnalyzePage(){
        return "manager/book_analyze";
    }
    @RequestMapping("/type_manager")
    public String showTypeManagerPage(){
        return "manager/type_manager";
    }
    @RequestMapping("/publish_house_manager")
    public String showPublishHouseManagerPage(){
        return "manager/publish_house_manager";
    }
    @RequestMapping("/book_info")
    public String showBookInfoPage(String id,Model model){
        BookInfo bookInfo = bookService.selectBookInfoById(id);
        model.addAttribute("bookInfo",bookInfo) ;
        return "manager/book_info" ;
    }

    @RequestMapping("/getBookInfo")
    @ResponseBody
    public TBook getBookInfo(String isbn){
        return bookService.getBookById(isbn);
    }
    @RequestMapping("/bookAdd")
    @ResponseBody
    public String bookAdd(QueryVo vo){

        boolean result = true;
        try {
            result = bookService.insertBook(vo);
        } catch (Exception e) {
            e.printStackTrace();
            result = false ;
        }
        if (result == true)
            return "0" ;
        return "1" ;
    }

    @RequestMapping("/publish_manager")
    @ResponseBody
    public String getPublishList(PageBean pageBean){
        PageBean pageBean1=publishService.getPublishListByPageBean(pageBean);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }
    @RequestMapping("/bookPage_manager")
    @ResponseBody
    public String selectBookPage(PageBean pageBean,String dim){
        QueryVo vo = new QueryVo() ;
        vo.setDim(dim);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean pageBean1 = bookService.selectBookInfo(pageBean, vo);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_store")
    @ResponseBody
    public String delBook(String id){
        boolean status = bookService.delBook(id) ;
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/comment_manager")
    @ResponseBody
    public String selectCommentPage(PageBean pageBean,String isbn){
        QueryVo vo = new QueryVo() ;
        vo.setDim(isbn);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean commentInfo = bookService.selectCommentInfo(pageBean, vo);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_comment")
    @ResponseBody
    public String delComment(String commentId){
        boolean status = bookService.delComment(commentId) ;
        return status == true ? "0" : "1" ;
    }

    @RequestMapping("/comment_info")
    public String showCommentInfo(String commentId,String tsmc,Model model){
        CommentInfo commentInfo = bookService.selectCommentInfoById(commentId);
        model.addAttribute("tsmc",tsmc) ;
        model.addAttribute("commentInfo",commentInfo) ;
        return "manager/comment_info" ;
    }

    @RequestMapping("/publish_del")
    @ResponseBody
    public String publishDel(String pubId){
        boolean status = publishService.deletePublish(pubId);
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/publish_add")
    public String showPublishAdd(){
        return "manager/publish_add" ;
    }
    @RequestMapping("/publishAdd")
    @ResponseBody
    public String publishAdd(TPublish publish){
        boolean status = publishService.addPublish(publish) ;
        return status == true ? "0" : "1" ;
    }

    @RequestMapping("/publish_edit")
    public String showPublishEdit(String pubId,Model model){
        TPublish publish = publishService.getPublishById(pubId);
        model.addAttribute("publish",publish) ;
        return "manager/publish_edit" ;
    }

    @RequestMapping("/publishEdit")
    @ResponseBody
    public String publishUpdate(TPublish publish){
        boolean status = publishService.updatePublish(publish);
        return status == true ? "0" : "1" ;
    }

    /**
     * 图书类型管理部分
     * */
    @RequestMapping("/typeManager")
    @ResponseBody
    public String typeManager(PageBean pageBean){
        PageBean commentInfo = typeService.getTypePageByPageBean(pageBean);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }

}
