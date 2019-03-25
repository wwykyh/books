package com.dragon.book.controller;


import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;
import com.dragon.book.service.my.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 我的模块：个人信息
 * zzm
 */

@Controller
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalServiceImpl personalServiceImpl;

    /**
     * 转到personal界面，并加载数据
     * @param uid
     * @param map
     * @return
     */
    @RequestMapping("/topersonalindex")
    public String toPersonalIndex(@RequestParam("userid")String uid,Map map){
//        System.out.print(uid);
          int userid = Integer.parseInt(uid);
        if(0!=userid){
            TSysUser userInformation = personalServiceImpl.selectUserIndormation(userid);
            List<BookBorrow> bookBorrow = personalServiceImpl.selectBookInformation(userid);
            map.put("userInformation",userInformation);
            map.put("bookBorrow",bookBorrow);
        }
        return "/my/personal";
    }

    /**
     * 归还图书
     * @param
     * @return
     */
    @RequestMapping("/returnbook")
    @ResponseBody
    public String returnBook(@RequestParam("isbn")String isbn,@RequestParam("userid")String uid){
        if(isbn.equals("")||isbn.equals(null)) {
            return "error";
        }
        int userid = Integer.parseInt(uid);
        personalServiceImpl.returnBook(isbn,userid);
        return "success";
    }

    /**
     * 续借
     * @param isbn
     * @param uid
     * @return
     */
    @RequestMapping("/renew")
    @ResponseBody
    public String renew(@RequestParam("isbn")String isbn,@RequestParam("userid")String uid){
        if(isbn.equals("")||isbn.equals(null)) {
            return "error";
        }
        int userid = Integer.parseInt(uid);
        personalServiceImpl.renew(isbn,userid);
        return "success";
    }

    /**
     * 借阅详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/borrowinfo")
    public String borrowInfo(@RequestParam("id")String id,Map map){
        int tborrowrid = Integer.parseInt(id);
        TBorrow tborrow = personalServiceImpl.borrowInfo(tborrowrid);
        map.put("tborrow",tborrow);
        return "/my/bookBorrowInfo";
    }
}
