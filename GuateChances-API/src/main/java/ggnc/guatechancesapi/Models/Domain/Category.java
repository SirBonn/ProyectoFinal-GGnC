package ggnc.guatechancesapi.Models.Domain;

@lombok.NoArgsConstructor
public class Category {

    private int idCode;
    private String categoryName;
    private String categoryDesc;
    private int isActive;

    public Category(int idCode, String categoryName, String categoryDesc, int isActive) {
        this.idCode = idCode;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.isActive = isActive;
    }

    public Category(int idCode, String categoryName, String categoryDesc) {
        this.idCode = idCode;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    public Category(int idCode) {
        this.idCode = idCode;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsActive() {
        return isActive;
    }
}
