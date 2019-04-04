package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import com.dragon.book.service.ebookService.EBookFileService;
import com.dragon.book.service.ebookService.EBookInfoService;
import com.dragon.book.util.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * author: lanwq
 * description:  电子书上传下载控制层
 */
@Controller
@RequestMapping(value = "/eBookFile")
public class EBookFileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private EBookFileService ebookFileService;

    @Autowired
    private EBookInfoService eBookInfoService;

    @GetMapping(value = "/page/upload")
    public String pageUpload() {
        return "ebook/fileUpload";
    }

    @GetMapping(value = "/page/manage")
    public String pageManage(HttpServletRequest request) {
        request.setAttribute("types", ebookFileService.getTypeList());
        return "ebook/eBookManager";
    }

    // TODO 跳转到电子书添加界面
    @GetMapping(value = "/page/uploadAdd")
    public String pageUploadAdd(HttpServletRequest request) {
        request.setAttribute("types", ebookFileService.getTypeList());
        return "ebook/fileUploadAdd";
    }

    @PostMapping(value = "/eBook")
    @ResponseBody
    public String eBookUpload(MultipartFile[] eBookFile, TEBook teBook) {
        boolean result = ebookFileService.eBookFileSingleOrMulUpload(eBookFile, teBook);
        if (result) return "success";
        return "error";
    }

    /**
     * 电子书下载
     *
     * @param eBookId 下载的电子书ID
     */
    @GetMapping(value = "/eBook/{eBookId}")
    public void eBookDownload(@PathVariable String eBookId, HttpServletResponse response) {
        try {
            TEBook teBook = eBookInfoService.getSingleEBook(eBookId);
            String fileName = teBook.geteBookXm();
            // TODO  设置让浏览器弹出下载对话框的Header
            Servlets.setFileDownloadHeader(response, fileName);
            boolean result = ebookFileService.finalDownload(teBook);

            if (result) ebookFileService.updateDownloadDate(teBook);
            logger.info("文件下载状态： " + result);
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 查询所有的电子书列表
     *
     * @param pageBean 查询参数封装类
     * @param request  http请求
     * @return 电子书json串
     */
    @GetMapping(value = "/eBook")
    @ResponseBody
    public String eBookSearch(PageBean pageBean, HttpServletRequest request) {
        // TODO 查询参数封装到map中
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer count = eBookInfoService.getCount(searchParams);
        searchParams.put("firstRow", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TEBookVo> TEBookVoList = eBookInfoService.getTEBookVoList(searchParams);
        pageBean.setRows(TEBookVoList);
        pageBean.setTotal(count);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }
}
