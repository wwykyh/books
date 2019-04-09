package com.dragon.book.common;


import com.dragon.book.config.FtpConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @ClassName FtpServer
 * @Description ftp文件上传卸载实现类
 * @Author Administrator
 * @Date 2019/4/2  14:50
 * @Version 1.0
 */

@Service
public class FtpFileSystem extends AbFileSystems implements FileSystemInterface {

    private final static Logger logger = LoggerFactory.getLogger(FtpFileSystem.class);

    private static FTPClient ftpClient;

    @Autowired
    private FtpConfig ftpConfig;

    @PostConstruct
    public void init() {
        ftpClient = (FTPClient) this.getServerConnection();
    }

    /**
     * @return ftpClient 对象
     */
    @Override
    protected Object getServerConnection() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
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
    /**
     * @param file 上传的文件
     * @param path 路径
     * @return 上传成功或者失败
     */
    @Override
    public boolean uploadFile(MultipartFile file, String path) {
        boolean result = false;
        init();
        try {
            InputStream input = file.getInputStream();

            String uploadPath = new String(path.getBytes("GBK"), StandardCharsets.ISO_8859_1);
            boolean dirRe = ftpClient.changeWorkingDirectory(uploadPath);

            if (!dirRe) {
                ftpClient.makeDirectory(uploadPath);
                ftpClient.changeWorkingDirectory(uploadPath);
            }
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            OutputStream out = ftpClient.storeFileStream(new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
            result = fileReadWrite(input, out);
            logger.info(fileName + " 上传状态:" + result);

            input.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public boolean downloadFile(String fileName, String path) {
        boolean result = false;
        try {
            HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
            assert response != null;
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream inputStream = getFileStream(fileName, "/" + path + "/");

            // 文件流操作
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 将输入流中的数据写入到输出流中
            result = fileReadWrite(bufferedInputStream, bufferedOutputStream);

            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 实现AbFileSystems 关闭连接
    @Override
    public void closeConnection() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传下载的文件读写方法
     *
     * @param input 输入流
     * @param out   输出流
     * @return 读写结果
     */
    private boolean fileReadWrite(InputStream input, OutputStream out) {
        try {
            byte[] byteArray = new byte[4096];
            int read;
            while ((read = input.read(byteArray)) != -1) {
                out.write(byteArray, 0, read);
            }
        } catch (IOException e) {
            logger.warn("fileReadWrite异常------->"+e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * @param fileName 文件名
     * @param ftpPath  文件路径
     * @return 返回文件对应的输入流
     */
    private InputStream getFileStream(String fileName, String ftpPath) {
        InputStream inputStream = null;
        init();
        try {
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
            logger.warn("没有找到" + fileName + "文件," + e.getMessage());
            e.printStackTrace();
        } catch (SocketException e) {
            logger.warn("连接FTP失败 " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.warn("文件读取错误" + e.getMessage());
            e.printStackTrace();
        }
        return inputStream;
    }
}
