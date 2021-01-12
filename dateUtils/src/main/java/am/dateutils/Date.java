package am.dateutils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class Date implements Parcelable {

    private String date;
    private String timezone_type;
    private String timezone;

    public Date() {
    }

    public Date(String date, String timezone) {
        this.date = date;
        this.timezone = timezone;
    }

    public Date(String date, String timezone_type, String timezone) {
        this.date = date;
        this.timezone_type = timezone_type;
        this.timezone = timezone;
    }

    //-------------------------------- Methods -----------------------------------------------------

    public String toJSON() {
        return new Gson().toJson(this);
    }


    @NonNull
    @Override
    public String toString() {
        return "Date " + getDate() + " TimeZone_type " + getTimezone_type() + " TimeZone " + getTimezone();
    }

    //-------------------------------- Getters & Setters -------------------------------------------

    private Date(Parcel in) {
        date = in.readString();
        timezone_type = in.readString();
        timezone = in.readString();
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone_type() {
        return timezone_type;
    }

    public void setTimezone_type(String timezone_type) {
        this.timezone_type = timezone_type;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(timezone_type);
        dest.writeString(timezone);
    }

}
