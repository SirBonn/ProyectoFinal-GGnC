package ggnc.guatechancesapi.Models.Domain;

import java.util.List;

@lombok.ToString
public class PhoneBook {

    private List<Long> telephones;
    private String userCode;

    public PhoneBook() {
    }

    public PhoneBook(List<Long> telephones, String userCode) {
        this.telephones = telephones;
        this.userCode = userCode;
    }


    public List<Long> getTelephones() {
        return this.telephones;
    }

    public void setTelephones(List<Long> telephones) {
        this.telephones = telephones;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }


}
