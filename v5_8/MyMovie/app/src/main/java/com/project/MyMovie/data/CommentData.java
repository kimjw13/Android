package com.project.MyMovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentData implements Parcelable {

    int total;
    int recommend;
    String writer;
    String review_id;
    String writer_image;
    String time;
    String timestamp;
    String contents;
    float rating;

    public CommentData(int total, int recommend, String writer, String review_id, String writer_image, String time, String timestamp, String contents, float rating) {
        this.total = total;
        this.recommend = recommend;
        this.writer = writer;
        this.review_id = review_id;
        this.writer_image = writer_image;
        this.time = time;
        this.timestamp = timestamp;
        this.contents = contents;
        this.rating = rating;
    }

    public CommentData(Parcel src){
        total = src.readInt();
        recommend = src.readInt();
        writer = src.readString();
        review_id = src.readString();
        writer_image = src.readString();
        time = src.readString();
        timestamp = src.readString();
        contents = src.readString();
        rating = src.readFloat();

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getWriter_image() {
        return writer_image;
    }

    public void setWriter_image(String writer_image) {
        this.writer_image = writer_image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public CommentData createFromParcel(Parcel in){
            return new CommentData(in);
        }

        @Override
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
        dest.writeInt(total);
        dest.writeInt(recommend);
        dest.writeString(writer);
        dest.writeString(review_id);
        dest.writeString(writer_image);
        dest.writeString(time);
        dest.writeString(timestamp);
        dest.writeString(contents);
        dest.writeFloat(rating);
    }
}
