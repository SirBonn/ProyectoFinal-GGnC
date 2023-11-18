package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.SelectOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectEmployer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectSeeker;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertSeeker;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.DeleteUserCategory;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.InsertUserCategory;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UserCategories.SelectUserCategories;
import ggnc.guatechancesapi.Models.Domain.*;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class SeekersService {


    public void sendSeeker(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
        System.out.println("getting as: " + jobSeeker.toString());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), jobSeeker);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void sendAllSeekers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Employer> users = new SelectEmployer().getAllEmployers();
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), users);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void savePDF(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = req.getPart("PDFfile");
        String fileName = filePart.getSubmittedFileName();
        InputStream pdfInputStream = null;
        boolean isPDF = fileName.toLowerCase().endsWith(".pdf");

        if (filePart != null) {
            pdfInputStream = filePart.getInputStream();
        }

        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        jobSeeker = new SelectSeeker().getSeekerByID(jobSeeker);
        try {
            if (isPDF) {
                jobSeeker.setCv(pdfInputStream);
                System.out.println("getting with pdf: " + jobSeeker.toString());
                new InsertSeeker().insertPDFtoSeeker(jobSeeker);
                response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.getWriter().println("El archivo no es un pdf");
                throw new ErrorOcurredException("El archivo no es un pdf");
            }
        } catch (ErrorOcurredException e) {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            sendError(objectMapper, e, req, response);

        }
    }

    public void isPDFfileUploaded(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        jobSeeker = new SelectSeeker().getCompleteSeekerByID(jobSeeker);
        if (jobSeeker.getCv() != null) {
            response.getWriter().println("true");
        } else {
            response.getWriter().println("false");
        }
    }

    public void getCategoriesList(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
            List<Category> userCategories = new SelectUserCategories().selectCategoriesByUser(jobSeeker);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), userCategories);
            response.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            sendError(objectMapper, e, req, response);

        }
    }

    public void getDiffCategoriesList(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        try {
            List<Category> categories = new SelectUserCategories().selectDiffCategories(new JobSeeker(req.getParameter("idCode")));
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), categories);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ErrorOcurredException e) {

            sendError(objectMapper, e, req, response);

        }
    }

    public void addCategory(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        InputStream inputStream = req.getInputStream();
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        try {
            System.out.println(req);
            System.out.println(req.getContentType());
            Category category = objectMapper.readValue(req.getInputStream(), Category.class);
            UserCategories userCategories = new UserCategories(jobSeeker.getIdCode());
            userCategories.addCategory(category.getIdCode());
            new InsertUserCategory().insertUserCategory(userCategories);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch ( Exception e) {

            sendError(objectMapper, e, req, response);

        }
    }

    public void removeCategory(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        InputStream inputStream = req.getInputStream();
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));
        try {
            System.out.println(req);
            System.out.println(req.getContentType());
            Category category = objectMapper.readValue(req.getInputStream(), Category.class);

            new DeleteUserCategory().deleteUserCategory(jobSeeker, category);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ErrorOcurredException e) {

            sendError(objectMapper, e, req, response);

        }
    }

    public void getOffersByCategories(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        JobSeeker jobSeeker = new JobSeeker(req.getParameter("idCode"));

        try {
            System.out.println(req);
            System.out.println(req.getContentType());
            List<Offer> userSuggestedOffers = new SelectOffer().getOffersByCategories(jobSeeker);
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), userSuggestedOffers);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            sendError(objectMapper, e, req, response);
        }
    }

    private void sendError(ObjectMapper objectMapper, Exception e, HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json");
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", e.getMessage());
        response.getWriter().print(objectNode.toString());
    }
}
