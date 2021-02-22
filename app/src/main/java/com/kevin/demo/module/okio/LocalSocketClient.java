package com.kevin.demo.module.okio;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by tuchuantao on 2020/11/2
 */
public class LocalSocketClient {

    private Thread mThread;

    private static int number = 0;

    public void startClient() {
        stopIfNeed();
        number++;
        mThread = new Thread(() -> {
            try {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666));

                ByteBuffer writeBuffer = ByteBuffer.allocate(32);
                ByteBuffer readBuffer = ByteBuffer.allocate(32);

                writeBuffer.put(("client " + number + " hello").getBytes());
                writeBuffer.flip();

                while (true) {
                    writeBuffer.rewind();
                    socketChannel.write(writeBuffer);
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    Log.d("LocalServerSocket", "Client " + number + " received : " + new String(readBuffer.array(), 0, readBuffer.position()));
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mThread.start();
    }

    public void stopIfNeed() {
        if (mThread != null && mThread.isAlive()) {
            mThread.stop();
            mThread = null;
        }
    }
}

