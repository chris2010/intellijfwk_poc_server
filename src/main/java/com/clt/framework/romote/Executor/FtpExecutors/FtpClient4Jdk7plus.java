package com.clt.framework.romote.Executor.FtpExecutors;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/**
 * Created by liujinbang on 15/9/6.
 *
 *   使用JDK7+ 的client实现FTP
 *
 */
public class FtpClient4Jdk7plus {


    // FTP客户端
    private FtpClient ftpClient;

    /**
     * 服务器连接
     *
     * @param ip
     *            服务器IP
     * @param port
     *            服务器端口
     * @param user
     *            用户名
     * @param password
     *            密码
     * @param path
     *            服务器路径
     */
    public void connectServer(String ip, int port, String user,
                              String password, String path) {
        try {
            ftpClient = FtpClient.create();
            try {
                SocketAddress addr = new InetSocketAddress(ip, port);
                this.ftpClient.connect(addr);
                this.ftpClient.login(user, password.toCharArray());
                System.out.println("login success!");
                if (path.length() != 0) {
                    // 把远程系统上的目录切换到参数path所指定的目录
                    this.ftpClient.changeDirectory(path);
                }
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 上传文件
     *
     * @param localFile
     *            本地文件
     * @param remoteFile
     *            远程文件
     */
    public void upload(String localFile, String remoteFile) {
        //this.localFilename = localFile;
        //this.remoteFilename = remoteFile;
        OutputStream os = null;
        FileInputStream is = null;
        try {
            try {
                // 将远程文件加入输出流中
                os = this.ftpClient.putFileStream(remoteFile);
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
            // 获取本地文件的输入流
            File file_in = new File(localFile);
            is = new FileInputStream(file_in);
            // 创建一个缓冲区
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            System.out.println("upload success");
        } catch (IOException ex) {
            System.out.println("not upload");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件下载
     *
     * @param remoteFile
     *            远程文件
     * @param localFile
     *            本地文件
     */
    public void download(String remoteFile, String localFile) {
        InputStream is = null;
        FileOutputStream os = null;
        try {
            try {
                // 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地
                is = this.ftpClient.getFileStream(remoteFile);
            } catch (FtpProtocolException e) {
                e.printStackTrace();
            }
            File file_in = new File(localFile);
            os = new FileOutputStream(file_in);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
            System.out.println("download success");
        } catch (IOException ex) {
            System.out.println("not download");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnect() {
        try {
            this.ftpClient.close();
            System.out.println("disconnect success");
        } catch (IOException ex) {
            System.out.println("not disconnect");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }


}
