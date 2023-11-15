package ggnc.guatechancesapi.Models.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ggnc.guatechancesapi.Utils.PasswordEncoder;
import ggnc.guatechancesapi.Utils.TimeNDateFormater;

import java.sql.Date;
import java.util.List;


@lombok.NoArgsConstructor
@lombok.ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String idCode;
    private String forename;
    private String direction;
    private String username;
    private String password;
    private String email;

    public User(String idCode, String forename, String direction, String username, String password, String email,
                String birthdate, String noCUI, int usertype) {
        this.idCode = idCode;
        this.forename = forename;
        this.direction = direction;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = TimeNDateFormater.stringToDate(birthdate);
        this.noCUI = noCUI;
        this.usertype = usertype;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String noCUI;
    private List<Long> telephone;
    private int usertype = -1;
    private int isActive;

    public User(String idCode, String forename, String direction, String username, String password, String email,
                String noCUI, String birthdate, int usertype, int isActive, List<Long> telephone) {
        this.idCode = idCode;
        this.forename = forename;
        this.direction = direction;
        this.username = username;
        this.password = PasswordEncoder.encodePassword(password);
        this.email = email;
        this.noCUI = noCUI;
        this.birthdate = TimeNDateFormater.stringToDate(birthdate);
        this.usertype = usertype;
        this.isActive = isActive;
        this.telephone = telephone;
    }


    public User(String password, String username) {
        this.password = PasswordEncoder.encodePassword(password);
        this.username = username;
    }

    public User(String idCode) {
        this.idCode = idCode;
    }


    @JsonIgnore
    public String getFormatedStringDate() {
        return TimeNDateFormater.dateToString(birthdate);
    }

    public String getIdCode() {
        return this.idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = PasswordEncoder.encodePassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNoCUI() {
        return noCUI;
    }

    public void setNoCUI(String noCUI) {
        this.noCUI = noCUI;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<Long> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<Long> telephone) {
        this.telephone = telephone;
    }
}
