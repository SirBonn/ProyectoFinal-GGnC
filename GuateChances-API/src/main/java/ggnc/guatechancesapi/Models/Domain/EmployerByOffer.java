package ggnc.guatechancesapi.Models.Domain;

public class EmployerByOffer {

    private Employer employer;
    private int totalOffers;

    public EmployerByOffer() {
    }

    public EmployerByOffer(Employer employer, int totalOffers) {
        this.employer = employer;
        this.totalOffers = totalOffers;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public int getTotalOffers() {
        return totalOffers;
    }

    public void setTotalOffers(int totalOffers) {
        this.totalOffers = totalOffers;
    }

}
