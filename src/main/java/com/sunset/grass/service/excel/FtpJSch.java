package com.sunset.grass.service.excel;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

@Service
public class FtpJSch {

    /*
    缺关闭连接
     */
    private static ChannelSftp sftp = null;
    private static Channel channel=null;
    private static Session sshSession=null;

    //账号
    private static String user = "root";
    //主机ip
    private static String host="8.129.232.204";
    //密码
    private static String password = "li200904218@";
    //端口
    private static int port = 22;
    //上传地址
    private static String directory = "/usr/local/1111";
    //下载目录
    private static String saveFile = "/Users/tuantuan/IdeaProjects/grass";
    //filename
    private static String fileNameEnd="test";

    public static void getConnect(){
        try {
            JSch jsch = new JSch();
            //获取sshSession 账号-ip-端口
            System.out.println("开始连接了。。。。。！！！！");
            sshSession =jsch.getSession(user, host,port);
            //添加密码
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking", "no");

            sshSession.setConfig(sshConfig);
            //开启sshSession链接
            sshSession.connect();
            System.out.println("连接完成了。。。。！！！！");
            //获取sftp通道
            channel = sshSession.openChannel("sftp");
            //开启
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param uploadFile 上传文件的路径
     * @return 服务器上文件名
     */
    public String upload(String uploadFile) {
        getConnect();
        System.out.println("连上了。。。。。！！！");
        File file = null;
        String fileName = null;
        try {
            sftp.cd(directory);
            file = new File(uploadFile);
            //获取随机文件名
//            fileName = UUID.randomUUID().toString() + file.getName().substring(file.getName().length()-5);
            fileName= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+fileNameEnd+file.getName().substring(file.getName().length()-5);
            //文件名是 随机数加文件名的后5位
            System.out.println("文件名。。。。。="+fileName);
            sftp.put(new FileInputStream(file), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeChannel(channel);
        closeSession(sshSession);
        return file == null ? null : fileName;
    }

    /**
     * 下载文件
     */
    public void download(String downloadFileName) {
        try {
            getConnect();
            sftp.cd(directory);
            Iterator iterator=sftp.ls(directory).iterator();
            while (iterator.hasNext()){
               ChannelSftp.LsEntry lsEntry=(ChannelSftp.LsEntry)iterator.next();
                downloadFileName=lsEntry.getFilename();
                SftpATTRS attrs= lsEntry.getAttrs();
                if (lsEntry.getFilename().equals(".") || lsEntry.getFilename().equals("..")) {
                    continue;
                }
                if (attrs.isDir()){
                    continue;
                }
                File file = new File(saveFile+File.separator+downloadFileName);
                System.out.println("下载文件名字为："+file.getName());
                sftp.get(downloadFileName, new FileOutputStream(file));
                System.out.println("下载完成"+downloadFileName);
            }
            closeChannel(channel);
            closeSession(sshSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param deleteFile
     * 要删除的文件名
     */
    public void delete(String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     *
     * 要列出的目录
     * @return
     * @throws SftpException
     */
    public Vector listFiles()
            throws SftpException {
        getConnect();
        Vector ls = sftp.ls(directory);
        closeChannel(channel);
        closeSession(sshSession);
        return ls;
    }
    private static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
