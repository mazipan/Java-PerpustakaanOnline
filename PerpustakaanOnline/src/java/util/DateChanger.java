/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author jitzu
 */
public class DateChanger {

    public DateChanger() {

    }

    // 13/07/2014 14:46:58 to yyyy-MM-dd HH:mm:ss
    public static String changeDateToMin(String before) {

        String[] arr = before.split("/");
        String date = arr[0];
        String month = arr[1];
        String yearAndHour = arr[2];

        String year = yearAndHour.substring(0, 4);
        String hour = yearAndHour.substring(5);

        StringBuilder build = new StringBuilder();
        build.append(year);
        build.append("-");
        build.append(month);
        build.append("-");
        build.append(date);
        build.append(" ");
        build.append(hour);
        return build.toString();
    }

}
