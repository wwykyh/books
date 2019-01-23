package com.dragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BookManagerController
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/23
 */
@Controller
public class BookManagerController {
    @RequestMapping("/book_manager")
    public String showBookManagerPage(){
        return "manager/book_manager";
    }
    @RequestMapping("/book_add")
    public  String showBookAddPage(){
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

}
