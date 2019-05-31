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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.book.model.*;
import com.dragon.book.pojo.Book;

import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.service.*;
import com.dragon.book.util.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
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

    @Autowired
    private BookManagerService bookServices;

    @Autowired
    private UserBorrowService userBorrowService;
    @Autowired
    private HomeService homeService;

    /**
     * 用户主页
     *
     * @return
     */
    @GetMapping("/index")
    public String bookIndex() {


        return "index";
    }

    @GetMapping("/book_info")
    public String booksIndex(@RequestParam String id, Model model) {
      //  System.out.println(id);
        String isbn = id;
        //  System.out.println(id);
        id = id.trim();
        //  System.out.println(id);

        BookInfo bookInfo = bookServices.selectBookInfoById(id);
//        System.out.println(bookInfo.toString() + "================");
        model.addAttribute("bookInfo", bookInfo);

        if (isbn.substring(0, 1).equals("a")) {
            String Isbn = isbn.substring(1);
            bookInfo = bookServices.selectBookByIsbn(Isbn);
        }
        model.addAttribute("bookInfo", bookInfo);
        String myIsbn = bookInfo.getIsbn();
        List<CommentInfo> commentInfos = userBorrowService.selBookComment(myIsbn);
        model.addAttribute("commentInfos", commentInfos);
        return "book/book_info";
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

    @GetMapping("/search")
    public String search(Model model) {
        List<Borrow> hotBooks = homeService.getHotBooks();
        List<Borrow> borrows = hotBooks.subList(0, 4);
        System.out.println(borrows.toString());
        model.addAttribute("borrow", borrows);
        return "book/searchList";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/Home1")
    public String home(Model model, HttpServletRequest request) {
        List<TBorrow> bookList = bookService.getBookTop();
        List<TBorrow> userList = bookService.getUserTop();
        model.addAttribute("bookList", bookList);
        model.addAttribute("userList", userList);
		
		/*String path = request.getServletContext().getRealPath("/js");
		System.out.println(path+"-------------------------------");*/

        return "home";
    }

    /* */

    /**
     * 第一次进入检索界面
     *
     * @param model
     * @return
     *//*
    @GetMapping("/sea")
    public String Sea(Model model) {
        List<TType> typeList = typeService.getAllTypes();
        model.addAttribute("typeList", typeList);


        int pageNo = 1;      //设置默认页码，当pageNumber类型转换出错时，会起作用，否则值被覆盖

        Page<Book> pagebean = new Page<Book>();

       *//* String dim = "多线程";
        String s_type = "shuyy";*//*
        PageBean page = new PageBean();
        try {
            //servlet层获取的参数类型为string，需要转换为整型
            pageNo = Integer.parseInt("1");
        } catch (Exception e) {
            System.out.println("字符串转换出错");
        }
        int total = bookService.getTotal(page);// 计算总数
        //  System.out.println("-=-=-=-="+total);

        // pagebean = bookService.getPage(pageNo, 10, dim, s_type, total);  //获取页面信息

        System.out.println("12121212-----" + pagebean.getPageSize() + "  /n totl:" + pagebean.getTotalPage());

        model.addAttribute("page", pagebean);
        // return "0";
        // return "book/books";

        return "book/book";
    }*/
    @GetMapping("/page")
    public String search(@RequestParam(value = "pageNumber", defaultValue = "1") String pageNumber, @RequestParam(value = "dim", defaultValue = "") String dim, @RequestParam(value = "s_type", defaultValue = "") String s_type,
                        @RequestParam(value = "status",defaultValue = "") String status, Model model, HttpServletRequest request, HttpServletResponse resopnse
    ) {


       // System.out.println("number:" + pageNumber + "dim:" + dim + "type:" + s_type);


        List<TType> typeList = typeService.getAllTypes();
        model.addAttribute("typeList", typeList);

        int pageNo = 1;      //设置默认页码，当pageNumber类型转换出错时，会起作用，否则值被覆盖

        Page<Book> pagebean = new Page<Book>();


        PageBean page = new PageBean();
        try {
            //servlet层获取的参数类型为string，需要转换为整型
            pageNo = Integer.parseInt(pageNumber);
        } catch (Exception e) {
            System.out.println("字符串转换出错");
        }
        int total = bookService.getTotal(page);// 计算总数
        // System.out.println("-=-=-=-=" + total);

        pagebean = bookService.getPage(pageNo, 10, dim, s_type, total,status);  //获取页面信息

        System.out.println("12121212-----" + pagebean.getPageSize() + "  /n totl:" + pagebean.getTotalPage() + pagebean.toString());

        model.addAttribute("page", pagebean);
        return "book/book";

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
                (TSysUser) session.getAttribute("user"));
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
       /* if (0 == book.gettStore().getStatus()) {
            model.addAttribute("status", "出库");
        } else {
            model.addAttribute("status", "在库");
        }*/
        return "book/borrow";
    }

    /**
     * 借阅申请处理
     *
     * @param id 图书id
     * @return
     */
    @PostMapping("/doBorrow")
    @ResponseBody
    public String doBorrow(
            @RequestParam String id, HttpSession session) {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(d);
        Book book = bookService.getBook(id);
        TSysUser user = (TSysUser) session.getAttribute("user");
        int borrow1 = bookService.getBorrow(user.getUserId());
        if (borrow1 < user.getKjtscs()) {
            TBorrow borrow = bookService.setBorrow(id, user.getUserId(), book.gettBook().getSm(), user.getLxfs(), now, bookService.getTime(now, Integer.toString(user.getKjsc())), 2, 0);
            int i = bookService.insertBorrow(borrow);
            if (i > 0) {
                int i1 = bookService.updateByKey(id, 0);
                if (i1 > 0)
                    return "0";
            }
        }
        return "1";
    }


    @GetMapping("like")
    @ResponseBody
    public String like(String bookId, HttpSession session) {

        TSysUser user = (TSysUser) session.getAttribute("user");
        String id = user.getBookId();
        System.out.println(id);
        if (id.contains(bookId)) {
            return "0";
        }
        if ("".equals(id) || id == null) {
            id = bookId;
        } else {
            id = id + "," + bookId;
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


          /*  WordToPDF wordToPDF = new WordToPDF();
            try {
                wordToPDF.WordToPDF("e://简历.doc","e://成了.pdf");
            } catch (IOException e) {
                e.printStackTrace();


        }*/
        return "PDF";
    }


    @GetMapping("pageinfo")
    public String pageInfo(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage, Model model) {
        List<Book> list = bookService.getPageInfo("1", "2", currentPage);
        System.out.println(currentPage);
        PageInfo<Book> page = new PageInfo<>(list);
        model.addAttribute("pageInfo", page);
        System.out.println("===============");
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return "/book/pageInfo";
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
