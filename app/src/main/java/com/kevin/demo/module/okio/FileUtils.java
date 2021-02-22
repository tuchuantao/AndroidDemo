package com.kevin.demo.module.okio;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by tuchuantao on 2020/10/29
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();
    private static final String DIRECTORY = "iotest";

    /**
     * java io
     */
    public static void ioCopyFile() {
        File dir = getIOTestDirectory();
        if (dir == null) {
            return;
        }
        File sourceFile = new File(dir.getPath() + File.separator + "io_test_content.pdf");
        File destFile = new File(dir.getPath() + File.separator + "io_test_content_copy.pdf");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            byte[] arr = new byte[8192];
            int len;
            long startTime = System.currentTimeMillis();
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
            }
            Log.v(TAG, "IO consume time=" + (System.currentTimeMillis() - startTime));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "ioWrite() exception: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "ioWrite() exception: " + e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void nioCopyFile() {
        File dir = getIOTestDirectory();
        if (dir == null) {
            return;
        }
        File sourceFile = new File(dir.getPath() + File.separator + "io_test_content.pdf");
        File destFile = new File(dir.getPath() + File.separator + "io_test_content_copy.pdf");

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile);
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = fos.getChannel()) {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();

            // 方案1
            ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
            //ByteBuffer buffer = ByteBuffer.allocate(8192);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

            // 方案2
            /*int pos = 0;
            long size = inChannel.size();
            long tranLen;
            while (pos < size) {
                tranLen = inChannel.transferTo(pos, size, outChannel);
                //Log.v(TAG, "Nio transfer len=" + tranLen);
                if (tranLen <= 0) {
                    break;
                }
                pos += tranLen;
            }*/
            outChannel.transferFrom(inChannel, 0 ,inChannel.size());
            Log.v(TAG, "Nio consume time=" + (System.currentTimeMillis() - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void okioCopyFile() {
        File dir = getIOTestDirectory();
        if (dir == null) {
            return;
        }
        File sourceFile = new File(dir.getPath() + File.separator + "io_test_content.pdf");
        File destFile = new File(dir.getPath() + File.separator + "io_test_content_copy.pdf");
        try(BufferedSource source = Okio.buffer(Okio.source(sourceFile));
            BufferedSink sink = Okio.buffer(Okio.sink(destFile))) {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            sink.writeAll(source);
            Log.v(TAG, "Okio consume time=" + (System.currentTimeMillis() - startTime));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData() {
        File dir = getIOTestDirectory();
        if (dir == null) {
            return;
        }
        String name = "xiaoming";
        int age = 18;
        File file = new File(dir.getPath() + File.separator + "output.txt");
        // 普通IO
        try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            if (!file.exists()) {
                file.createNewFile();
            }
            dataOutputStream.writeUTF(name);
            dataOutputStream.writeInt(18);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Okio
        try(BufferedSink sink = Okio.buffer(Okio.sink(file))) {
            if (!file.exists()) {
                file.createNewFile();
            }
            sink.writeUtf8(name).writeInt(age);
            //Okio.buffer(Okio.sink(file)).writeUtf8(name).writeInt(age).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getIOTestDirectory() {
        File sdDir = Environment.getExternalStorageDirectory();
        if (sdDir == null) {
            return null;
        }
        File sourcePath = new File(sdDir.getPath() + File.separator + DIRECTORY);
        if (!sourcePath.exists()) {
            sourcePath.mkdirs();
        }
        if (!sourcePath.exists()) {
            sourcePath = null;
        }
        return sourcePath;
    }

    private void testAutoClose() {
        try (FileInputStream fileInputStream = new FileInputStream(new File("filePath"))) {
            fileInputStream.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void normalClose() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("filePath"));
            fileInputStream.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
