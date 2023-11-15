package ggnc.guatechancesapi.Models.Domain;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Employer extends User{

    private Card card;
    private String vision;
    private String mision;

    public Employer(String idCode, String forename, String direction, String username, String password, String email,
                    String CUI, String birthdate, int usertype, Card card, String vision, String mision, int isActive, List<Long> phones) {
        super(idCode, forename, direction, username, password, email, CUI, birthdate, usertype, isActive, phones);
        this.card = card;
        this.vision = vision;
        this.mision = mision;
    }

    public Employer(User user){
        super(user.getIdCode(), user.getForename(), user.getDirection(), user.getUsername(), user.getPassword(), user.getEmail(), user.getNoCUI(), user.getFormatedStringDate(), user.getUsertype(), user.getIsActive(), user.getTelephone());
        this.card = card;
        this.vision = vision;
        this.mision = mision;
    }

    public Employer(String idCode){
        super(idCode);
    }

    public void setUserOnEmployer(User user){
        this.setIdCode(user.getIdCode());
        this.setForename(user.getForename());
        this.setDirection(user.getDirection());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setNoCUI(user.getNoCUI());
        this.setBirthdate(user.getBirthdate());
        this.setUsertype(user.getUsertype());
        this.setIsActive(user.getIsActive());
        this.setTelephone(user.getTelephone());
    }

    @Override
    public String toString() {
        return super.toString() + "Employer{" +
                "card=" + card +
                ", vision='" + vision +
                ", mision='" + mision+
                '}';
    }
}
