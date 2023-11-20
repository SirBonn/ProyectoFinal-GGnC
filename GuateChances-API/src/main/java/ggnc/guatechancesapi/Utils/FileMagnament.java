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

import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;

import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectEmployer;
import ggnc.guatechancesapi.Models.Domain.*;

public class FileMagnament {
    private ObjectMapper objectMapper = new ObjectMapper();
    private InputStream fileContent;
    private BufferedReader bufferedReader;
    private JsonNode rootNode;
    private String json;
    private FileReaded fileReaded;

    public FileMagnament(String fileContent, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.json = fileContent;
        fileReaded = new FileReaded();
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

    public void loadData() {


        fileReaded.setCategories(loadCategories()); //            loadCategories();
        fileReaded.setAdmins(loadAdmins());//            loadAdmins();
        fileReaded.setEmployers(loadEmployers());//            loadEmployers();
        fileReaded.setJobSeekers(loadSeekers());//            loadSeekers();
        fileReaded.setOffers(loadOffers());//            loadOffers();
        fileReaded.insertData();

    }


    private List<Category> loadCategories() {

        ArrayList<Category> categories = new ArrayList<>();
        JsonNode categoriesNode = rootNode.path("categorias");

        for (JsonNode categoryNode : categoriesNode) {
            try {
                int idCode = categoryNode.get("codigo").asInt();
                String name = categoryNode.get("nombre").asText();
                String description = categoryNode.get("descripcion").asText();
                Category category = new Category(idCode, name, description);
                categories.add(category);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo: " + e.getMessage());
            }

        }
        return categories;
    }


    private List<User> loadAdmins() {

        ArrayList<User> admins = new ArrayList<>();
        JsonNode adminsNode = rootNode.path("admin");
        for (JsonNode adminNode : adminsNode) {
            try {
                User user = userConstructor(adminNode);
                System.out.println("CREADO admin: " + user.toString());
                admins.add(user);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadAdmins: " + e.getMessage());
            }
        }
        return admins;
    }

    private List<Employer> loadEmployers() {
        ArrayList<Employer> employers = new ArrayList<>();
        JsonNode employersNode = rootNode.path("empleadores");
        for (JsonNode employerNode : employersNode) {
            try {
                User user = userConstructor(employerNode);
                String vision = employerNode.get("vision").asText();
                String mision = employerNode.get("mision").asText();
                PhoneBook phoneBook = loadTelephones(employerNode, user.getIdCode());
                Card card = loadCards(employerNode);
                Employer employer = new Employer(user, card, vision, mision, phoneBook.getTelephones());
                System.out.println("CREADO EMP: " + employer.toString());
                employers.add(employer);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadEmployers: " + e.getMessage());
            }
        }
        return employers;
    }

    private Card loadCards(JsonNode employerNode) {
        try {
            JsonNode cardNode = employerNode.get("tarjeta");
            Long cardNumber = cardNode.get("numero").asLong();
            String cardName = cardNode.get("Titular").asText();
            int cardCVV = cardNode.get("codigoSeguridad").asInt();
            Card card = new Card(cardNumber, cardName, cardCVV);
            this.fileReaded.addCard(card);
            return new Card(cardNumber, cardName, cardCVV);

        } catch (Exception e) {
            fileReaded.addException("Error durante en lectura de archivo loadCards: " + e.getMessage());
            return null;
        }

    }

    private PhoneBook loadTelephones(JsonNode employerNode, String userCode) {
        try {
            JsonNode telephoneNode = employerNode.get("telefonos");
            List<Long> telephones = new ArrayList<>();
            for (JsonNode telephone : telephoneNode) {
                long number = telephone.asLong();
                telephones.add(number);
            }
            PhoneBook phoneBook = new PhoneBook(telephones, userCode);
            this.fileReaded.addPhoneBook(phoneBook);
            return phoneBook;
        } catch (Exception e) {
            fileReaded.addException("Error durante en lectura de archivo: " + e.getMessage());
            return null;
        }

    }

    private List<JobSeeker> loadSeekers() {
        ArrayList<JobSeeker> seekers = new ArrayList<>();
        JsonNode seekersNode = rootNode.path("usuarios");
        for (JsonNode seekerNode : seekersNode) {
            try {
                User user = userConstructor(seekerNode);
                PhoneBook phoneBook = loadTelephones(seekerNode, user.getIdCode());
                JobSeeker seeker = new JobSeeker(user);
                System.out.println("CREADO SEEKER: " + seeker.toString());
                JsonNode categoryNode = seekerNode.get("categorias");
                loadUserCategories(seekerNode, user.getIdCode());
                seekers.add(seeker);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadSeekers: " + e.getMessage());
            }
        }
        return seekers;
    }

    private UserCategories loadUserCategories(JsonNode seekerNode, String userCode) {
        JsonNode categoryNode = seekerNode.get("categorias");
        List<Integer> categories = new ArrayList<>();
        try {
            for (JsonNode category : categoryNode) {
                int number = category.asInt();
                categories.add(number);
            }
            UserCategories userCategories = new UserCategories(categories, userCode);
            this.fileReaded.addUserCategories(userCategories);
            return userCategories;
        } catch (Exception e) {
            fileReaded.addException("Error durante en lectura de archivo laodUserCategories: " + e.getMessage());
            return null;
        }
    }

    private List<Offer> loadOffers() {
        ArrayList<Offer> offers = new ArrayList<>();
        JsonNode offersNode = rootNode.get("ofertas");
        int intState = 0;
        int intModality = 1;
        for (JsonNode offerNode : offersNode) {
            try {
                int idCode = offerNode.get("codigo").asInt();
                String nombre = offerNode.get("nombre").asText();
                String offerDesc = offerNode.get("descripcion").asText();
                Employer employer = new Employer(offerNode.get("empresa").asText()); //offerNode.get("empresa").asText() = userCode
                Category category = new Category(offerNode.get("categoria").asInt()); //offerNode.get("categoria").asInt() = categoryCode
                String state = offerNode.get("estado").asText();
                intState = getState(state, "ACTIVA", "ENTREVISTA", "FINALIZADA");
                String publicationDate = offerNode.get("fechaPublicacion").asText();
                String expirationDate = offerNode.get("fechaLimite").asText();
                double salary = offerNode.get("salario").asDouble();
                String modality = offerNode.get("modalidad").asText();
                intModality = getState(modality, "PRESENCIAL", "REMOTO", "HIBRIDO");
                String direction = offerNode.get("ubicacion").asText();
                String details = offerNode.get("detalles").asText();
                JobSeeker seekerSelected = new JobSeeker("-1");
                if (!offerNode.get("usuarioElegido").isNull()) {
                    seekerSelected = new JobSeeker(offerNode.get("usuarioElegido").asText());
                }

                Offer offer = new Offer(idCode, nombre, offerDesc, employer, category, intState, publicationDate, expirationDate, salary, intModality, direction, details, seekerSelected);
                System.out.println("CREADO offer: " + offer.toString());
                loadAplication(offerNode, offer);
                loadInterviews(offerNode, offer);
                offers.add(offer);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadOffers: " + e.getMessage());

            }
        }
        return offers;
    }

    private void loadAplication(JsonNode offerNode, Offer offer) {
        JsonNode applicationsNode = offerNode.get("solicitudes");
        List<Application> applications = new ArrayList<>();
        for (JsonNode applicationNode : applicationsNode) {
            try {
                int applicationCode = applicationNode.get("codigo").asInt();
                String seekerCode = applicationNode.get("usuario").asText();
                String message = applicationNode.get("mensaje").asText();
                Application application = new Application(applicationCode, new JobSeeker(seekerCode), offer, message, 0);
                applications.add(application);
                fileReaded.addApplication(application);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadAplication: " + e.getMessage());
            }
        }
    }

    private void loadInterviews(JsonNode offerNode, Offer offer) {
        JsonNode intterviewsNode = offerNode.get("entrevistas");
        List<Interview> interviews = new ArrayList<>();
        for (JsonNode interviewNode : intterviewsNode) {
            try {
                int interviewCode = interviewNode.get("codigo").asInt();
                String seekerCode = interviewNode.get("usuario").asText();
                String date = interviewNode.get("fecha").asText();
                String hour = interviewNode.get("hora").asText();
                String direction2 = interviewNode.get("ubicacion").asText();
                String interviewState = interviewNode.get("estado").asText();
                int intState = getState(interviewState, "PENDIENTE", "FINALIZADA");
                String notes = interviewNode.get("notas").asText();
                Application application = new Application(new JobSeeker(seekerCode), offer);
                Interview interview = new Interview(interviewCode, application, date, hour, intState, direction2, notes);
                interviews.add(interview);
                fileReaded.addInterview(interview);
            } catch (Exception e) {
                fileReaded.addException("Error durante en lectura de archivo loadInterviews: " + e.getMessage());
            }
        }

    }

    private User userConstructor(JsonNode userNode) {
        String idCode = userNode.get("codigo").asText();
        String forename = userNode.get("nombre").asText();
        String direction = userNode.get("direccion").asText();
        String username = userNode.get("username").asText();
        String password = userNode.get("password").asText();
        String email = userNode.get("email").asText();
        String noCUI = userNode.get("CUI").asText();
        String birthDate = "";
        if (userNode.has("fechaFundacion")) {
            birthDate = userNode.get("fechaFundacion").asText();
        } else {
            birthDate = userNode.get("fechaNacimiento").asText();
        }

        return new User(idCode, forename, direction, username, password, email, birthDate, noCUI, 0);
    }

    private int getState(String COMPARE, String state1, String state2) {
        int intState = 0;
        if (COMPARE.equals(state1)) {
            intState = 0;
        } else if (COMPARE.equals(state2)) {
            intState = 1;
        }
        return intState;
    }

    private int getState(String COMPARE, String state1, String state2, String state3) {
        int intState = 0;
        if (COMPARE.equals(state1)) {
            intState = 0;
        } else if (COMPARE.equals(state2)) {
            intState = 1;
        } else if (COMPARE.equals(state3)) {
            intState = 2;
        }
        return intState;
    }

    public FileReaded getFileReaded() {
        return fileReaded;
    }
}
