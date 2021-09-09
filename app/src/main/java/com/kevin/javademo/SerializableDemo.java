package com.kevin.javademo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by tuchuantao on 2021/9/8
 * Desc:
 */
public class SerializableDemo implements Serializable {

    private volatile static SerializableDemo singleton;

    private SerializableDemo() {
    }

    public static SerializableDemo getSingleton() {
        if (singleton == null) {
            synchronized (SerializableDemo.class) {
                if (singleton == null) {
                    singleton = new SerializableDemo();
                }
            }
        }
        return singleton;
    }

//    private Object readResolve() {
//        return singleton;
//    }

    public static void main(String[] args) {
        //Write Obj to file
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(SerializableDemo.getSingleton());
            //Read Obj from file
            File file = new File("tempFile");
            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
            SerializableDemo newInstance = (SerializableDemo) ois.readObject();
            //判断是否是同一个对象
            System.out.println(newInstance == SerializableDemo.getSingleton());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
