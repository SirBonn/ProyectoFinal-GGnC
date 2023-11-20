package ggnc.guatechancesapi.Models.Domain;

import java.util.List;

@lombok.NoArgsConstructor
public class Employer extends User{

    private Card card;
    private String vision;
    private String mision;

    private double plataformPayments;

    public Employer(String idCode, String forename, String direction, String username, String password, String email,
                    String CUI, String birthdate, int usertype, Card card, String vision, String mision, int isActive, List<Long> phones) {
        super(idCode, forename, direction, username, password, email, CUI, birthdate, 1, isActive, phones);
        this.card = card;
        this.vision = vision;
        this.mision = mision;
    }

    public Employer(User user, Card card, String vision, String mision){
        super(user.getIdCode(), user.getForename(), user.getDirection(), user.getUsername(), user.getPassword(), user.getEmail(), user.getNoCUI(), user.getFormatedStringDate(), 1, user.getIsActive(), user.getTelephone());
        this.card = card;
        this.vision = vision;
        this.mision = mision;
    }

    public Employer(User user, Card card, String vision, String mision, List<Long> phones){
        super(user.getIdCode(), user.getForename(), user.getDirection(), user.getUsername(), user.getPassword(), user.getEmail(), user.getNoCUI(), user.getFormatedStringDate(), 1, user.getIsActive(), user.getTelephone());
        this.card = card;
        this.vision = vision;
        this.mision = mision;
        this.setTelephone(phones);
    }

    public Employer(User user){
        super(user.getIdCode(), user.getForename(), user.getDirection(), user.getUsername(), user.getPassword(), user.getEmail(), user.getNoCUI(), user.getFormatedStringDate(), 1, user.getIsActive(), user.getTelephone());
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
        this.setUsertype(1);
        this.setIsActive(user.getIsActive());
        this.setTelephone(user.getTelephone());
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public double getPlataformPayments() {
        return plataformPayments;
    }

    public void setPlataformPayments(double plataformPayments) {
        this.plataformPayments = plataformPayments;
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
