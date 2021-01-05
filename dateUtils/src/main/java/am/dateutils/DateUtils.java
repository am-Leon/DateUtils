package am.dateutils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static am.dateutils.DateTimeStyle.DATE_DAY_NAME;
import static am.dateutils.DateTimeStyle.DATE_MONTH_NAME;

public class DateUtils {

    private Locale appLocale;
    private Calendar calendar;
    private final Context context;
    private String txtDate, txtTimeZone;
    private ISODateFormat dateFormat;


    //-------------------------------- Class Constructor -------------------------------------------

    public DateUtils(Context context) {
        this.context = context;
        init(null, null);
    }


    public DateUtils(Context context, @NonNull String txtDate) {
        this(context, txtDate, null, null);
    }


    public DateUtils(Context context, @NonNull String txtDate, String appLocale) {
        this(context, txtDate, null, appLocale);
    }


    public DateUtils(Context context, am.dateutils.Date date) {
        this(context, date.getDate(), date.getTimezone(), null);
    }


    public DateUtils(Context context, am.dateutils.Date date, String appLocale) {
        this(context, date.getDate(), date.getTimezone(), appLocale);
    }


    public DateUtils(Context context, @NonNull String txtDate, String txtTimeZone, String appLocale) {
        this.context = context;
        this.txtDate = txtDate;
        init(appLocale, txtTimeZone);
    }


    private void init(String appLocale, String txtTimeZone) {
        this.dateFormat = new ISODateFormat();
        calendar = Calendar.getInstance(this.appLocale);
        this.txtTimeZone = txtTimeZone != null ? txtTimeZone : "UTC";
        this.appLocale = appLocale != null ? Locale.forLanguageTag(appLocale) : Locale.US;
    }

    //-------------------------------- Getter & Setter ---------------------------------------------

