package com.kevin.demo.module.okio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tuchuantao on 2022/2/21
 * Desc:
 */
public class IOUtils {

  private Thread mServerThread;
  private Thread mThread;

  public void startServer() {
    stopServerIfNeed();
    mServerThread = new Thread(() -> {
      try {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
          Socket socket = serverSocket.accept();
          System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");
          BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          String mess = br.readLine();
          System.out.println("客户端：" + mess);
          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
          bw.write(mess + "\n");
          bw.flush();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    mServerThread.start();
  }

  public void startClient() {
    stopIfNeed();
    mThread = new Thread(() -> {
      try {
        Socket socket = new Socket("127.0.0.1", 8888);
        //构建IO
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        //向服务器端发送一条消息
        bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
        bw.flush();

        //读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String mess = br.readLine();
        System.out.println("服务器：" + mess);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    mThread.start();
  }

  public void stopServerIfNeed() {
    if (mServerThread != null && mServerThread.isAlive()) {
      mServerThread.interrupt();
      mServerThread = null;
    }
  }

  public void stopIfNeed() {
    if (mThread != null && mThread.isAlive()) {
      mThread.interrupt();
      mThread = null;
    }
  }
}
