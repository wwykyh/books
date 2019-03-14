package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.EbookFileService;
import com.dragon.book.service.ebookService.RevertCheckService;
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

/**
 * 借阅归还审核控制层
 */
@Controller
@RequestMapping("/revertCheck")
public class BorrowRevertController {

    @Autowired
    private EbookFileService ebookFileService;

    @Autowired
    private RevertCheckService revertCheckService;

    @RequestMapping("/revert")
    public String showBookBorrow(HttpServletRequest request) {
        List<TType> types = ebookFileService.getPageTypeList();
        request.setAttribute("types", types);
        return "book/bookRevert";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String showBookBorrowPage(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer total = revertCheckService.getTBorrowRevertListCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TBorrowInfo> tBorrows = revertCheckService.getTBorrowRevertList(searchParams);

        pageBean.setRows(tBorrows);
        pageBean.setTotal(total);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }

    @RequestMapping("/toRevertCheck")
    public String toBorrowCheck(Model model, String id) {
        if (!StringUtils.isEmpty(id)) {
            model.addAttribute("singleTBorrow", revertCheckService.getSingleRevertTBorrow(Integer.parseInt(id)));
        }
        return "book/bookRevertCheck";
    }

    @RequestMapping("/check")
    @ResponseBody
    public Object borrowCheck(String id, String sh, String status) {
        return revertCheckService.updateRevertTBorrowSh(Integer.parseInt(id),Integer.parseInt(sh), status);
    }
}
