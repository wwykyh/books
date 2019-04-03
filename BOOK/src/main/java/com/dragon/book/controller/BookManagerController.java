package com.dragon.book.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TBookAnalyze;
import com.dragon.book.model.TPublish;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.BookInfo;
import com.dragon.book.pojo.CommentInfo;
import com.dragon.book.pojo.HistoryInfo;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.*;
import com.dragon.book.util.FileDownloadUtils;
import com.dragon.book.util.PageBean;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BookManagerController
 * @Description TODO
 * @Author liulei
 * @Date 2019/1/23
 */
@Controller
public class BookManagerController {

    @Autowired
    private TypeManagerService typeService ;
    @Autowired
    private BookManagerService bookService ;
    @Autowired
    private PublishManagerService publishManagerService;
    @Autowired
    private BookAnalyzeService bookAnalyzeService;

    @RequestMapping("/book_manager")
    public String showBookManagerPage(){
        return "manager/book_manager";
    }
    @RequestMapping("/book_add")
    public  String showBookAddPage(Model model){
        List<TPublish> publishList = publishManagerService.getPublishList();
        List<TType> typeList = typeService.getTypeList();
        model.addAttribute("publishList",publishList) ;
        model.addAttribute("typeList",typeList) ;
        return "manager/book_add";
    }
    @RequestMapping("/asset_analyze")
    public String showResourceAnalyzePage(){
        return "manager/asset_analyze";
    }
    @RequestMapping("/borrow_history")
    public String showBorrowHistoryPage(){
        return "manager/borrow_history";
    }


    @RequestMapping("/type_manager")
    public String showTypeManagerPage(){
        return "manager/type_manager";
    }
    @RequestMapping("/publish_house_manager")
    public String showPublishHouseManagerPage(){
        return "manager/publish_house_manager";
    }
    @RequestMapping("/book_info")
    public String showBookInfoPage(String id,Model model){
        BookInfo bookInfo = bookService.selectBookInfoById(id);
        model.addAttribute("bookInfo",bookInfo) ;
        return "manager/book_info" ;
    }

    @RequestMapping("/getBookInfo")
    @ResponseBody
    public TBook getBookInfo(String isbn){
        return bookService.getBookById(isbn);
    }
    @RequestMapping("/bookAdd")
    @ResponseBody
    public String bookAdd(QueryVo vo){

        boolean result = true;
        try {
            result = bookService.insertBook(vo);
        } catch (Exception e) {
            e.printStackTrace();
            result = false ;
        }
        if (result == true)
            return "0" ;
        return "1" ;
    }

