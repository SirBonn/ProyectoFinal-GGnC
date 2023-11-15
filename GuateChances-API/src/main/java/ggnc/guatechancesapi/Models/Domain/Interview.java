package ggnc.guatechancesapi.Models.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ggnc.guatechancesapi.Utils.TimeNDateFormater;

import java.sql.Date;
import java.sql.Time;

public class Interview {

    private int idCode;

    private Application application;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date interviewDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Time interviewTime;
    private int interviewState = -1;
    private String direction;
    private String notes;

    public Interview(int idCode, Application application, String interviewDate, String interviewTime, int interviewState, String direction, String notes) {
        this.idCode = idCode;
        this.application = application;
        this.interviewDate = TimeNDateFormater.stringToDate(interviewDate);
        this.interviewTime = TimeNDateFormater.stringToTime(interviewTime);
        this.interviewState = interviewState;
        this.direction = direction;
        this.notes = notes;
    }

    public Interview(int idCode) {
        this.idCode = idCode;
    }

    public Interview() {
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Time getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Time interviewTime) {
        this.interviewTime = interviewTime;
    }

    public int getInterviewState() {
        return interviewState;
    }

    public void setInterviewState(int interviewState) {
        this.interviewState = interviewState;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonIgnore
    public String getStringInterviewDate() {
        return TimeNDateFormater.dateToString(interviewDate);
    }

    @JsonIgnore
    public String getStringInterviewTime() { return TimeNDateFormater.timeToString(interviewTime); }

}
