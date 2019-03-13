/**
 * 版权所有：厦门市巨龙软件工程有限公司
 * Copyright 2009 Xiamen Dragon Software Eng. Co. Ltd.
 * All right reserved.
 * ====================================================
 * 文件名称: BookController.java
 * 修订记录：
 * No    日期				    作者(操作:具体内容)
 * 1.    1 22, 2019			王炜焱(创建:创建文件)
 * ====================================================
 * 类描述：图书控制器
 */
package com.dragon.book.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.dragon.book.model.*;
import com.dragon.book.pojo.Book;
import com.dragon.book.util.WordToPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dragon.book.service.BookService;
import com.dragon.book.service.TypeService;
import com.dragon.book.service.UserService;
import com.dragon.book.util.PageBean;

@Controller
public class BookController {

    final int Max = 100;

    @Autowired
    private BookService bookService;

    @Autowired
    private TypeService typeService;


    @Autowired
    private UserService userService;

    /**
     * 用户主页
     *
     * @return
     */
    @GetMapping("/index")
    public String bookIndex() {


        return "index";
    }

    /**
     * 管理员主页 admin
     *
     * @return
     */
    @GetMapping("/adminindex")
    public String adminBookIndex() {
        return "adminIndex";
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/Home")
    public String home(Model model, HttpServletRequest request) {
        List<TBorrow> bookList = bookService.getBookTop();
        List<TBorrow> userList = bookService.getUserTop();
        model.addAttribute("bookList", bookList);
        model.addAttribute("userList", userList);
		
		/*String path = request.getServletContext().getRealPath("/js");
		System.out.println(path+"-------------------------------");*/

        return "home";
    }

    /**
     * 第一次进入检索界面
     *
     * @param model
     * @return
     */
    @GetMapping("/sea")
    public String Sea(Model model) {
        List<TType> typeList = typeService.getAllTypes();
        model.addAttribute("typeList", typeList);
        return "book/search";
    }

    /**
     * @param dim      书名、作者
     * @param s_tsdl   图书大类
     * @param s_type   图书类型
     * @param page     当前页
     * @param pagesize 页面大小
     * @param model    返回数据
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam String dim, @RequestParam String s_tsdl,
                         @RequestParam String s_type, @RequestParam int page,
                         @RequestParam int pagesize, Model model) {
        PageBean pagebean = new PageBean();
        pagebean.setDim(dim);
        pagebean.setPage(page - 1);// 当前页要-1
        pagebean.setPagesize(pagesize);
        pagebean.setS_tsdl(s_tsdl);
        pagebean.setS_type(s_type);
        List<BookAndEBook> allBookList = bookService
                .joinBook(bookService.getBooks(pagebean),
                        bookService.getEBooks(pagebean));

        int total = bookService.getTotal(pagebean);// 计算总数
        pagebean.setRows(allBookList);
        pagebean.setTotal(total);
        return JSON.toJSONString(pagebean).replaceAll("rows", "Rows")
                .replaceAll("total", "Total");
    }

    /**
     * 借阅
     *
     * @param id      图书id
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/borrow")
    public String borrow(@RequestParam("id") String id, Model model,
                         HttpSession session) {

        model.addAttribute("user",
                userService.getUserInfo((int) session.getAttribute("userId")));
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        if (0 == book.gettStore().getStatus()) {
            model.addAttribute("status", "出库");
        } else {
            model.addAttribute("status", "在库");
        }
        return "book/borrow";
    }

    /**
     * 借阅申请处理
     *
     * @param id     图书id
     * @param sm     图书书名
     * @param lxfs   用户联系方式
     * @param jyrq   借阅日期
     * @param jhghrq 借阅周期
     * @param userId 用户id
     * @param model
     * @return
     */
    @GetMapping("/doBorrow")
    public String doBorrow(
            @RequestParam String id, @RequestParam String sm,
            @RequestParam String lxfs, @RequestParam String jyrq,
            @RequestParam String jhghrq, @RequestParam String userId,
            Model model) {

        TBorrow borrow = bookService.setBorrow(id, Integer.parseInt(userId), sm, lxfs, jyrq, bookService.getTime(jyrq, jhghrq), 2);
        bookService.insertBorrow(borrow);
        bookService.updateByKey(id, 0);

        return "redirect:/sea";
    }


    @GetMapping("like")
    @ResponseBody
    public String like(String bookId, HttpSession session) {

        TSysUser user = (TSysUser) session.getAttribute("user");
        String id = user.getBookId();
        System.out.println(id);
        if (id.contains(bookId)){
            return "0";
        }
        if ("".equals(id)||id==null){
            id=bookId;
        }
        else{
            id = id + ","+bookId;
        }

        user.setBookId(id);

        boolean b = userService.updataByUser(user);
        if (b) {
            session.setAttribute("user", user);
            return "1";
        } else
            return "0";
    }

    @GetMapping("pdf")
    public String pdf(Model model) {

        // model.addAttribute("url",
        // "https://view.officeapps.live.com/op/view.aspx?src=http://storage.xuetangx.com/public_assets/xuetangx/PDF/1.xls");
       // model.addAttribute("url", "images/1.doc");


            WordToPDF wordToPDF = new WordToPDF();
            try {
                wordToPDF.WordToPDF("e://简历.doc","e://成了.pdf");
            } catch (IOException e) {
                e.printStackTrace();


        }
        return "PDF";
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getMax() {
        return Max;
    }
}
