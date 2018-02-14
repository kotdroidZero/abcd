package com.error_found.kotdroid.cgscopy.models.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user12 on 14/2/18.
 */

public class Profile implements Parcelable{

    public Integer owner_id;
    public String owner_email;
    public String owner_name;
    public String owner_phone_no;
    public String owner_image;
    public String owner_apartment_no;
    public String push_notification;
    public String qr_code;
    public String is_present;
    public String location_lat;
    public String location_long;

    protected Profile(Parcel in) {
        if (in.readByte() == 0) {
            owner_id = null;
        } else {
            owner_id = in.readInt();
        }
        owner_email = in.readString();
        owner_name = in.readString();
        owner_phone_no = in.readString();
        owner_image = in.readString();
        owner_apartment_no = in.readString();
        push_notification = in.readString();
        qr_code = in.readString();
        is_present = in.readString();
        location_lat = in.readString();
        location_long = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (owner_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(owner_id);
        }
        parcel.writeString(owner_email);
        parcel.writeString(owner_name);
        parcel.writeString(owner_phone_no);
        parcel.writeString(owner_image);
        parcel.writeString(owner_apartment_no);
        parcel.writeString(push_notification);
        parcel.writeString(qr_code);
        parcel.writeString(is_present);
        parcel.writeString(location_lat);
        parcel.writeString(location_long);
    }
}
