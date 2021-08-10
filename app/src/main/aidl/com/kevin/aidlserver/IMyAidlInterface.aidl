// IMyAidlInterface.aidl
package com.kevin.aidlserver;

import com.kevin.aidlserver.User; // note 不要忘记导包，哪怕在同一包下

// Declare any non-default types here with import statements
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int add(int value, int value2);

    void inUserInfo(in User user);

    void outUserInfo(out User user);

    void inOutUserInfo(inout User user);


    User getUserInfo();
}
