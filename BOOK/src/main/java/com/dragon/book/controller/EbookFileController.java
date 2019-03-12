package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import com.dragon.book.model.TType;
import com.dragon.book.service.ebookService.EbookFileService;
import com.dragon.book.util.PageBean;
import com.dragon.book.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/** author: lanwq
 * description:  电子书上传下载控制层
 */
@Controller
@RequestMapping(value = "/eBookFile")
public class EbookFileController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EbookFileService ebookFileService;

    @RequestMapping(value = "/toUpload", method = RequestMethod.GET)
    public String toUpload(HttpServletRequest request) {
        try {
            List<TType> types = ebookFileService.getTypeList();
            request.setAttribute("types", types);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ebook/fileUpload";
    }

    @RequestMapping(value = "/bookUpload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadBook(MultipartFile[] ebookFile, TEBook teBook,
                             HttpServletRequest request, HttpServletResponse response) {
        boolean result = ebookFileService.ebookFileSingleOrMulUpload(ebookFile, teBook);
        if (result) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * @return 跳转到电子书管理界面，可以下载电子书
     */
    @RequestMapping(value = "/ebookManger", method = RequestMethod.GET)
    public String toEbookManager(HttpServletRequest request) {
        List<TType> types = ebookFileService.getTypeList();
        request.setAttribute("types", types);
        return "ebook/ebookManager";
    }

    @RequestMapping(value = "/bookDownload", method = RequestMethod.GET)
    public void downloadBook(String eBookId, HttpServletResponse response, HttpServletRequest request) {
        FTPClient ftp = ebookFileService.getFtpConfig();
        try {
            TEBook teBook = ebookFileService.getSingleEbook(eBookId);
            String fileName = teBook.geteBookXm();
            String ftpPath = teBook.getWjdz();

            Servlets.setFileDownloadHeader(response, fileName);  // 设置让浏览器弹出下载对话框的Header

            ServletOutputStream outputStream = response.getOutputStream();
            InputStream inputStream = ebookFileService.getFtpInputStream(ftp, fileName, "/" + ftpPath + "/");
            boolean result = ebookFileService.serviceDownload(inputStream, outputStream);
            if(result){
                ebookFileService.updateDownloadDate(teBook);
            }
            logger.info("文件下载状态： "+result);
            // 界面跳转
            toEbookManager(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            FtpUtils.closeConnection(ftp);
        }
    }

    @RequestMapping(value = "/ebookPage")
    @ResponseBody
    public String selectPage(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

        Integer count = ebookFileService.getCount(searchParams);
        searchParams.put("firstRow", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TEBookVo> TEBookVoList = ebookFileService.getTEBookVoList(searchParams);
        pageBean.setRows(TEBookVoList);
        pageBean.setTotal(count);
        return JSON.toJSONString(pageBean).replaceAll("rows", "Rows").replaceAll("total", "Total");
    }
}
