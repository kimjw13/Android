package com.project.MyMovie.data;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetailData implements Parcelable{

    public String title;
    public String date;
    public float user_rating;
    public float audience_rating;
    public float reviewer_rating;
    public float reservation_rate;
    public int reservation_grade;
    public int grade;
    public String thumb;
    public String image;
    public String photo;
    public String videos;
    public String outLinks;
    public String genre;
    public int duration;
    public int audience;
    public String synopsis;
    public String director;
    public String actor;
    public int like;
    public int dislike;

    public MovieDetailData(Parcel src){
        title = src.readString();
        date = src.readString();
        user_rating = src.readFloat();
        audience_rating = src.readFloat();
        reviewer_rating = src.readFloat();
        reservation_rate = src.readFloat();
        reservation_grade = src.readInt();
        grade = src.readInt();
        thumb = src.readString();
        image = src.readString();
        photo = src.readString();
        videos = src.readString();
        outLinks = src.readString();
        genre = src.readString();
        duration = src.readInt();
        audience = src.readInt();
        synopsis = src.readString();
        director = src.readString();
        actor = src.readString();
        like = src.readInt();
        dislike = src.readInt();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public MovieDetailData createFromParcel(Parcel src){
            return new MovieDetailData(src);
        }

        public MovieDetailData[] newArray(int size){
            return new MovieDetailData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(date);
        dest.writeFloat(user_rating);
        dest.writeFloat(audience_rating);
        dest.writeFloat(reviewer_rating);
        dest.writeFloat(reservation_rate);
        dest.writeInt(reservation_grade);
        dest.writeInt(grade);
        dest.writeString(thumb);
        dest.writeString(image);
        dest.writeString(photo);
        dest.writeString(videos);
        dest.writeString(outLinks);
        dest.writeString(genre);
        dest.writeInt(duration);
        dest.writeInt(audience);
        dest.writeString(synopsis);
        dest.writeString(director);
        dest.writeString(actor);
        dest.writeInt(like);
        dest.writeInt(dislike);

    }
}
