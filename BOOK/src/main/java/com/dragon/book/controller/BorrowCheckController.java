package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.BorrowCheckService;
import com.dragon.book.service.ebookService.EBookFileService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/borrowCheck")
public class BorrowCheckController {

    @Autowired
    private EBookFileService ebookFileService;

    @Autowired
    private BorrowCheckService borrowCheckService;

    @GetMapping("/borrow")
    public String showBookBorrow(HttpServletRequest request) {
        List<TType> types = ebookFileService.getTypeList();
        request.setAttribute("types", types);
        return "book/bookBorrow";
    }

    @GetMapping("/")
    @ResponseBody
    public String showBookBorrowPage(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer total = borrowCheckService.getCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TBorrowInfo> tBorrows = borrowCheckService.getTBorrowCheckList(searchParams);

        pageBean.setRows(tBorrows);
        pageBean.setTotal(total);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }

    @GetMapping("/{id}")
    public String toBorrowCheck(Model model, @PathVariable String id) {
        if (!StringUtils.isEmpty(id)) {
            model.addAttribute("singleTBorrow", borrowCheckService.getSingleTBorrow(Integer.parseInt(id)));
        }
        return "book/bookBorrowCheck";
    }

    @PutMapping("/")
    @ResponseBody
    public Object borrowCheck(String id, String status, String bz) {
        TBorrow tBorrow;
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(status)) {
            tBorrow = borrowCheckService.getSingleTBorrow(Integer.parseInt(id));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if ("1".equals(status)) {  // 确认通过
                tBorrow.setStatus(1);
                String jyri = format.format(new Date());
                tBorrow.setJyrq(jyri);
            }
            return borrowCheckService.updateTBorrow(tBorrow);
        } else {
            return false;
        }
    }
}
