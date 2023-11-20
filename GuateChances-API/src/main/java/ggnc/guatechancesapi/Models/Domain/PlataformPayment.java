package ggnc.guatechancesapi.Models.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ggnc.guatechancesapi.Utils.TimeNDateFormater;

import java.sql.Date;
import java.time.LocalDate;

public class PlataformPayment {

    private int idCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private User user;
    private double payment;

    public PlataformPayment() {
    }

    public PlataformPayment(User user, double payment) {
        this.date = setNowDate();
        this.user = user;
        this.payment = payment;
    }

    public PlataformPayment(int idCode, String date, User user, double payment) {
        this.idCode = idCode;
        this.date = TimeNDateFormater.stringToDate(date);
        this.user = user;
        this.payment = payment;
    }

    public Date setNowDate() {
        Date fechaDate = null;
        try {

            LocalDate fechaLD = LocalDate.now();
            fechaDate = Date.valueOf(fechaLD);

        } catch (Exception e) {
            System.out.println("no se pudo parsear la fecha por " + e);
            e.printStackTrace(System.out);
        }
        return fechaDate;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @JsonIgnore
    public String getStringDate() {
        return TimeNDateFormater.dateToString(date);
    }


}