    private String getTxtDate() {
        return txtDate;
    }


    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }


    public String getTxtTimeZone() {
        return txtTimeZone;
    }


    public void setTxtTimeZone(String txtTimeZone) {
        this.txtTimeZone = txtTimeZone;
    }


    //-------------------------------- Date & Calendar Formats -------------------------------------

    private Calendar getCalendarFormat() {
        calendar.setTime(getDateFormat());
        return calendar;
    }


    private Date getDateFormat() {
        Date date = new Date();
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone(txtTimeZone));
            date = dateFormat.parse(txtDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    //-------------------------------- Pattern Style -----------------------------------------------

    public String setSpecificFormat(DateTimeStyle dateTimeStyle) {
        return new SimpleDateFormat(dateTimeStyle.patternStyle, appLocale).format(getDateFormat());
    }


    public String setSpecificFormat(String wantedPattern) {
        return new SimpleDateFormat(wantedPattern, appLocale).format(getDateFormat());
    }


    public String getDefaultDateFormat() {
        if (appLocale.toLanguageTag().equals("ar"))
            return setSpecificFormat(DateTimeStyle.DATE_LONG_STANDARD_AR);
        else
            return setSpecificFormat(DateTimeStyle.DATE_LONG_STANDARD);
    }


    public String getSimpleDateFormat() {
        if (appLocale.toLanguageTag().equals("ar"))
            return setSpecificFormat(DateTimeStyle.DATE_SHORT_STANDARD_AR);
        else
            return setSpecificFormat(DateTimeStyle.DATE_SHORT_STANDARD);
    }


    public String getBackEndFormat() {
        return setSpecificFormat(DateTimeStyle.DATE_BACKEND_FORMAT);
    }


    //-------------------------------- Static Methods ----------------------------------------------

    public static boolean isDateBeforeDeviceDate(String date) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(date, null);

        return startDate.before(currentDate);
    }


    public static boolean isDateBeforeDeviceDate(String date, String dateTimeZone) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(date, dateTimeZone);

        return startDate.before(currentDate);
    }


    public static boolean isDateBeforeDeviceDate(am.dateutils.Date date) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(date.getDate(), date.getTimezone());

        return startDate.before(currentDate);
    }


    public static boolean isFirstDateBeforeSecondDate(String firstDate, String secondDate) {
        Date startDate = getDateFormat(firstDate, null);
        Date endDate = getDateFormat(secondDate, null);

        return startDate.before(endDate);
    }


    public static boolean isFirstDateBeforeSecondDate(String firstDate, String firstTimeZone, String secondDate, String secondTimeZone) {
        Date startDate = getDateFormat(firstDate, firstTimeZone);
        Date endDate = getDateFormat(secondDate, secondTimeZone);

        return startDate.before(endDate);
    }


    public static boolean isFirstDateBeforeSecondDate(am.dateutils.Date firstDate, am.dateutils.Date secondDate) {
        Date startDate = getDateFormat(firstDate.getDate(), firstDate.getTimezone());
        Date endDate = getDateFormat(secondDate.getDate(), secondDate.getTimezone());

        return startDate.before(endDate);
    }


    public static boolean isCurrentDateTimeBetweenDates(String StartDate, String EndDate) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(StartDate, null);
        Date endDate = getDateFormat(EndDate, null);

        return currentDate.after(startDate) && currentDate.before(endDate);
    }


    public static boolean isCurrentDateTimeBetweenDates(String StartDate, String startTimeZone, String EndDate, String endTimeZone) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(StartDate, startTimeZone);
        Date endDate = getDateFormat(EndDate, endTimeZone);

        return currentDate.after(startDate) && currentDate.before(endDate);
    }


    public static boolean isCurrentDateTimeBetweenDates(am.dateutils.Date StartDate, am.dateutils.Date EndDate) {
        Date currentDate = getDeviceDate();
        Date startDate = getDateFormat(StartDate.getDate(), StartDate.getTimezone());
        Date endDate = getDateFormat(EndDate.getDate(), EndDate.getTimezone());

        return currentDate.after(startDate) && currentDate.before(endDate);
    }


    public static String getDateTimeWithTimeZoneAsString(String date, String time) {
        return date.concat(" ").concat(time).concat(" ").concat(getDeviceTimeZone());
    }


    public static am.dateutils.Date getDateTimeWithTimeZoneAsDate(String date, String time) {
        return new am.dateutils.Date(date.concat(" ").concat(time), getDeviceTimeZone());
    }


    private static Date getDateFormat(String txtDate, String txtTimeZone) {
        Date date = new Date();
        ISODateFormat dateFormat = new ISODateFormat();
        try {
            if (txtTimeZone != null)
                dateFormat.setTimeZone(TimeZone.getTimeZone(txtTimeZone));
            date = dateFormat.parse(txtDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    private static Date getDeviceDate() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        ISODateFormat dateFormat = new ISODateFormat();
        return getDateFormat(dateFormat.format(c.getTime()), null);
    }


    private static String getDeviceTimeZone() {
        return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
    }

    //-------------------------------- Date Functions ----------------------------------------------

    public long toMillis() {
        return getDateFormat().getTime();
    }


    public boolean isDateBeforeDeviceDate() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        ISODateFormat dateFormat = new ISODateFormat();
        Date currentDate = getDateFormat(dateFormat.format(c.getTime()), null);

        return getDateFormat().before(currentDate);
    }


    public boolean isYesterday() {
        // Check if yesterday
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday

        return c1.get(Calendar.YEAR) == getCalendarFormat().get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == getCalendarFormat().get(Calendar.DAY_OF_YEAR);
    }


    public boolean isToday() {
        return android.text.format.DateUtils.isToday(getDateFormat().getTime());
    }


    public int getYear() {
        return getCalendarFormat().get(Calendar.YEAR);
    }


    public String getMonthName() {
        return new SimpleDateFormat(DATE_MONTH_NAME.patternStyle, appLocale).format(getDateFormat());
    }


    public String getMonth() {
        int month = getCalendarFormat().get(Calendar.MONTH) + 1;

        if (month < 10)
            return "0" + month;
        else
            return String.valueOf(month);
    }


    public String getDayName() {
        return new SimpleDateFormat(DATE_DAY_NAME.patternStyle, appLocale).format(getDateFormat());
    }


    public String getDay() {
        int day = getCalendarFormat().get(Calendar.DAY_OF_MONTH);

        if (day < 10)
            return "0" + day;
        else
            return String.valueOf(day);
    }


    public String getHourFormat() {
        if (appLocale.toLanguageTag().equals("ar"))
            return getHourAr();
        else
            return getHourEn();
    }


    public String getTimeAgo() {
        if (appLocale.toLanguageTag().equals("ar"))
            return getArMinutes(false);
        else
            return getMinutes(false);
    }


    public String getSimpleTimeAgo() {
        if (appLocale.toLanguageTag().equals("ar"))
            return getArMinutes(true);
        else
            return getMinutes(true);
    }


    private String getMinutes(boolean simpleFormat) {
        int timeInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis() - getCalendarFormat().getTimeInMillis());

        if (timeInMinutes < 0 && timeInMinutes > -1440)
            return getString(R.string.today) + getHourFormat();

        else if (timeInMinutes == 0 || timeInMinutes == 1)
            return getString(R.string.now);

        else if (timeInMinutes == 2)
            return timeInMinutes + getString(R.string.minutes).concat(getString(R.string.ago));

        else if (timeInMinutes > 2 && timeInMinutes < 11)
            return timeInMinutes + getString(R.string.minutes).concat(getString(R.string.ago));

        else if (timeInMinutes >= 11 && timeInMinutes < 60)
            return timeInMinutes + getString(R.string.minutes).concat(getString(R.string.ago));

        else if (timeInMinutes >= 60 && timeInMinutes < 180)
            return (timeInMinutes / 60) + getString(R.string.hours).concat(getString(R.string.ago));

        else if (timeInMinutes >= 180 && timeInMinutes < 660)
            return (timeInMinutes / 60) + getString(R.string.hours).concat(getString(R.string.ago));

        else if (timeInMinutes >= 660 && timeInMinutes < 1440)
            return (timeInMinutes / 60) + getString(R.string.hours).concat(getString(R.string.ago));

        else if (timeInMinutes >= 1440 && timeInMinutes < 2880)
            return getString(R.string.yesterday);

        else {
            if (simpleFormat)
                return getSimpleDateFormat();
            else
                return getDefaultDateFormat();
        }
    }


    private String getArMinutes(boolean simpleFormat) {
        int timeInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis() - getCalendarFormat().getTimeInMillis());

        if (timeInMinutes < 0 && timeInMinutes > -1440)
            return getHourFormat() + getString(R.string.today);

        else if (timeInMinutes == 0 || timeInMinutes == 1)
            return getString(R.string.now);

        else if (timeInMinutes == 2)
            return getString(R.string.ago) + getString(R.string.two_minutes);

        else if (timeInMinutes > 2 && timeInMinutes < 11)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes) + getString(R.string.minutes);

        else if (timeInMinutes >= 11 && timeInMinutes < 60)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes) + getString(R.string.minute);

        else if (timeInMinutes >= 60 && timeInMinutes < 120)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes / 60) + getString(R.string.hour);

        else if (timeInMinutes >= 120 && timeInMinutes < 180)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes / 60) + getString(R.string.two_hours);

        else if (timeInMinutes >= 180 && timeInMinutes < 660)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes / 60) + getString(R.string.hours);

        else if (timeInMinutes >= 660 && timeInMinutes < 1440)
            return getString(R.string.ago) + Utils.getNumberFormat(timeInMinutes / 60) + getString(R.string.hour);

        else if (timeInMinutes >= 1440 && timeInMinutes < 2880)
            return getString(R.string.yesterday);

        else {
            if (simpleFormat)
                return getSimpleDateFormat();
            else
                return getDefaultDateFormat();
        }
    }


    private String getHourEn() {
        StringBuilder stringBuilder = new StringBuilder();
        int hour = getCalendarFormat().get(Calendar.HOUR);
        int minute = getCalendarFormat().get(Calendar.MINUTE);

        if (hour < 10) {
            if (minute < 10)
                stringBuilder.append("0").append(hour).append(":0").append(minute);
            else
                stringBuilder.append("0").append(hour).append(":").append(minute);
        } else
            stringBuilder.append(hour).append(":").append(minute);

        if (getCalendarFormat().get(Calendar.AM_PM) == Calendar.AM)
            stringBuilder.append(" ").append(getString(R.string.am));
        else
            stringBuilder.append(" ").append(getString(R.string.pm));

        return String.valueOf(stringBuilder.append(" "));
    }


    private String getHourAr() {
        StringBuilder stringBuilder = new StringBuilder();
        int hour = getCalendarFormat().get(Calendar.HOUR);
        int minute = getCalendarFormat().get(Calendar.MINUTE);

        if (hour < 10) {
            if (minute < 10)
                stringBuilder.append(Utils.getNumberFormat(0)).append(Utils.getNumberFormat(minute)).append(":")
                        .append(Utils.getNumberFormat(0)).append(Utils.getNumberFormat(hour));
            else
                stringBuilder.append(Utils.getNumberFormat(minute)).append(":")
                        .append(Utils.getNumberFormat(0)).append(Utils.getNumberFormat(hour));
        } else
            stringBuilder.append(Utils.getNumberFormat(minute)).append(":").append(Utils.getNumberFormat(hour));

        if (getCalendarFormat().get(Calendar.AM_PM) == Calendar.AM)
            stringBuilder.append(" ").append(getString(R.string.am));
        else
            stringBuilder.append(" ").append(getString(R.string.pm));

        return String.valueOf(stringBuilder.append(" "));
    }


    //-------------------------------- Settings ----------------------------------------------------

    private String getString(@StringRes int resID) {
        return context.getString(resID);
    }

}