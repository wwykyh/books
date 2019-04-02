package com.dragon.book.controller;
import com.dragon.book.model.TBorrow;
import com.dragon.book.pojo.Book;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.pojo.UserBorrow;
import com.dragon.book.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

   //还书
    @RequestMapping("/return_book")
    public String returnBook( @RequestParam("userid")int userid, Model model) {
        List<TBorrow> books = homeService.getUserBorrow(userid);
        model.addAttribute("books",books);
        return "returnBook" ;
    }

    //主页
    @GetMapping("/Home")
    public String getRankList(Model model) {
        List<Borrow> rankingListUsers = homeService.getRankingList();
        List<Borrow> hotBooks = homeService.getHotBooks();
        List<Borrow> newBooks = homeService.getNewBooks();
        String overTimeUsers = homeService.getOverTimeUser();
        model.addAttribute("rankingListUsers",rankingListUsers);
        model.addAttribute("hotBooks",hotBooks);
        model.addAttribute("newBooks",newBooks);
        model.addAttribute("overTimeUsers",overTimeUsers);
        return "home" ;
    }
}
