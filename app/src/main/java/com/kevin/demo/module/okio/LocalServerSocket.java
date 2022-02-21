package com.kevin.demo.module.okio;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tuchuantao on 2020/11/2
 */
public class LocalServerSocket {

    private Thread mThread;

    public void startServer() {
        stopServerIfNeed();
        mThread = new Thread(() -> {
            try {
                ServerSocketChannel serverSocket = ServerSocketChannel.open();
                serverSocket.socket().bind(new InetSocketAddress("127.0.0.1", 6666));
                serverSocket.configureBlocking(false);

                Selector selector = Selector.open();
                // 注册 channel，并且指定感兴趣的事件是 Accept
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);

                ByteBuffer readBuff = ByteBuffer.allocate(1024);
                ByteBuffer writeBuff = ByteBuffer.allocate(128);
                writeBuff.put("received".getBytes());
                writeBuff.flip();

                while (true) {
                    int nReady = selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();

                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();

                        if (key.isAcceptable()) {
                            // 创建新的连接，并且把连接注册到selector上，而且，
                            // 声明这个channel只对读操作感兴趣。
                            SocketChannel socketChannel = serverSocket.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                        else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            readBuff.clear();
                            socketChannel.read(readBuff);

                            readBuff.flip();
                            Log.d("LocalServerSocket", "received : " + new String(readBuff.array(), readBuff.position(), readBuff.limit()));
                            key.interestOps(SelectionKey.OP_WRITE);
                        }
                        else if (key.isWritable()) {
                            writeBuff.rewind();
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            socketChannel.write(writeBuff);
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }

            } catch (IOException e) {

            }
        });
        mThread.start();
    }

    public void stopServerIfNeed() {
        if (mThread != null && mThread.isAlive()) {
            mThread.interrupt();
            mThread = null;
        }
    }
}