    @RequestMapping("/publish_manager")
    @ResponseBody
    public String getPublishList(PageBean pageBean){
        PageBean pageBean1= publishManagerService.getPublishListByPageBean(pageBean);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }
    @RequestMapping("/bookPage_manager")
    @ResponseBody
    public String selectBookPage(PageBean pageBean, String dim){
        QueryVo vo = new QueryVo() ;
        vo.setDim(dim);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean pageBean1 = bookService.selectBookInfo(pageBean, vo);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_store")
    @ResponseBody
    public String delBook(String id){
        boolean status = bookService.delBook(id) ;
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/comment_manager")
    @ResponseBody
    public String selectCommentPage(PageBean pageBean, String isbn){
        QueryVo vo = new QueryVo() ;
        vo.setDim(isbn);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean commentInfo = bookService.selectCommentInfo(pageBean, vo);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_comment")
    @ResponseBody
    public String delComment(String commentId){
        boolean status = bookService.delComment(commentId) ;
        return status == true ? "0" : "1" ;
    }

    @RequestMapping("/comment_info")
    public String showCommentInfo(String commentId,String tsmc,Model model){
        CommentInfo commentInfo = bookService.selectCommentInfoById(commentId);
        model.addAttribute("tsmc",tsmc) ;
        model.addAttribute("commentInfo",commentInfo) ;
        return "manager/comment_info" ;
    }
    /**
     * 出版社管理部分
     * */
    @RequestMapping("/publish_del")
    @ResponseBody
    public String publishDel(String pubId){
        boolean status = publishManagerService.deletePublish(pubId);
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/publish_add")
    public String showPublishAdd(){
        return "manager/publish_add" ;
    }
    @RequestMapping("/publishAdd")
    @ResponseBody
    public String publishAdd(TPublish publish){
        boolean status = publishManagerService.addPublish(publish) ;
        return status == true ? "0" : "1" ;
    }

    @RequestMapping("/publish_edit")
    public String showPublishEdit(String pubId,Model model){
        TPublish publish = publishManagerService.getPublishById(pubId);
        model.addAttribute("publish",publish) ;
        return "manager/publish_edit" ;
    }

    @RequestMapping("/publishEdit")
    @ResponseBody
    public String publishUpdate(TPublish publish){
        boolean status = publishManagerService.updatePublish(publish);
        return status == true ? "0" : "1" ;
    }

    /**
     * 图书类型管理部分
     * */
    @RequestMapping("/typeManager")
    @ResponseBody
    public String typeManager(PageBean pageBean){
        PageBean commentInfo = typeService.getTypePageByPageBean(pageBean);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }
    @RequestMapping("/type_add")
    public String showTypeAdd(){
        return "manager/type_add" ;
    }
    @RequestMapping("/typeAdd")
    @ResponseBody
    public String publishAdd(TType type){
        boolean status = typeService.addType(type) ;
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/type_edit")
    public String showTypeEdit(String typeId,Model model){
        TType type = typeService.getTypeByTypeId(typeId);
        model.addAttribute("type",type) ;
        return "manager/type_edit" ;
    }
    @RequestMapping("/typeEdit")
    @ResponseBody
    public String typeUpdate(TType type){
        boolean status = typeService.updateType(type) ;
        return status == true ? "0" : "1" ;
    }
    @RequestMapping("/type_del")
    @ResponseBody
    public String typeDel(String typeId){
        boolean status = typeService.deleteTypeByTyepId(typeId) ;
        return status == true ? "0" : "1" ;
    }

    /**
     * 图书资产导出功能
     * */
    @RequestMapping("/book_export")
    @ResponseBody
    public String bookExport(HttpServletRequest request, HttpServletResponse response){
        //第一步：查询所有的数据
        List<BookInfo> bookInfos = bookService.selectAllBookInfo();
        //第二步：使用POI将数据写到Excel文件中
        //在内存中创建excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook() ;
        //创建标签页
        HSSFSheet sheet = hssfWorkbook.createSheet("图书数据");
        //创建标题行
        HSSFRow headRow = sheet.createRow(0) ;
        //设置标题行
        headRow.createCell(0).setCellValue("ISBN");
        headRow.createCell(1).setCellValue("图书名称");
        headRow.createCell(2).setCellValue("出版社名称");
        headRow.createCell(3).setCellValue("出版日期");
        headRow.createCell(4).setCellValue("作者");
        headRow.createCell(5).setCellValue("图书类型");
        headRow.createCell(6).setCellValue("图书提供者");
        headRow.createCell(7).setCellValue("位置");
        headRow.createCell(8).setCellValue("损毁程度");
        headRow.createCell(9).setCellValue("入库时间");
        headRow.createCell(10).setCellValue("状态");
        //遍历数据集合设置填入数据行内容
        for (BookInfo book:bookInfos) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(book.getIsbn()) ;
            dataRow.createCell(1).setCellValue(book.getSm()) ;
            dataRow.createCell(2).setCellValue(book.getCbsmc()) ;
            dataRow.createCell(3).setCellValue(book.getCbrq()) ;
            dataRow.createCell(4).setCellValue(book.getZz()) ;
            dataRow.createCell(5).setCellValue(book.getLxmc()) ;
            dataRow.createCell(6).setCellValue(book.getUname()) ;
            dataRow.createCell(7).setCellValue(book.getWz()) ;
            dataRow.createCell(8).setCellValue(book.getSh()) ;
            dataRow.createCell(9).setCellValue(book.getRksj()) ;
            dataRow.createCell(10).setCellValue(book.getStatus()) ;
        }
        //第三步：使用输出流进行文件下载（一个流、两个头）
        String filename = "图书数据.xls";
        String contentType = request.getServletContext().getMimeType(filename) ;
        try {
            ServletOutputStream out = response.getOutputStream();
            response.setContentType(contentType);
            //获取客户端浏览器类型
            String agent = request.getHeader("User-Agent");
            filename = FileDownloadUtils.encodeDownloadFilename(filename,agent) ;
            response.setHeader("content-disposition", "attachment;filename="+filename) ;
            hssfWorkbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return null ;
        }
    }

    /**
     * 借阅历史
     * */
    @RequestMapping("/historyPage_manager")
    @ResponseBody
    public String HistoryPageManager(PageBean pageBean){
        //System.err.print("\n"+pageBean.getStartTime()+"/"+pageBean.getEndTime()+"/"+pageBean.getDim()+"/"+pageBean.getUser()+"/"+pageBean.getPage()+"/"+pageBean.getPagesize());
        PageBean commentInfo = bookService.selectHistoryInfo(pageBean);
        return JSON.toJSONString(commentInfo).replaceAll("rows","Rows").replaceAll("total","Total");
    }
    @RequestMapping("/history_info")
    public String HistoryInfo(Integer id,Model model){
        HistoryInfo history = bookService.selectHistoryById(id);
        model.addAttribute("history",history) ;
        return "manager/history_info";
    }
    /**
     * 图书分析控制
     */
    @RequestMapping("/bookAnalyze")
    public String showBookAnalyzePage(){
        return "manager/bookAnalyze";
    }

    /**
     * 异步获取数据
     */
    @RequestMapping(value="/borrowInfo",method=RequestMethod.GET)
    public @ResponseBody List<TBookAnalyze> charts(){
        List<TBookAnalyze> user =bookAnalyzeService.getBoorowNum();
        return user;
    }


}
