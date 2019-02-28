package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TType;
import com.dragon.book.service.ebookService.BorrowCheckService;
import com.dragon.book.service.ebookService.EbookFileService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/borrowCheck")
public class BorrowCheckController {

    @Autowired
    private EbookFileService ebookFileService;

    @Autowired
    private BorrowCheckService borrowCheckService;

    @RequestMapping("/borrow")
    public String showBookBorrow(HttpServletRequest request) {
        List<TType> types = ebookFileService.getTypeList();
        request.setAttribute("types", types);
        return "book/bookBorrow";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String showBookBorrowPage(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer total = borrowCheckService.getCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TBorrow> tBorrows = borrowCheckService.getTBorrowCheckList(searchParams);

        pageBean.setRows(tBorrows);
        pageBean.setTotal(total);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }

    @RequestMapping("/toCheck")
    public String toBorrowCheck(Model model, String id) {
        if (!StringUtils.isEmpty(id)) {
            model.addAttribute("singleTBorrow", borrowCheckService.getSingleTBorrow(Integer.parseInt(id)));
        }
        return "book/bookBorrowCheck";
    }

    @RequestMapping("/check")
    @ResponseBody
    public Object borrowCheck(String id, String status, String bz) {
        TBorrow tBorrow;
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(status)) {
            tBorrow = borrowCheckService.getSingleTBorrow(Integer.parseInt(id));
            if ("1".equals(status)) {  // 审核通过
                tBorrow.setStatus(1);
                tBorrow.setJyzt(0);
            }
            if ("0".equals(status)) {  // 审核不通过，并设置不通过理由 bz：备注
                tBorrow.setBz(bz);
                tBorrow.setStatus(0);
            }
            return borrowCheckService.updateTBorrow(tBorrow);
        } else {
            return false;
        }
    }
}
