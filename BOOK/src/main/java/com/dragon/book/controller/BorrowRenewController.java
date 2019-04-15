package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TBorrow;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.TBorrowInfo;
import com.dragon.book.service.ebookService.EBookFileService;
import com.dragon.book.service.ebookService.IRenewCheckService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 续借审核
 * zzm
 */

@Controller
@RequestMapping("/renewCheck")
public class BorrowRenewController {

    @Autowired
    private EBookFileService ebookFileService;

    @Autowired
    private IRenewCheckService renewCheckService;

    /**
     * 跳转到续借界面，并加载图书类型
     *
     * @param request
     * @return
     */
    @RequestMapping("/renew")
    public String showBookRenew(HttpServletRequest request) {
        List<TType> types = ebookFileService.getTypeList();
        request.setAttribute("types", types);
        return "book/bookRenew";
    }

    /**
     * 查找借书信息并分页
     *
     * @param pageBean
     * @param request
     * @return
     */
    @GetMapping("/")
    @ResponseBody
    public String showBookBorrowPage(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer total = renewCheckService.getRenewCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TBorrowInfo> tBorrows = renewCheckService.getTBorrowRenewList(searchParams);

        pageBean.setRows(tBorrows);
        pageBean.setTotal(total);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }

    /**
     * 获得解约详细信息，并跳转到审核弹窗
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String toBorrowCheck(Model model, @PathVariable String id) {
        if (!StringUtils.isEmpty(id)) {
            model.addAttribute("singleTBorrow", renewCheckService.getSingleRenewTBorrow(Integer.parseInt(id)));
        }
        return "book/bookRenewCheck";
    }

    /**
     * 更新审核信息
     *
     * @param id
     * @param status
     * @param bz
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public Object borrowCheck(String id, String status, String bz) {
        TBorrow tBorrow;
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(status)) {
            tBorrow = renewCheckService.getSingleRenewTBorrow(Integer.parseInt(id));
            if ("1".equals(status)) {  // 审核通过
                renewCheckService.updateTBorrow(tBorrow, id, status, bz);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

}
