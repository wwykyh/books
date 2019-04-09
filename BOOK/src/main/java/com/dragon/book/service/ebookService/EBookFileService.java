package com.dragon.book.service.ebookService;

import com.dragon.book.model.TEBook;
import com.dragon.book.model.TType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 电子书上传下载服务接口
 */
public interface EBookFileService {

    boolean finalDownload(TEBook teBook);

    boolean eBookFileSingleOrMulUpload(MultipartFile[] eBookFile, TEBook teBook);

    boolean saveEBookFile(TEBook teBook);

    TType getTTypeByPk(int pk);

    List<TType> getTypeList();

    // 下载后更新数据库中的数据
    void updateDownloadDate(TEBook teBook);
}