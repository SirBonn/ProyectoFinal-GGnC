package ggnc.guatechancesapi.Models.Domain;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.InsertApplication;
import ggnc.guatechancesapi.Models.DataBase.CardDAOs.InsertCard;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.InsertCategory;
import ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs.InsertInterview;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.InsertOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertUser;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.PhonebookDAOs.InsertPhone;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.InsertEmployer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.InsertUserCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileReaded {

    private List<User> admins;
    private List<JobSeeker> jobSeekers;
    private List<Employer> employers;
    private List<Offer> offers;
    private List<Card> cards;
    private List<Application> applications;
    private List<Category> categories;
    private List<Interview> interviews;
    private List<String> exceptions;

    private List<PhoneBook> phonebooks;

    private List<UserCategories> userCategories;

    public FileReaded() {
        this.admins = new ArrayList<>();
        this.jobSeekers = new ArrayList<>();
        this.employers = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.applications = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.interviews = new ArrayList<>();
        this.phonebooks = new ArrayList<>();
        this.userCategories = new ArrayList<>();
        this.exceptions = new ArrayList<>();
    }

    public void insertData() {

        for (Category category : categories) {

            if (!new InsertCategory().insertCategory(category)) {
                this.exceptions.add("Error en la categoria: " + category.toString());
            }
        }

        for (User user : admins) {

            if (!new InsertUser().insertUser(user)) {
                this.exceptions.add("Error en el usuario: " + user.toString());
            }


        }
        for (JobSeeker user : jobSeekers) {

            if (!new InsertUser().insertUser(user)) {
                this.exceptions.add("Error en el usuario: " + user.toString());
            }

        }

        for (Card card : cards) {

            if (!new InsertCard().insertEmployerCard(card)) {
                this.exceptions.add("Error en la tarjeta: " + card.toString());
            }

        }

        for (Employer user : employers) {

            if (!new InsertEmployer().insertEmployer(user)) {
                this.exceptions.add("Error en el usuario: " + user.toString());
            }

        }

        for (UserCategories userCategory : userCategories) {

            if (!new InsertUserCategory().insertUserCategory(userCategory)) {
                this.exceptions.add("Error en la categoria: " + userCategory.toString());
            }

        }
        for (PhoneBook phonebook : phonebooks) {

            if (!new InsertPhone().insertPhone(phonebook)) {
                this.exceptions.add("Error en el directorio: " + phonebook.toString());
            }

        }

        for (Offer offer : offers) {
            if (!new InsertOffer().insertOffer(offer)) {
                this.exceptions.add("Error en la oferta: " + offer.toString());
            }

        }
        for (Application application : applications) {

            if (!new InsertApplication().insertApplication(application)) {
                this.exceptions.add("Error en la aplicacion: " + application.toString());
            }

        }
        for (Interview interview : interviews) {

            if (!new InsertInterview().insertInterview(interview)) {
                this.exceptions.add("Error en la entrevista: " + interview.toString());
            }
        }

        //return exceptions;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public void setJobSeekers(List<JobSeeker> jobSeekers) {
        this.jobSeekers = jobSeekers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public void setPhonebooks(List<PhoneBook> phonebooks) {
        this.phonebooks = phonebooks;
    }

    public void setUserCategories(List<UserCategories> userCategories) {
        this.userCategories = userCategories;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    public void addInterview(Interview interview) {
        this.interviews.add(interview);
    }

    public void addException(String exception) {
        this.exceptions.add(exception);
    }

    public void addPhoneBook(PhoneBook phonebook) {
        this.phonebooks.add(phonebook);
    }

    public void addUserCategories(UserCategories userCategories) {
        this.userCategories.add(userCategories);
    }

    public String getExceptions() {
        StringBuilder sb = new StringBuilder();
        for (String exception : exceptions) {
            sb.append("Error en: \n");
            sb.append(exception);
            sb.append("\n");
        }
        return sb.toString();
    }

}
