package ggnc.guatechancesapi.Models.Domain;


@lombok.NoArgsConstructor
public class Application {

    private int idCode;
    private JobSeeker seeker;
    private Offer offer;
    private String seekerMessage;
    private int state;

    public Application(int idCode) {
        this.idCode = idCode;
    }

    public Application(int idCode, JobSeeker seeker, Offer offer, String seekerMessage, int state) {
        this.idCode = idCode;
        this.seeker = seeker;
        this.offer = offer;
        this.seekerMessage = seekerMessage;
        this.state = state;
    }


    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public JobSeeker getSeeker() {
        return seeker;
    }

    public void setSeeker(JobSeeker seeker) {
        this.seeker = seeker;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getSeekerMessage() {
        return seekerMessage;
    }

    public void setSeekerMessage(String seekerMessage) {
        this.seekerMessage = seekerMessage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
