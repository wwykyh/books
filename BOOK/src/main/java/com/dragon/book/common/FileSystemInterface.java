package com.dragon.book.common;

import org.springframework.web.multipart.MultipartFile;


public interface FileSystemInterface {
    boolean uploadFile(MultipartFile file, String path);

    boolean downloadFile(String fileName, String ftpPath);

}
