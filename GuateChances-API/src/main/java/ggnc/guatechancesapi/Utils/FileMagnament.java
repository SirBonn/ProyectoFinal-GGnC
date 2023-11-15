package ggnc.guatechancesapi.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStream;

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.InsertApplication;
import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.InsertCategory;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs.InsertInterview;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.InsertOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertUser;

import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectEmployer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.InsertUserCategory;
import ggnc.guatechancesapi.Models.Domain.*;

public class FileMagnament {
    private ObjectMapper objectMapper = new ObjectMapper();
    private InputStream fileContent;
    private BufferedReader bufferedReader;
    private JsonNode rootNode;
    private String json;

    public FileMagnament(String fileContent, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.json = fileContent;
        initJasonRootNode();
    }

    private void initJasonRootNode() {

        try {

            this.rootNode = objectMapper.readTree(json);
            System.out.println("exito ocn rootNode");
        } catch (IOException ex) {

            Logger.getLogger(FileMagnament.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void loadData() throws ErrorOcurredException {

        try {
            loadCategories();
            loadAdmins();
            loadEmployers();
            loadSeekers();
            loadOffers();

        } catch (Exception e) {
            throw new ErrorOcurredException("Error al cargar datos: " + e.getMessage());
        }

    }

    private void loadCategories() throws ErrorOcurredException{

        ArrayList<Category> categories = new ArrayList<>();
        JsonNode categoriesNode = rootNode.path("categorias");
        InsertCategory insertCategory = new InsertCategory();
        for (JsonNode categoryNode : categoriesNode) {
            int idCode = categoryNode.get("codigo").asInt();
            String name = categoryNode.get("nombre").asText();
            String description = categoryNode.get("descripcion").asText();
            Category category = new Category(idCode, name, description);
            categories.add(category);
        }

        for (Category category : categories) {
           insertCategory.insertCategory(category);
        }

    }


    private void loadAdmins() throws ErrorOcurredException {
        InsertUser insertUser = new InsertUser();
        ArrayList<User> admins = new ArrayList<>();
        JsonNode adminsNode = rootNode.path("admin");

        for (JsonNode adminNode : adminsNode) {
            String idCode = adminNode.get("codigo").asText();
            String forename = adminNode.get("nombre").asText();
            String direction = adminNode.get("direccion").asText();
            String username = adminNode.get("username").asText();
            String password = adminNode.get("password").asText();
            String email = adminNode.get("email").asText();
            String noCUI = adminNode.get("CUI").asText();
            String birthDate = adminNode.get("fechaNacimiento").asText();

            User user = new User(idCode, forename, direction, username, password, email, birthDate, noCUI, 0);
            admins.add(user);
        }

        for (User admin : admins) {
            insertUser.insertUser(admin);
        }

    }

    private void loadEmployers() throws ErrorOcurredException {
        InsertUser insertUser = new InsertUser();
        ArrayList<Employer> employers = new ArrayList<>();
        JsonNode employersNode = rootNode.path("empleadores");

        for (JsonNode employerNode : employersNode) {
            String idCode = employerNode.get("codigo").asText();
            String forename = employerNode.get("nombre").asText();
            String direction = employerNode.get("direccion").asText();
            String username = employerNode.get("username").asText();
            String password = employerNode.get("password").asText();
            String email = employerNode.get("email").asText();
            String noCUI = employerNode.get("CUI").asText();
            String birthDate = employerNode.get("fechaFundacion").asText();
            JsonNode telephoneNode = employerNode.get("telefonos");
            List<Long> telephones = new ArrayList<>();
            for (JsonNode telephone : telephoneNode) {
                long number = telephone.asLong();
                telephones.add(number);
            }
            User user = new User(idCode, forename, direction, username, password, email, birthDate, noCUI, 1);
            user.setTelephone(telephones);
            Employer employer = new Employer(user);

            String vision = employerNode.get("vision").asText();
            String mision = employerNode.get("mision").asText();

            JsonNode cardNode = employerNode.get("tarjeta");
            int cardNumber = cardNode.get("numero").asInt();
            String cardName = cardNode.get("Titular").asText();
            int cardCVV = cardNode.get("codigoSeguridad").asInt();

            Card card = new Card(cardNumber, cardName, cardCVV);
            employer.setCard(card);

            employers.add(employer);
        }

        for (User employer : employers) {
            System.out.println("cargando: " + employer.toString());
            insertUser.insertUser(employer);
        }

    }

    private void loadSeekers() throws ErrorOcurredException {

        ArrayList<JobSeeker> seekers = new ArrayList<>();
        JsonNode seekersNode = rootNode.path("usuarios");

        for (JsonNode seekerNode : seekersNode) {
            String idCode = seekerNode.get("codigo").asText();
            String forename = seekerNode.get("nombre").asText();
            String direction = seekerNode.get("direccion").asText();
            String username = seekerNode.get("username").asText();
            String password = seekerNode.get("password").asText();
            String email = seekerNode.get("email").asText();
            String noCUI = seekerNode.get("CUI").asText();
            String birthDate = seekerNode.get("fechaNacimiento").asText();
            JsonNode telephoneNode = seekerNode.get("telefonos");
            List<Long> telephones = new ArrayList<>();
            for (JsonNode telephone : telephoneNode) {
                long number = telephone.asLong();
                telephones.add(number);
            }
            User user = new User(idCode, forename, direction, username, password, email, birthDate, noCUI, 2);
            user.setTelephone(telephones);
            JobSeeker seeker = new JobSeeker(user);
            System.out.println("insertando: " + seeker.toString());

            new InsertUser().insertUser(seeker);

            JsonNode categoryNode = seekerNode.get("categorias");
            List<Integer> categories = new ArrayList<>();
            for (JsonNode category : categoryNode) {
                int number = category.asInt();
                categories.add(number);
            }

            for (int category : categories) {
                System.out.println("insertando: " + seeker.toString() + " y " + category);
                new InsertUserCategory().insertUserCategory(seeker, new Category(category));
            }

            //seekers.add(seeker);
        }

        for (User seeker : seekers) {

        }
    }

    private void loadOffers() throws ErrorOcurredException {
        InsertOffer insertOffer = new InsertOffer();
        ArrayList<Offer> offers = new ArrayList<>();
        JsonNode offersNode = rootNode.get("ofertas");
        int intState = 0;
        int intModality = 1;
        for (JsonNode offerNode : offersNode) {
            int idCode = offerNode.get("codigo").asInt();
            String nombre = offerNode.get("nombre").asText();
            String offerDesc = offerNode.get("descripcion").asText();
            String employerCode = offerNode.get("empresa").asText();
            int category = offerNode.get("categoria").asInt();
            String state = offerNode.get("estado").asText();
            if (state.equals("ACTIVA")){
                intState= 0;
            } else if (state.equals("FINALIZADA")){
                intState = 1;
            }
            String publicationDate = offerNode.get("fechaPublicacion").asText();
            String expirationDate = offerNode.get("fechaLimite").asText();
            double salary = offerNode.get("salario").asDouble();
            String modality = offerNode.get("modalidad").asText();
            if (modality.equals("PRESENCIAL")){
                intState= 1;
            } else if (modality.equals("REMOTO")){
                intState = 2;
            } else if (modality.equals("HIBRIDO")){
                intState = 3;
            }
            String direction = offerNode.get("ubicacion").asText();
            String details = offerNode.get("detalles").asText();

            Offer offer = new Offer(idCode, nombre, offerDesc, new SelectEmployer().getEmployer(new Employer(employerCode)),
                    new SelectCategory().getCategory(new Category(category)), intState, publicationDate,
                    expirationDate, salary, 0, intModality, direction, details);

            JsonNode applicationsNode = offerNode.get("solicitudes");
            List<Application> applications = new ArrayList<>();
            InsertApplication insertApplication = new InsertApplication();
            for (JsonNode applicationNode : applicationsNode) {
                int applicationCode = applicationNode.get("codigo").asInt();
                String seekerCode = applicationNode.get("usuario").asText();
                String message = applicationNode.get("mensaje").asText();
                Application application = new Application(applicationCode, new JobSeeker(seekerCode), offer, message, 0);
                applications.add(application);
            }

            for (Application application : applications) {
                System.out.println("insertando: " + application.toString());
                insertApplication.insertApplication(application);
            }

            JsonNode interviewNode = offerNode.get("entrevistas");
            List<Interview> interviews = new ArrayList<>();
            InsertInterview insertInw = new InsertInterview();
            for (JsonNode iNode : interviewNode) {
                int interviewCode = iNode.get("codigo").asInt();
                String seekerCode = iNode.get("usuario").asText();
                String date = iNode.get("fecha").asText();
                String hour = iNode.get("hora").asText();
                String direction2 = iNode.get("ubicacion").asText();
                String state2 = iNode.get("estado").asText();
                if (state.equals("PENDIENTE")){
                    intState= 0;
                } else if (state.equals("FINALIZADA")){
                    intState = 1;
                }
                String notes = iNode.get("notas").asText();
                int applicationCode = new SelectApplication().identifyApplication(new User(seekerCode), offer);
                Interview interview = new Interview(interviewCode, new Application(applicationCode), date, hour, intState, direction2, notes);
                interviews.add(interview);
            }

            for (Interview interview : interviews) {
                System.out.println("insertando: " + interview.toString());
                insertInw.insertInterview(interview);
            }

            offers.add(offer);
        }

        for (Offer offer : offers) {
            System.out.println("insertando: " + offer.toString());
            insertOffer.insertOffer(offer);
        }
    }

}
