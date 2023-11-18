package ggnc.guatechancesapi.Models.Domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@lombok.NoArgsConstructor
public class JobSeeker extends User {


    private InputStream cv;


    public JobSeeker(String idCode, String forename, String direction, String username, String password, String email,
                     String CUI, String birthdate, int usertype, int isActive, List<Long> phones) {
        super(idCode, forename, direction, username, password, email, CUI, birthdate, usertype, isActive, phones);
    }


    public JobSeeker(User user){
        super(user.getIdCode(), user.getForename(), user.getDirection(), user.getUsername(), user.getPassword(), user.getEmail(), user.getNoCUI(), user.getFormatedStringDate(), 2, user.getIsActive(), user.getTelephone());
    }

    public JobSeeker(String idCode) {
        super(idCode);
    }

    public void setUserOnSeeker(User user){
        this.setIdCode(user.getIdCode());
        this.setForename(user.getForename());
        this.setDirection(user.getDirection());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setNoCUI(user.getNoCUI());
        this.setBirthdate(user.getBirthdate());
        this.setUsertype(2);
        this.setIsActive(user.getIsActive());
        this.setTelephone(user.getTelephone());
    }

    public InputStream getCv() {
        return cv;
    }

    public void setCv(InputStream cv) {
        this.cv = cv;
    }


}
