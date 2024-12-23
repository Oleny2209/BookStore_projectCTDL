package util;

import java.time.LocalDate;

public class AnalyzeDate {
    public static LocalDate convertDayFomart(String dayString) {
        String[] input = new String[3];
        if (dayString.contains("-")) {
            input = dayString.split("-");
        }
        if (dayString.contains("/")) {
            input = dayString.split("/");
        }
        if (dayString.length() == 8) {
            String[] arr = dayString.split("|");
            input[0] = arr[0]+arr[1];
            input[1] = arr[2]+arr[3];
            input[2] = arr[4]+arr[5]+arr[6]+arr[7];
        }
        return LocalDate.parse(input[2] + "-" + input[1] + "-" + input[0]);
    }
}
