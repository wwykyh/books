package com.dragon.book.controller;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

   //显示还书界面
    @RequestMapping("/return_book")
    public String returnBook( @RequestParam("userid")int userid, Model model) {
        List<TBorrow> books = homeService.getUserBorrow(userid);
        model.addAttribute("books",books);
        return "returnBook" ;
    }

    //还书
    @RequestMapping("/return_book_request")
    @ResponseBody
    public boolean returnBookRequest(HttpServletRequest request) {
        String[] checkboxs;
        //用来获取多个复选按钮的值
        checkboxs = request.getParameterValues("category");
        boolean status = homeService.returnBookRequest(checkboxs);
        return status ;
    }


    //主页
    @RequestMapping("/Home")
    public String getRankList(@RequestParam(value = "isadmin" ,required = false)int isadmin, Model model) {
        String home ="home";
        if (isadmin==1)
            home="homeAdmin";
        int userTotal = homeService.userTotal();
        int bookTotal = homeService.bookTotal();
        List<Borrow> rankingListUsers = homeService.getRankingList();
        List<Borrow> hotBooks = homeService.getHotBooks();
        List<Borrow> newBooks = homeService.getNewBooks();
        String overTimeUsers = homeService.getOverTimeUser();
        model.addAttribute("userTotal",userTotal);
        model.addAttribute("bookTotal",bookTotal);
        model.addAttribute("rankingListUsers",rankingListUsers);
        model.addAttribute("hotBooks",hotBooks);
        model.addAttribute("newBooks",newBooks);
        model.addAttribute("overTimeUsers",overTimeUsers);
        return home ;
    }
}
