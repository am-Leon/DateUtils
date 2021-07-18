package am.dateutils;

public enum DateTimeStyle {

    /**
     * Full Date Time Standard (الاحد, ١٣ مايو ٢٠٢٠ ١٠:٥٣ م)
     */
    DATE_TIME_FULL_STANDARD_AR("EEEE, dd MMMM yyyy hh:mm a"),

    /**
     * Full Date Time Standard (Sunday, June 13, 2020 10:53 PM)
     */
    DATE_TIME_FULL_STANDARD("EEEE, dd MMMM yyyy hh:mm a"),

    /**
     * Full Standard (الاحد, ١٣ مايو ٢٠٢٠)
     */
    DATE_FULL_STANDARD_AR("EEEE, dd MMMM yyyy"),

    /**
     * Full Standard (Sunday, June 13, 2020)
     */
    DATE_FULL_STANDARD("EEEE, MMMM dd yyyy"),

    /**
     * Full Separator (الاحد ٢٠٢٠/٠٦/١٣)
     */
    DATE_FULL_SEPARATOR_AR("EEEE dd/MM/yyyy"),

    /**
     * Full Separator (Sunday 06/13/2020)
     */
    DATE_FULL_SEPARATOR("EEEE MM/dd/yyyy"),

    /**
     * Long Date Time Standard (١٣ مايو ٢٠٢٠ ١٠:٥٣ م)
     */
    DATE_TIME_LONG_STANDARD_AR("dd MMM yyyy hh:mm a"),

    /**
     * Long Date Time Standard (June 13 2020 10:53 PM)
     */
    DATE_TIME_LONG_STANDARD("MMM dd yyyy hh:mm a"),

    /**
     * Long Standard (١٣ مايو ٢٠٢٠)
     */
    DATE_LONG_STANDARD_AR("dd MMM yyyy"),

    /**
     * Long Standard (June 13 2020)
     */
    DATE_LONG_STANDARD("MMM dd yyyy"),

    /**
     * Medium Standard (٢٠٢٠/٠٦/١٣)
     */
    DATE_MEDIUM_STANDARD_AR("dd/MM/yyyy"),

    /**
     * Medium Standard (06/13/2020)
     */
    DATE_MEDIUM_STANDARD("MM/dd/yyyy"),

    /**
     * Short Date Time Standard (١٣ مايو ١٠:٥٣ م)
     */
    DATE_TIME_SHORT_STANDARD_AR("dd MMM hh:mm a"),

    /**
     * Short Date Time Standard (June 13 10:53 PM)
     */
    DATE_TIME_SHORT_STANDARD("MMM dd hh:mm a"),

    /**
     * Short Standard (١٣ مايو)
     */
    DATE_SHORT_STANDARD_AR("dd MMM"),

    /**
     * Short Standard (June 13)
     */
    DATE_SHORT_STANDARD("MMM dd"),

    /**
     * Backend Style (yyyy-MM-dd hh:mm:ss z)
     */
    DATE_TIME_ZONE_FULL_BACKEND_FORMAT("yyyy-MM-dd hh:mm:ss z"),

    /**
     * Backend Style (yyyy-MM-dd hh:mm:ss)
     */
    DATE_TIME_FULL_BACKEND_FORMAT("yyyy-MM-dd hh:mm:ss"),

    /**
     * Backend Style (yyyy-MM-dd)
     */
    DATE_BACKEND_FORMAT("yyyy-MM-dd"),

    /**
     * Date Full Month Name (April)
     */
    DATE_MONTH_NAME("MMMM"),

    /**
     * Date Simple Month Name (Apr)
     */
    DATE_MONTH_SIMPLE_NAME("MMM"),

    /**
     * Date Full Day Name (Sunday)
     */
    DATE_DAY_NAME("EEEE"),

    /**
     * Date Simple Day Name (Sun)
     */
    DATE_DAY_SIMPLE_NAME("EEE"),

    /**
     * Time Full (10:50 AM)
     */
    TIME_FULL("hh:mm a");

    //--------------------------------------- Constructor ------------------------------------------

    String patternStyle;

    DateTimeStyle(String patternStyle) {
        this.patternStyle = patternStyle;
    }

}