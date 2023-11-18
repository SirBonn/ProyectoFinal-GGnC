package ggnc.guatechancesapi.Models.Domain;

import jakarta.ejb.Local;

import java.util.ArrayList;
import java.util.List;

@lombok.ToString
public class UserCategories {

    private List<Integer> catgories;
    private String userCode;

    public UserCategories() {
    }

    public UserCategories(List<Integer> catgories, String userCode) {
        this.catgories = catgories;
        this.userCode = userCode;
    }

    public UserCategories(String userCode) {
        this.userCode = userCode;
        this.catgories = new ArrayList<>();
    }

    public void addCategory(int category) {
        this.catgories.add(category);
    }

    public List<Integer> getCatgories() {
        return catgories;
    }

    public void setCatgories(List<Integer> catgories) {
        this.catgories = catgories;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
