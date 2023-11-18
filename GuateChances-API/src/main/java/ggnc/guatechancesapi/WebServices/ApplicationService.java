package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.DeleteApplication;
import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.InsertApplication;
import ggnc.guatechancesapi.Models.DataBase.ApplicationsDAOs.SelectApplication;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.InsertUserCategory;
import ggnc.guatechancesapi.Models.Domain.Application;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.Offer;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ApplicationService {

    public void sendApplication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        int idCode = Integer.parseInt(req.getParameter("idCode"));
        Application application = new Application(idCode);

        try {

            application = new SelectApplication().selectApplicationByID(application);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), application);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }


    public void sendAllApplications(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        User user = new User(req.getParameter("userCode"));
        List<Application> applications = new SelectApplication().getAllApplicationsFromUser(user);
        resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(resp.getWriter(), applications);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void deleteApplication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        int idCode = Integer.parseInt(req.getParameter("idApplication"));
        Application application = new Application(idCode);
        try {
            new DeleteApplication().deleteApplicationByID(application);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ErrorOcurredException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void addApplication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            System.out.println(req);
            System.out.println(req.getContentType());
            Application app = objectMapper.readValue(req.getInputStream(), Application.class);
            System.out.println("adding: " +app);
            new InsertApplication().insertApplication(app);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());

        }
    }

    public void sendApplicationsByOffer (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        int idCode = Integer.parseInt(req.getParameter("offerId"));
        Offer offer = new Offer(idCode);

        try {

            List<Application> applications = new SelectApplication().getApplicationsByOffer(offer);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), applications);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

}
