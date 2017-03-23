package com.example.naumov.illia.illianaumov.transitionimage.mvp.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by illia_naumov.
 */

public class NewsPost implements Parcelable {
    private String title;
    private String text;
    private String imageUrl;
    private Date date;

    public NewsPost(String title, String text, String imageUrl, Date date) {
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.imageUrl);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
    }

    protected NewsPost(Parcel in) {
        this.title = in.readString();
        this.text = in.readString();
        this.imageUrl = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Creator<NewsPost> CREATOR = new Creator<NewsPost>() {
        @Override
        public NewsPost createFromParcel(Parcel source) {
            return new NewsPost(source);
        }

        @Override
        public NewsPost[] newArray(int size) {
            return new NewsPost[size];
        }
    };
}
