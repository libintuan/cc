package com.sunset.grass.service.excel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpUtil {
    @Value("${spring.redis.host}")
    String ip;
    private final int dataTimeout=1000;
    private final int conTimeout=10000;

    public boolean uploadToFtp(File file){
        FTPClient ftpClient = new FTPClient();
        try {
            //连接ftp服务器 参数填服务器的ip
            try {
                ftpClient.setDataTimeout(dataTimeout);
                ftpClient.setConnectTimeout(conTimeout);
                ftpClient.connect(ip,22);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("啊啊啊啊啊啊啊");
            //进行登录 参数分别为账号 密码
            ftpClient.login("root","li200904218@");

            //改变工作目录（按自己需要是否改变）
            //只能选择local_root下已存在的目录
            System.out.println("呜呜呜呜呜呜呜呜");
            ftpClient.changeWorkingDirectory("/usr/local");

            System.out.println("喂喂喂喂喂喂喂");
            //设置文件类型为二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //开启被动模式（按自己如何配置的ftp服务器来决定是否开启）
            //ftpClient.enterLocalPassiveMode();

            //上传文件 参数：上传后的文件名，输入流
            ftpClient.storeFile(file.getName(), new FileInputStream(file));

            System.out.println(file.getName());
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
