package com.dragon.book.common;

import com.dragon.book.config.FtpConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.SocketException;

/**
 * @ClassName AbFileSystems
 * @Description 文件系统抽象类
 * @Author Administrator
 * @Date 2019/4/4  10:01
 * @Version 1.0
 */
public abstract class AbFileSystems {
    private final static Logger logger = LoggerFactory.getLogger(FtpFileSystem.class);

    @Autowired
    private FtpConfig ftpConfig;
    /**
     * 默认写的是FTP的连接获取,子类为其他文件系统的时候
     *要重写这个方法，获取相对应的连接
     * @return ftpClient 对象
     */
    public Object getServerConnection() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            System.out.println("ftpConfig------------HOST:"+ftpConfig.getFtpHost());
            ftpClient.connect(ftpConfig.getFtpHost(), ftpConfig.getFtpPort()); // 连接FTP服务器
            ftpClient.login(ftpConfig.getFtpUserName(), ftpConfig.getFtpPassWord());// 登陆FTP服务器

            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftpClient.disconnect();
            } else {
                logger.info("AbFileSystems  FTP连接成功");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.error("FTP的IP地址可能错误," + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("FTP的端口可能错误," + e.getMessage());
        }
        return ftpClient;
    }

    public abstract void closeConnection();
}
