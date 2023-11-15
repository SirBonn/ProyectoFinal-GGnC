package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.InsertOffer;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.SelectOffer;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectUser;
import ggnc.guatechancesapi.Models.Domain.Employer;
import ggnc.guatechancesapi.Models.Domain.Offer;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.List;

public class OfferServices {

    public void sendAllOffers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Offer> allOffers = new SelectOffer().getAllOffers();

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), allOffers);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void sendOffer(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Offer offer = new SelectOffer().getOffer(new Offer(Integer.parseInt(req.getParameter("idCode"))));

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), offer);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void sendOffersByEmployer(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Employer employer = new Employer(req.getParameter("empCode"));
        List<Offer> offersByEmployer = new SelectOffer().getActiveOffers(employer);


        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), offersByEmployer);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void createOffer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            Offer offer = objectMapper.readValue(req.getReader(), Offer.class);
            offer.setPublicationDate(offer.setNowDate());
            System.out.println(offer.toString());
            new InsertOffer().insertOffer(offer);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), offer);
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
