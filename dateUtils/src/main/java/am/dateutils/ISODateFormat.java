package am.dateutils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class ISODateFormat extends DateFormat {

    private final ISOUtils utils;

    public ISODateFormat() {
        utils = new ISOUtils();
    }


    @NonNull
    @Override
    public StringBuffer format(@NonNull Date date, @NonNull StringBuffer toAppendTo, @NonNull FieldPosition fieldPosition) {
        String value = utils.format(date);
        toAppendTo.append(value);
        return toAppendTo;
    }


    @Nullable
    @Override
    public Date parse(@NonNull String source, @NonNull ParsePosition pos) {
        try {
            return utils.parse(source, pos);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * supply our own parse(String) since pos isn't updated during parsing,
     * but the exception should have the right error offset.
     */
    @Nullable
    @Override
    public Date parse(@NonNull String source) throws ParseException {
        return utils.parse(source, new ParsePosition(0));
    }

    @Override
    public void setTimeZone(@NonNull TimeZone zone) {
        utils.setTimeZone(zone);
    }

    @NonNull
    @Override
    public Object clone() {
        return this;
    }


    @NonNull
    @Override
    public String toString() {
        return getClass().getName();
    }

}