package com.project.MyMovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentData implements Parcelable {
    String name;
    String mention;

    public CommentData(String name, String mention){
        this.name = name;
        this.mention = mention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public CommentData(Parcel src){
        name = src.readString();
        mention = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public CommentData createFromParcel(Parcel src){
            return new CommentData(src);
        }

        public CommentData[] newArray(int size){
            return new CommentData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(mention);
    }
}
