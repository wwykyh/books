package com.dragon.book.service.impl;

import com.dragon.book.common.FileSystemInterface;
import com.dragon.book.common.FtpFileSystem;
import com.dragon.book.mapper.*;
import com.dragon.book.model.*;
import com.dragon.book.service.ebookService.EBookFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EBookFileServiceImpl implements EBookFileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TEBookMapper teBookMapper;

    @Autowired
    private TTypeMapper tTypeMapper;

    @Autowired
    private TUserBookMapper tUserBookMapper;

    @Autowired
    private TEbookDownMapper tEbookDownMapper;

    @Autowired
    private FileSystemInterface fileSystemInterface;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveEBookFile(TEBook teBook) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        teBook.setScsj(df.format(date));
        TType type = tTypeMapper.selectByPrimaryKey(teBook.getTypeId());
        String wjdz = type.getLxmc();
        teBook.setWjdz(wjdz);
        int row = teBookMapper.insert(teBook);
        TUserBook tUserBook = new TUserBook();
        tUserBook.setIsbn(teBook.geteBookId());
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        TSysUser user = (TSysUser) request.getSession().getAttribute("user");
        tUserBook.setUserId(user.getUserId());
        int row2 = tUserBookMapper.insert(tUserBook);
        return row > 0 && row2 > 0;
    }


    @Override
    public boolean finalDownload(TEBook teBook) {
        String fileName = teBook.geteBookXm();
        String ftpPath = teBook.getWjdz();
        return fileSystemInterface.downloadFile(fileName, "/" + ftpPath + "/");
    }


    @Override
    public TType getTTypeByPk(int pk) {
        return tTypeMapper.selectByPrimaryKey(pk);
    }

    @Override
    public List<TType> getTypeList() {
        TTypeExample example = new TTypeExample();
        return tTypeMapper.selectByExample(example);
    }

    // 封装的单个或多个文件上传代码
    @Override
    public boolean eBookFileSingleOrMulUpload(MultipartFile[] eBookFile, TEBook teBook) {
        boolean result = false, flag = false;
        TType tType = getTTypeByPk(teBook.getTypeId());
        // typeName就是上传的文件路径
        String typeName = tType.getLxmc();
        try {
            for (MultipartFile multipartFile : eBookFile) {
                // TODO 新的文件上传封装
                result = fileSystemInterface.uploadFile(multipartFile, typeName);

                String eBookId = UUID.randomUUID().toString().replace("-", "");
                // 上传到服务器的文件名
                String fileName = multipartFile.getOriginalFilename();

                if (result) {
                    teBook.seteBookId(eBookId);
                    // 存在数据库中的文件地址 ,这里只是存了文件名，目录就是type类型
                    teBook.seteBookXm(fileName);
                    teBook.setMs(teBook.getMs());
                    flag = saveEBookFile(teBook);
                }
            }
//            new FtpFileSystem().closeConnection();  // 关闭连接 这里有个bug
        } catch (Exception e) {
            logger.info("上传失败--->" + e.getMessage());
            e.printStackTrace();
        }
        return result && flag;
    }


    @Override
    public void updateDownloadDate(TEBook teBook) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String downloadTime = df.format(new Date());
        TEbookDown tEbookDown = new TEbookDown();
        tEbookDown.setEbookId(teBook.geteBookId());
        tEbookDown.setXzsj(downloadTime);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        TSysUser user = (TSysUser) request.getSession().getAttribute("user");
        tEbookDown.setUserId(user.getUserId());
        tEbookDownMapper.insert(tEbookDown);
    }
}
