package ggnc.guatechancesapi.Utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TimeNDateFormater {

    public static Date stringToDate(String stringDate) {
        Date parsedDate = null;
        try {

            LocalDate dateToParse = LocalDate.parse(stringDate);
            parsedDate = Date.valueOf(dateToParse);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha" + stringDate + " por " + e);
            e.printStackTrace(System.out);
        }

        return parsedDate;
    }

    public static String dateToString(Date date) {

        if (date != null) {

            LocalDate localDate = date.toLocalDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return localDate.format(formatter);

        } else {
            return "";
        }
    }

    public static Time stringToTime(String timeStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        java.util.Date date = null;

        try {
            date = sdf.parse(timeStr);
        } catch (ParseException ex) {
            System.out.println("ocurrio un error convirtiendo la hora: \n" + ex.getMessage());
        }
        return new Time(date.getTime());
    }

    public static String timeToString(Time transferTime) {

        if (transferTime != null) {

            LocalTime localTime = transferTime.toLocalTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formatedTime = localTime.format(formatter);

            return formatedTime;
        } else {
            return "";
        }
    }

}
