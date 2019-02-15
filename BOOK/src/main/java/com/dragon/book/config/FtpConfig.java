package com.dragon.book.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 用来测试注解获取配置文件的ftp参数
     * @return 返回ftp配置参数
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/value")
    public Map<String, Object> value(){
        Map<String, Object> map = new HashMap<>();
        map.put("type", ftpHost);
        map.put("ftpPort", ftpPort);
        map.put("ftpUserName", ftpUserName);
        map.put("ftpPassWord", ftpPassWord);
        // *.properties文件中的中文默认以ISO-8859-1方式编码，因此需要对中文内容进行重新编码
        // map.put("title", new String(title.getBytes("ISO-8859-1"), "UTF-8"));
        return map;
    }
    public  static void main(String [] args){
        SpringApplication application = new SpringApplication(FtpConfig.class);
        application.run(args);
    }
}
