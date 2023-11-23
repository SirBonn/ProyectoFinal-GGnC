package ggnc.guatechancesapi.Models.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ggnc.guatechancesapi.Utils.TimeNDateFormater;

import java.sql.Date;
import java.time.LocalDate;

@lombok.NoArgsConstructor
@lombok.ToString
public class Offer {

    private int idCode;
    private String offerName;
    private String offerDesc;
    private Employer employer;
    private Category category;
    private int offerState;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date publicationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expireDate;
    private double payment;
    private int modality;
    private String direction;
    private String details;
    private JobSeeker seekerSelected;

    public Offer(int idCode, String offerName, String offerDesc, Employer employer, Category category, int offerState,
                 String publicationDate, String expireDate, double payment, int modality, String direction, String details) {
        this.idCode = idCode;
        this.offerName = offerName;
        this.offerDesc = offerDesc;
        this.employer = employer;
        this.category = category;
        this.offerState = offerState;
        this.publicationDate = TimeNDateFormater.stringToDate(publicationDate);
        this.expireDate = TimeNDateFormater.stringToDate(expireDate);
        this.payment = payment;
        this.modality = modality;
        this.direction = direction;
        this.details = details;
    }

    public Offer(int idCode, String offerName, String offerDesc, Employer employer, Category category, int offerState,
                 String publicationDate, String expireDate, double payment,  int modality, String direction, String details, JobSeeker seekerSelected) {
        this.idCode = idCode;
        this.offerName = offerName;
        this.offerDesc = offerDesc;
        this.employer = employer;
        this.category = category;
        this.offerState = offerState;
        this.publicationDate = TimeNDateFormater.stringToDate(publicationDate);
        this.expireDate = TimeNDateFormater.stringToDate(expireDate);
        this.payment = payment;
        this.modality = modality;
        this.direction = direction;
        this.details = details;
        this.seekerSelected = seekerSelected;
    }

    public Offer(String offerName, String offerDesc, Employer employer, Category category, Date expireDate, double payment,
                 int modality, String direction, String details) {
        this.offerName = offerName;
        this.offerDesc = offerDesc;
        this.employer = employer;
        this.category = category;
        this.publicationDate = setNowDate();
        this.expireDate = expireDate;
        this.payment = payment;
        this.modality = modality;
        this.direction = direction;
        this.details = details;
    }

    @JsonIgnore
    public String getStringPublicationDate() {
        return TimeNDateFormater.dateToString(publicationDate);
    }

    @JsonIgnore
    public String getStringExpireDate() {
        return TimeNDateFormater.dateToString(expireDate);
    }

    public Offer(int idCode) {
        this.idCode = idCode;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getOfferState() {
        return offerState;
    }

    public void setOfferState(int offerState) {
        this.offerState = offerState;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = TimeNDateFormater.stringToDate(publicationDate);
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = TimeNDateFormater.stringToDate(expireDate);
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getModality() {
        return modality;
    }

    public void setModality(int modality) {
        this.modality = modality;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public JobSeeker getSeekerSelected() {
        if(seekerSelected == null) {
            return new JobSeeker("-1");
        } else {
            return seekerSelected;
        }
    }

    public void setSeekerSelected(JobSeeker seekerSelected) {
        this.seekerSelected = seekerSelected;
    }
}
