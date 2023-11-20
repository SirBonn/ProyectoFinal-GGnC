package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.*;
import ggnc.guatechancesapi.Models.Domain.Employer;
import ggnc.guatechancesapi.Models.Domain.EmployerByOffer;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmployersService {

    public void sendEmployer(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Employer employer = new Employer(req.getParameter("idCode"));
        employer = new SelectEmployer().getEmployer(employer);
        System.out.println("getting as: " + employer.toString());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), employer);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void sendAllEmployers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Employer> users = new SelectEmployer().getAllEmployers();
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), users);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void updateEmployer(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = req.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            System.out.println(req);
            System.out.println(req.getContentType());

            Employer emp = objectMapper.readValue(req.getInputStream(), Employer.class);

            System.out.println("Updating: " + emp.toString());
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), emp);
            new UpdateEmployer().updateEmployer(emp);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            objectMapper.writeValue(response.getWriter(),"error en la actualizacion del usuario" + e.getMessage());
            response.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            response.getWriter().print(objectNode.toString());
        }
    }

    public void getTopEmployersByOffers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<EmployerByOffer> topEmployers = new SelectReports().getTopEmployersPublisher();

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), topEmployers);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void getTopEmployersByPays(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String start = req.getParameter("startDate");
        String end = req.getParameter("endDate");
        List<Employer> topEmployers = new SelectReports().getTopEmployersContributedBy(start, end);

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), topEmployers);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}
