package com.boyue.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {

        //1.创建线程池
        //2.如果有客户端连接，创建一个线程与之通讯
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //创建serverSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了...");

        while(true){
            //监听，等待客户端连接
            final Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端...");

            threadPool.execute(()->{
                try {
                    handle(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static void handle(Socket socket) throws IOException {

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            //循环读取客户端发送的数据 阻塞
            while(true){
                int read = inputStream.read(bytes);
                if(read !=-1){
                    //等于-1时读完
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭连接...");
            socket.close();
        }


    }


}
