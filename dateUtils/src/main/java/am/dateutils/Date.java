package am.dateutils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Date implements Parcelable {

    private String date;
    private String timezone_type;
    private String timezone;

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

    @NonNull
    @Override
    public String toString() {
        return "Date " + getDate() + " TimeZone_type " + getTimezone_type() + " TimeZone " + getTimezone();
    }
}
