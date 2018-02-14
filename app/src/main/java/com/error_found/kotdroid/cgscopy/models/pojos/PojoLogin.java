package com.error_found.kotdroid.cgscopy.models.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user12 on 14/2/18.
 */

public class PojoLogin implements Parcelable{

    public String name;
    public String image;
    public String gender;
    public Integer guest_id;
    public String date;
    public String time;
    public String message;
    public String session_id;
    public Profile profile;


    protected PojoLogin(Parcel in) {
        name = in.readString();
        image = in.readString();
        gender = in.readString();
        if (in.readByte() == 0) {
            guest_id = null;
        } else {
            guest_id = in.readInt();
        }
        date = in.readString();
        time = in.readString();
        message = in.readString();
        session_id = in.readString();
        profile = in.readParcelable(Profile.class.getClassLoader());
    }

    public static final Creator<PojoLogin> CREATOR = new Creator<PojoLogin>() {
        @Override
        public PojoLogin createFromParcel(Parcel in) {
            return new PojoLogin(in);
        }

        @Override
        public PojoLogin[] newArray(int size) {
            return new PojoLogin[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(gender);
        if (guest_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(guest_id);
        }
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(message);
        parcel.writeString(session_id);
        parcel.writeParcelable(profile, i);
    }
}
