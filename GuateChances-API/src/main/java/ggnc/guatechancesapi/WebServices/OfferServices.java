package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.InsertOffer;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment.InsertUpdatePayment;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment.SelectPaymentLogs;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.SelectOffer;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.PlataformPayment.SelectOfferPayment;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.SelectOffersReports;
import ggnc.guatechancesapi.Models.DataBase.OffersDAOs.UpdateOffer;
import ggnc.guatechancesapi.Models.Domain.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.ArrayList;
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
        List<Offer> offersByEmployer = new ArrayList<>();
        if (req.getParameter("offers") == null) {
            offersByEmployer = new SelectOffer().getActiveOffers(employer);
        } else {
            offersByEmployer = new SelectOffer().getOffersByEmployer(employer);
        }

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

    public void sendOffersPayment(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        double payment = new SelectOfferPayment().getOfferPayment();

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), payment);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void updateOffersPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            User user = new User(req.getParameter("idCode"));
            double payment = objectMapper.readValue(req.getReader(), double.class);
            PlataformPayment plataformPayment = new PlataformPayment(user, payment);
            new InsertUpdatePayment().insertLog(plataformPayment);
            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), payment);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }

    public void updateOffersState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            List<Offer> offers = new SelectOffer().getAllOffers();
            List<Offer> expireOffers = new ArrayList<>();
            for (Offer offer : offers) {
                if (offer.getExpireDate().before(offer.setNowDate()) && offer.getOfferState() == 0) {
                    offer.setOfferState(1);
                    new UpdateOffer().updateOfferState(offer);
                    expireOffers.add(offer);
                }
            }

            resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(resp.getWriter(), expireOffers);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            resp.getWriter().print(objectNode.toString());
        }
    }


    public void sendPaymentLogs(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<PlataformPayment> paymentLogs = new SelectPaymentLogs().getPaymentLogs();

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), paymentLogs);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void sendOffersByTimes (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String idCode = req.getParameter("idCode");
        List<Offer> offersByTimes = new SelectOffersReports().getOffersByTimes(req.getParameter("start"), req.getParameter("end"), idCode);

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), offersByTimes);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void getTotalPayments (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String idCode = req.getParameter("idCode");
        double totalPayments = new SelectOffersReports().getTotalPayments(idCode);

        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), totalPayments);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}
