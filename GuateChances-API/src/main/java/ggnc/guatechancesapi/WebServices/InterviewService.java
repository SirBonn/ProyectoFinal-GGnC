package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs.InsertInterview;
import ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs.SelectInterview;
import ggnc.guatechancesapi.Models.DataBase.InterviewsDAOs.UpdateInterview;
import ggnc.guatechancesapi.Models.Domain.*;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.List;

public class InterviewService {

    public void getAllInterviews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            List<Interview> interviews = new SelectInterview().getAllInterviews();
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interviews);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void getInterview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            int intCode = Integer.parseInt(req.getParameter("interviewCode"));
            Interview interview = new SelectInterview().getInterviewByCode(new Interview(intCode));
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interview);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void getOfferInterviews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            int offerCode = Integer.parseInt(req.getParameter("offerCode"));
            Offer offer = new Offer(offerCode);
            List<Interview> interviews = new SelectInterview().getInterviewsByOffer(offer);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interviews);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void getApplicationInterview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            int appCode = Integer.parseInt(req.getParameter("appCode"));
            Interview interview = new SelectInterview().getInterviewByApplication(new Application(appCode));
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interview);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void getUserInterviews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            User user = new User(req.getParameter("seekerId"));
            List<Interview> interviews = new SelectInterview().getInterviewsBySeeker(user);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interviews);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void getEmployerInterviews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            Employer user = new Employer(req.getParameter("empCode"));
            List<Interview> interviews = new SelectInterview().getInterviewsByEmployer(user);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interviews);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void createInterview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            Interview interview = objectMapper.readValue(req.getReader(), Interview.class);
            new InsertInterview().insertInterviewWithUpdate(interview);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interview);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());

        }
    }

    public void updateInterview(HttpServletRequest req, HttpServletResponse resp, boolean isContract) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            Interview interview = objectMapper.readValue(req.getReader(), Interview.class);
            new UpdateInterview().updateStateInterview(interview, isContract);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interview);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());

        }
    }

    public void getOffersByDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            String date = req.getParameter("date");
            String idCode = req.getParameter("idCode");
            List<Interview> interviews = new SelectInterview().getInterviewsByDate(date, idCode);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), interviews);
            resp.setStatus(HttpServletResponse.SC_CREATED);

        } catch (Exception e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());

        }
    }

}
