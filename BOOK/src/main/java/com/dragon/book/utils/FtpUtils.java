package com.dragon.book.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class FtpUtils {

    private final static Logger logger = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * @param ftpHost     地址
     * @param ftpPort     端口号
     * @param ftpUserName 用户名
     * @param ftpPassword 密码
     * @return 客户端的对象
     */
    public static FTPClient getLocalFtpConnection(String ftpHost, String ftpUserName,
                                                  String ftpPassword, int ftpPort) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功");
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

    /**
     * @param ftpClient ftp客户端对象
     * @param input     上传的文件输入流
     * @param ftpPath   FTP服务器中文件所在路径 格式： ftptest/aa
     */
    public static boolean upload(FTPClient ftpClient, InputStream input, String ftpPath, String fileName) {
        boolean result = false;
        try {
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();   // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            String uploadPath = new String(ftpPath.getBytes("GBK"), StandardCharsets.ISO_8859_1);
            boolean dirRe = ftpClient.changeWorkingDirectory(uploadPath);

            if (!dirRe) {
                ftpClient.makeDirectory(uploadPath);
                ftpClient.changeWorkingDirectory(uploadPath);
            }
            logger.info("文件名：" + fileName);
            OutputStream out = ftpClient.storeFileStream(new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
            result = fileReadWrite(input, out);

            logger.info(fileName + " 上传状态:" + result);
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            closeConnection(ftpClient);
            return result;
        }
    }

    /**
     * 上传下载的文件读写方法
     *
     * @param input 输入流
     * @param out   输出流
     * @return 读写结果
     */
    public static boolean fileReadWrite(InputStream input, OutputStream out) {
        try {
            byte[] byteArray = new byte[4096];
            int read;
            while ((read = input.read(byteArray)) != -1) {
                out.write(byteArray, 0, read);
            }
            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param ftpClient ftp对象
     * @param fileName  要下载的文件名
     * @param ftpPath   文件所在路径 (即文件所在文件夹)
     * @return 返回一个inputStream ，controller用读取输入流返回到界面
     */
    public static InputStream getFileStream(FTPClient ftpClient, String fileName, String ftpPath) {
        InputStream inputStream = null;
        try {
            ftpClient.setControlEncoding("GBK");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes("GBK"), StandardCharsets.ISO_8859_1));

            logger.info("当前文件夹：" + new String(ftpClient.printWorkingDirectory().getBytes(StandardCharsets.ISO_8859_1), "GBK"));
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    inputStream = ftpClient.retrieveFileStream(new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
                    logger.info("inputStream: " + inputStream);
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("没有找到" + fileName + "文件," + e.getMessage());
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("文件读取错误" + e.getMessage());
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(FTPClient ftpClient) {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
