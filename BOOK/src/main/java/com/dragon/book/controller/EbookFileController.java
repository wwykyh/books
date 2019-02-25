package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import com.dragon.book.model.TType;
import com.dragon.book.service.ebookService.EbookFileService;
import com.dragon.book.utils.PageBean;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/eBookFile")
public class EbookFileController {

    private FTPClient ftp = null;

    @Autowired
    private EbookFileService ebookFileService;

    @RequestMapping(value = "/toUpload")
    public String toUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
            ftp = ebookFileService.getFtpConfig();
            List<String> dirList = ebookFileService.getDirectories(ftp);
            String ebookId = UUID.randomUUID().toString().replace("-", "");
            List<TType> types = ebookFileService.getTypeList();
            request.setAttribute("dirList", dirList);
            request.setAttribute("ebookId", ebookId);
            request.setAttribute("types", types);
            response.setContentType("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ebook/fileUpload";
    }

    @RequestMapping(value = "/bookUpload" ,method = RequestMethod.POST)
    @ResponseBody
    public String uploadBook(@RequestParam("ebookFile") MultipartFile file, TEBook teBook,
                             HttpServletRequest request, HttpServletResponse response) {
        boolean result, flag = false;
        try {
            ftp = ebookFileService.getFtpConfig();
            TType tType = ebookFileService.getTTypeByPk(teBook.getTypeId());
            String typeName = tType.getLxmc();
            String fileName = request.getParameter("eBookXm");
            if (StringUtils.isEmpty(fileName)) {
                fileName = file.getOriginalFilename();
            }

            InputStream inputStream = file.getInputStream();
            result = ebookFileService.uploadEbookFile(ftp, inputStream, "/" + typeName + "/", fileName);
            flag = ebookFileService.saveEbookFile(teBook);
        } catch (Exception e) {
            result = false;
            System.out.println("上传失败");
            e.printStackTrace();
        }
        if (result && flag) {
            return "success";
        } else {
            return "error";
        }

    }

    /**
     * @return 跳转到电子书管理界面，可以下载电子书
     */
    @RequestMapping(value = "/ebookManger")
    public String toEbookManager(HttpServletRequest request) {
        ftp = ebookFileService.getFtpConfig();
        List<String> dirList = ebookFileService.getDirectories(ftp);
        List<TType> types = ebookFileService.getTypeList();
        request.setAttribute("dirList", dirList);
        request.setAttribute("types", types);
        return "ebook/ebookManager";
    }


    @RequestMapping(value = "/bookDownload")
    public void downloadBook(String eBookId, HttpServletResponse response) {
        try {
            TEBook teBook = ebookFileService.getSingleEbook(eBookId);
            ftp = ebookFileService.getFtpConfig();
            String fileName = teBook.geteBookXm();
            String ftpPath = teBook.getWjdz();

            // 指定文件的保存类型
            response.reset();
            response.setContentType("application;charset=UTF-8");
            String tempName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            System.out.println("转换编码后的文件名:" + tempName);
            // 指定以附件的形式下载
            response.setHeader("Content-disposition", "attachment; filename=" + tempName);

            ServletOutputStream outputStream = response.getOutputStream();
            InputStream inputStream = ebookFileService.getFtpInputStream(ftp, fileName, "/" + ftpPath + "/");
            // 将输入流中的数据写入到输出流中
            byte[] buffer = new byte[inputStream.available()];
            int bytes_read;
            while ((bytes_read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytes_read);
            }
            System.out.println("文件下载完成");
            outputStream.close();
            inputStream.close();
            ftp.logout();
            ftp.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/ebookPage")
    @ResponseBody
    public String selectPage(PageBean pageBean, HttpServletRequest request) {
        ftp = ebookFileService.getFtpConfig();
        Map<String, Object> filter = new HashMap<>();
        String eBookXm = request.getParameter("eBookXm");
        String typeId = request.getParameter("typeId");
        if (!StringUtils.isEmpty(eBookXm)) {
            filter.put("eBookXm", eBookXm);
        }
        if (!StringUtils.isEmpty(typeId)) {
            filter.put("typeId", Integer.parseInt(typeId));
        }
        filter.put("firstRow",(pageBean.getPage()-1) * pageBean.getPagesize());
        filter.put("rowNum",pageBean.getPagesize());
        List<TEBookVo> TEBookVoList = ebookFileService.getTEBookVoList(filter);
        Integer count = ebookFileService.getCount(filter);
        pageBean.setRows(TEBookVoList);
        pageBean.setTotal(count);
        return JSON.toJSONString(pageBean).replaceAll("rows","Rows").replaceAll("total","Total");
    }
}
