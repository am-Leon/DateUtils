package am.dateutils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private Context context;
    private Locale appLocale;
    private Calendar calendar;
    private String txtDate, txtTimeZone;
    private DateFormat formatBackend, defaultDateFormat;


    //------------------------- Class Constructor ------------------------------

    public DateUtils(Context context) {
        this.context = context;
        init(null);
    }


    public DateUtils(Context context, String txtDate) {
        this(context, txtDate, null, null);
    }


    public DateUtils(Context context, String txtDate, String appLocale) {
        this(context, txtDate, null, appLocale);
    }


    public DateUtils(Context context, am.dateutils.Date date) {
        this(context, date.getDate(), date.getTimezone(), null);
    }


    public DateUtils(Context context, am.dateutils.Date date, String appLocale) {
        this(context, date.getDate(), date.getTimezone(), appLocale);
    }


    public DateUtils(Context context, String txtDate, String txtTimeZone, String appLocale) {
        this.context = context;
        this.txtDate = txtDate;
        this.txtTimeZone = txtTimeZone;
        init(appLocale);
    }


    private void init(String appLocale) {
        if (appLocale != null)
            this.appLocale = Locale.forLanguageTag(appLocale);
        else
            this.appLocale = Locale.US;

        formatBackend = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", this.appLocale);
        defaultDateFormat = new SimpleDateFormat("MMM dd yyyy", this.appLocale);

        calendar = Calendar.getInstance(this.appLocale);
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

    private Calendar getCalendarFormat(String txtDate) {
        calendar.setTime(getDateFormat(txtDate, null));

        return calendar;
    }


    private Calendar getCalendarFormat(String txtDate, String txtTimeZone) {
        calendar.setTime(getDateFormat(txtDate, txtTimeZone));

        return calendar;
    }


    private Date getDateFormat(String txtDate, String timeZone) {
        if (timeZone != null)
            formatBackend.setTimeZone(TimeZone.getTimeZone(timeZone));

        Date date = new Date();
        try {
            date = formatBackend.parse(txtDate);
        } catch (ParseException e) {
            try {
                date = defaultDateFormat.parse(txtDate);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return date;
    }


    public String getDefaultDateFormat() {
        if (appLocale.toLanguageTag().equals("ar"))
            return new SimpleDateFormat("dd MMM yyyy", appLocale).format(getDateFormat(txtDate, txtTimeZone));
        else
            return new SimpleDateFormat("MMM dd yyyy", appLocale).format(getDateFormat(txtDate, txtTimeZone));
    }


    public String getSimpleDateFormat() {
        if (appLocale.toLanguageTag().equals("ar"))
            return new SimpleDateFormat("dd MMM", appLocale).format(getDateFormat(txtDate, txtTimeZone));
        else
            return new SimpleDateFormat("MMM dd", appLocale).format(getDateFormat(txtDate, txtTimeZone));
    }


    public String getBackEndFormat() {
        return setSpecificFormat("yyyy-MM-dd");
    }


    //----------------------------------- Compare Dates --------------------------------------------

    /**
     * in case of @param lastDate has the same timeZone with device timeZone
     */
    public int compareTwoDates(String lastDate) {
        // if result = -1 then newDate bigger  else older is bigger
        return getCalendarFormat(txtDate).compareTo(getCalendarFormat(lastDate));
    }


    public int compareTwoDates(String lastDate, String lastDateTimeZone) {
        // if result = -1 then newDate bigger  else older is bigger
        return getCalendarFormat(txtDate).compareTo(getCalendarFormat(lastDate, lastDateTimeZone));
    }


    public int compareTwoDates(String firstDate, String firstDateTimeZone, String lastDate, String lastDateTimeZone) {
        // if result = -1 then newDate bigger  else older is bigger
        return getCalendarFormat(firstDate, firstDateTimeZone).compareTo(getCalendarFormat(lastDate, lastDateTimeZone));
    }


    public boolean getComparedDate(String StartDate, String EndDate) {
        boolean result = false;
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        try {
            Date currentDate = formatBackend.parse(formatBackend.format(c.getTime()));
            Date startDate = formatBackend.parse(StartDate);
            Date endDate = formatBackend.parse(EndDate);

            result = currentDate.after(startDate) && currentDate.before(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean getComparedDate(am.dateutils.Date startDate, am.dateutils.Date endDate) {
        boolean result = false;
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        try {
            Date currentDate = formatBackend.parse(formatBackend.format(c.getTime()));
            Date start = getDateFormat(startDate.getDate(), startDate.getTimezone());
            Date end = getDateFormat(endDate.getDate(), endDate.getTimezone());

            result = currentDate.after(start) && currentDate.before(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    //------------------------- Date Functions --------------------------------

    public String setSpecificFormat(String wantedPattern) {
        return new SimpleDateFormat(wantedPattern, appLocale).format(getDateFormat(txtDate, txtTimeZone));
    }


    public int getYear() {
        return getCalendarFormat(txtDate, txtTimeZone).get(Calendar.YEAR);
    }


    public String getMonthName() {
        return new SimpleDateFormat("MMMM", appLocale).format(getDateFormat(txtDate, txtTimeZone));
    }


    public String getMonth() {
        int month = getCalendarFormat(txtDate, txtTimeZone).get(Calendar.MONTH) + 1;

        if (month < 10)
            return "0" + month;
        else
            return String.valueOf(month);
    }


    public String getDayName() {
        return new SimpleDateFormat("EEEE", appLocale).format(getDateFormat(txtDate, txtTimeZone));
    }


    public String getDay() {
        int day = getCalendarFormat(txtDate, txtTimeZone).get(Calendar.DAY_OF_MONTH);

        if (day < 10)
            return "0" + day;
        else
            return String.valueOf(day);
    }


    public String getHourFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        int hour = getCalendarFormat(txtDate, txtTimeZone).get(Calendar.HOUR);
        int minute = getCalendarFormat(txtDate, txtTimeZone).get(Calendar.MINUTE);

        if (hour < 10) {
            if (minute < 10)
                stringBuilder.append("0").append(hour).append(":0").append(minute);
            else
                stringBuilder.append("0").append(hour).append(":").append(minute);
        } else
            stringBuilder.append(hour).append(":").append(minute);

        if (getCalendarFormat(txtDate, txtTimeZone).get(Calendar.AM_PM) == Calendar.AM)
            stringBuilder.append(" ").append(context.getString(R.string.am));
        else
            stringBuilder.append(" ").append(context.getString(R.string.pm));

        return String.valueOf(stringBuilder);
    }


    public String getTimeAgo() {
        return getMinutes(false);
    }


    public String getSimpleTimeAgo() {
        return getMinutes(true);
    }


    private String getMinutes(boolean simpleFormat) {
        int timeInMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis() - getCalendarFormat(txtDate, txtTimeZone).getTimeInMillis());

        if (timeInMinutes < 0 && timeInMinutes > -1440)
            return context.getString(R.string.today);

        else if (timeInMinutes == 0 || timeInMinutes == 1)
            return context.getString(R.string.now);

        else if (timeInMinutes == 2)
            return timeInMinutes + " " + context.getString(R.string.minute_ago);

        else if (timeInMinutes > 2 && timeInMinutes < 11)
            return timeInMinutes + " " + context.getString(R.string.minutes_ago);

        else if (timeInMinutes >= 11 && timeInMinutes < 60)
            return timeInMinutes + " " + context.getString(R.string.minute_ago);

        else if (timeInMinutes >= 60 && timeInMinutes < 180)
            return (timeInMinutes / 60) + " " + context.getString(R.string.hours_ago);

        else if (timeInMinutes >= 180 && timeInMinutes < 660)
            return (timeInMinutes / 60) + " " + context.getString(R.string.hours_ago);

        else if (timeInMinutes >= 660 && timeInMinutes < 1440)
            return (timeInMinutes / 60) + " " + context.getString(R.string.hours_ago);

        else if (timeInMinutes >= 1440 && timeInMinutes < 2880)
            return context.getString(R.string.yesterday);

        else {
            if (simpleFormat)
                return getSimpleDateFormat();
            else
                return getDefaultDateFormat();
        }
    }

}