package am.dateutils;

public class Utils {

    public static String getNumberFormat(int txtValue) {
        String val = String.valueOf(txtValue);
        char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < val.length(); i++) {
            if (Character.isDigit(val.charAt(i)))
                builder.append(arabicChars[(int) (val.charAt(i)) - 48]);
            else
                builder.append(val.charAt(i));
        }
        return builder.toString();
    }

}