package ggnc.guatechancesapi.Models.Domain;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.ToString

public class Card {

    private long number;
    private String titular;
    private int csv;

    public Card(long number, String titular, int csv) {
        this.number = number;
        this.titular = titular;
        this.csv = csv;
    }

    public Card(int number) {
        this.number = number;
    }

}
