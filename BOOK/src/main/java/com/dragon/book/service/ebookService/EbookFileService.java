package com.dragon.book.service.ebookService;


import com.dragon.book.model.TEBook;
import com.dragon.book.model.TEBookVo;
import com.dragon.book.model.TType;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface EbookFileService {

    FTPClient getFtpConfig();

    List<String> getDirectories(FTPClient ftpClient);

    String getDownloadUrl(String ftpPath, String fileName);

    boolean uploadEbookFile(FTPClient ftpClient, InputStream inputStream, String ftpPath, String fileName);

    boolean saveEbookFile(TEBook teBook);

    // 获取ftp服务器上文件的inputStream
    InputStream getFtpInputStream(FTPClient ftpClient, String fileName, String ftpPath);

    TType getTTypeByPk(int pk);

    List<TType> getTypeList();

    TEBook getSingleEbook(String ebookId);

    List<TEBookVo> getTEBookVoList(Map filter);

    Integer getCount(Map filter);

    boolean ebookFileSingleOrMulUpload(MultipartFile[] ebookFile, TEBook teBook);

    boolean serviceDownload(InputStream inputStream, OutputStream outputStream);
}
