package com.dragon.book.controller;


import com.dragon.book.model.TBorrow;
import com.dragon.book.service.my.IOvertimePaymentService;
import com.dragon.book.service.my.IPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 我的模块：超时归还Controller层
 */
@Controller
@RequestMapping("/overtimePayment")
public class OvertimePaymentController {

    @Autowired
    private IOvertimePaymentService overtimePaymentService;
    @Autowired
    private IPersonalService personalService;

    /**
     * 跳转到超时归还界面
     *
     * @return
     */
    @RequestMapping("/toOvertimePayment")
    public String overtimePaymentIndex(@RequestParam("userId") int uid, Map map) {
        if (0 != uid) {
            List<TBorrow> tBorrows = overtimePaymentService.findOvertimeBorrow(uid);
            map.put("tBorrows", tBorrows);
            return "/my/overtimePayment";
        } else {
            return "/my/error";
        }
    }

    /**
     * 归还图书
     *
     * @param isbn 图书编号
     * @param uId  用户id
     * @return
     */
    @RequestMapping("/returnbook")
    @ResponseBody
    public String returnBook(@RequestParam("isbn") String isbn, @RequestParam("userId") int uId) {
        if ("" == isbn && null == isbn && 0 == uId) {
            String data = "图书编号或用户ID不能为空！";
            return data;
        }
        personalService.returnBook(isbn, uId);
        return "success";
    }

    /**
     * 续借
     *
     * @param isbn 图书编号
     * @param uid  用户id
     * @return
     */
    @RequestMapping("/renew")
    @ResponseBody
    public String renew(@RequestParam("isbn") String isbn, @RequestParam("userId") int uid) {
        if ("" == isbn && null == isbn && 0 == uid) {
            String data = "图书编号或用户ID不能为空！";
            return data;
        }
        personalService.renew(isbn, uid);
        return "success";
    }

}
