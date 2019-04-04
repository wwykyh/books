package com.dragon.book.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
 * ftp服务器类，用来初始化ftp的属性参数
 */
@SpringBootApplication
@RestController
public class FtpConfig {

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private Integer ftpPort ;

    @Value("${ftp.username}")
    private String ftpUserName;

    @Value("${ftp.password}")
    private String ftpPassWord;

    @Value("${ftp.path}")
    private String ftpPath;

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(int ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassWord() {
        return ftpPassWord;
    }

    public void setFtpPassWord(String ftpPassWord) {
        this.ftpPassWord = ftpPassWord;
    }

    public String getFtpPath() {
        return ftpPath;
    }

    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath;
    }

    @Override
    public String toString() {
        return "FtpConfig{" +
                "ftpHost='" + ftpHost + '\'' +
                ", ftpPort=" + ftpPort +
                ", ftpUserName='" + ftpUserName + '\'' +
                ", ftpPassWord='" + ftpPassWord + '\'' +
                ", ftpPath='" + ftpPath + '\'' +
                '}';
    }
}
