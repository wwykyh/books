package com.dragon.book.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class FtpUtils {

    private static FTPClient ftpClient = null;

    /**
     * @param ftpHost     地址
     * @param ftpPort     端口号
     * @param ftpUserName 用户名
     * @param ftpPassword 密码
     * @return 客户端的对象
     */
    public static FTPClient getLocalFtpConnection(String ftpHost, String ftpUserName,
                                                  String ftpPassword, int ftpPort) {
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
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
            ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes("GBK"), StandardCharsets.ISO_8859_1));

            System.out.println(fileName);
            OutputStream out = ftpClient.storeFileStream(new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
            result = fileReadWrite(input, out);

            System.out.println(fileName + " 上传状态:" + result);
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
     * @param input 输入流
     * @param out   输出流
     * @return     读写结果
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
            System.out.println("当前文件夹：" + new String(ftpClient.printWorkingDirectory().getBytes(StandardCharsets.ISO_8859_1), "GBK"));
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.getName().equals(fileName)) {
                    inputStream = ftpClient.retrieveFileStream(new String(fileName.getBytes("GBK"), StandardCharsets.ISO_8859_1));
                    System.out.println("inputStream: " + inputStream);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到" + fileName + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取错误。");
            e.printStackTrace();
        }finally {
            FtpUtils.closeConnection(ftpClient);
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

    /**
     * 列出服务器上所有的文件和目
     */
    public static void ListNamesAll() {
        String s = "";
        try {
            String[] files = ftpClient.listNames();
            String[] fileName = new String[files.length];
            if (files.length == 0) {
                System.out.println("没有任何文件");
            } else {
                for (int i = 0; i < files.length; i++) {
                    fileName[i] = new String(files[i].getBytes(StandardCharsets.ISO_8859_1), "GBK");
                    System.out.println(fileName[i]);
                }
                System.out.println("编码格式：" + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     *
     * @param files 删除的文件对象
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        FTPClient ftpClient = getLocalFtpConnection("172.16.101.245", "test", "test", 21);
        System.out.println(ftpClient);

        //ListNamesAll();
        //closeConnection(); Java线程(第三版).pdf 操作系统原理.PDF

        String ftpPath = "/计算机科普/";
        String localPath = "D:/编程相关";
        String fileName = "Java线程(第三版).pdf";
        //下载一个文件
        // download(ftpClient, fileName, localPath, ftpPath);

        getFileStream(ftpClient, fileName, ftpPath);

        //上传一个文件
        /*try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\学习笔记\\笔记.txt");
            boolean test = upload(ftpClient,file,"shu/");
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }*/

        //在FTP服务器上生成一个文件，并将一个字符串写入到该文件中

        /*try {
            InputStream input = new ByteArrayInputStream("test ftp jyf".getBytes("GBK"));
            boolean flag = FtpUtils.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName, input);
            System.out.println(flag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }
}
