package com.kevin.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Create by Kevin-Tu on 2019/7/24.
 */
public class User implements Parcelable {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

/*    public void readFromParcel(Parcel dest) {
        id = dest.readInt();
        name = dest.readString();
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
