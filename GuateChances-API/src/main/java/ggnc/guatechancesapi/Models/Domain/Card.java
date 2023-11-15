package ggnc.guatechancesapi.Models.Domain;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
public class Card {

    private int number;
    private String titular;
    private int csv;

    public Card(int number, String titular, int csv) {
        this.number = number;
        this.titular = titular;
        this.csv = csv;
    }

    public Card(int number) {
        this.number = number;
    }

}
