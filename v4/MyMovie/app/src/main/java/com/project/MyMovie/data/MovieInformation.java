package com.project.MyMovie.data;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieInformation implements Parcelable {

    public int id;
    public String title;
    public String title_eng;
    public String date;
    public float user_rating;
    public float audience_rating;
    public float reviewer_rating;
    public float reservation_rate;
    public int reservation_grade;
    public int grade;
    public String thumb;
    public String image;

    public MovieInformation(int id, String title){
        this.id = id;
        this.title = title;
    }

    public MovieInformation(Parcel src){
        id = src.readInt();
        title = src.readString();
        title_eng = src.readString();
        date = src.readString();
        user_rating = src.readFloat();
        audience_rating = src.readFloat();
        reviewer_rating = src.readFloat();
        reservation_rate = src.readFloat();
        reservation_grade = src.readInt();
        grade = src.readInt();
        thumb = src.readString();
        image = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public MovieInformation createFromParcel(Parcel src){
            return new MovieInformation(src);
        }

        public MovieInformation[] newArray(int size){
            return new MovieInformation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(title_eng);
        dest.writeString(date);
        dest.writeFloat(user_rating);
        dest.writeFloat(audience_rating);
        dest.writeFloat(reviewer_rating);
        dest.writeFloat(reservation_rate);
        dest.writeInt(reservation_grade);
        dest.writeInt(grade);
        dest.writeString(thumb);
        dest.writeString(image);
    }
}

