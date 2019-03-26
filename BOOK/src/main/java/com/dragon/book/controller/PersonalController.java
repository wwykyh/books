package com.dragon.book.controller;


import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.BookBorrow;
import com.dragon.book.service.my.IPersonalService;
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
    private IPersonalService personalService;

    /**
     * 转到personal界面，并加载数据
     * @param uid 用户id
     * @param map
     * @return
     */
    @RequestMapping("/topersonalindex")
    public String toPersonalIndex(@RequestParam("userid")int uid,Map map){
//        System.out.print(uid);
        if(0!=uid){
            TSysUser userInformation = personalService.selectUserIndormation(uid);
            List<BookBorrow> bookBorrow = personalService.selectBookInformation(uid);
            map.put("userInformation",userInformation);
            map.put("bookBorrow",bookBorrow);
        }
        return "/my/personal";
    }

    /**
     * 归还图书
     *@param isbn 图书编号
     *@param uid 用户id
     * @return
     */
    @RequestMapping("/returnbook")
    @ResponseBody
    public String returnBook(@RequestParam("isbn")String isbn,@RequestParam("userid")int uid){
        if(""==isbn&&null==isbn&&0==uid) {
            String data = "图书编号或用户ID不能为空！";
            return data;
        }
        personalService.returnBook(isbn,uid);
        return "success";
    }

    /**
     * 续借
     * @param isbn 图书编号
     * @param uid 用户id
     * @return
     */
    @RequestMapping("/renew")
    @ResponseBody
    public String renew(@RequestParam("isbn")String isbn,@RequestParam("userid")int uid){
        if(""==isbn&&null==isbn&&0==uid) {
            String data = "图书编号或用户ID不能为空！";
            return data;
        }
        personalService.renew(isbn,uid);
        return "success";
    }

    /**
     * 借阅详情
     * @param id 借书消息id
     * @param map
     * @return
     */
    @RequestMapping("/borrowinfo")
    public String borrowInfo(@RequestParam("id")int id,Map map){
        if(0!=id){
        TBorrow tborrow = personalService.borrowInfo(id);
        map.put("tborrow",tborrow);
        return "/my/bookBorrowInfo";
        }else {
            return "/my/error";
        }
    }
}
