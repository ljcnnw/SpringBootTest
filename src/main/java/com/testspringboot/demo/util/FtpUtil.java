package com.testspringboot.demo.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FtpUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * ftp站点
     */
    @Value("${ftp.host}")
    private String ftpHost;
    /**
     * ftp端口号
     */
    @Value("${ftp.port}")
    private int ftpPort;
    /**
     * ftp访问用户名
     */
    @Value("${ftp.username}")
    private String ftpUsername;
    /**
     * ftp访问密码
     */
    @Value("${ftp.password}")
    private String ftpPassword;
    /**
     * ftp访问文件路径
     */
    @Value("${ftp.filepath}")
    private String ftpFilepath;

    private FTPClient ftpClient;


    public boolean ftpConnect() {
        ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(1000 * 30);
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.setDefaultPort(ftpPort);
        try {
            if (!ftpClient.isConnected()) {
                ftpClient.connect(ftpHost, ftpPort);
            }
            ftpClient.login(ftpUsername, ftpPassword);
            logger.info("===========链接FTP服务器============");
            int reply = ftpClient.getReplyCode();
            logger.info("===========FTP服务器返回code:"+reply+"============");
            if (FTPReply.isPositiveCompletion(reply)) {
                logger.info("=============链接FTP成功===========");
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传文件
     *
     * @param pathname
     *            ftp服务保存地址
     * @param fileName
     *            上传到ftp的文件名
     * @param originfilename
     *            待上传文件的名称（绝对地址） *
     * @return
     */
    public boolean uploadFile(String pathname, String fileName, String originfilename) {
        InputStream inputStream = null;
        try {
            logger.info("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            boolean flag = ftpConnect();
            if(!flag){
                logger.info("未连接到FTP服务器");
                return false;
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            // 观察是否真的上传成功
            boolean storeFlag = ftpClient.storeFile(fileName, inputStream);
            logger.info("storeFlag==" + storeFlag);
            inputStream.close();
            ftpClient.logout();
            logger.info("上传文件成功");
        } catch (Exception e) {
            logger.info("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    logger.info("=============关闭FTP服务器链接===========");
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    public void init() {

    }

}
