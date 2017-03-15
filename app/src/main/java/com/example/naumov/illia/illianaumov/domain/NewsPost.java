package com.example.naumov.illia.illianaumov.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by illia_naumov.
 */

public class NewsPost implements Parcelable {
    private String title;
    private String text;
    private String imageUrl;

    public NewsPost(String title, String text, String imageUrl) {
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.imageUrl);
    }

    protected NewsPost(Parcel in) {
        this.title = in.readString();
        this.text = in.readString();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<NewsPost> CREATOR = new Parcelable.Creator<NewsPost>() {
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
