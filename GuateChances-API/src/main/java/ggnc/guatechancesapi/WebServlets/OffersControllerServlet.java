package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import ggnc.guatechancesapi.WebServices.OfferServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerOffers", value = "/controller/offers")
public class OffersControllerServlet extends HttpServlet {
    OfferServices offerServices = new OfferServices();

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {

        if (req.getParameter("action") == null) {
            if (req.getParameter("idCode") == null) {
                offerServices.sendAllOffers(req, response);
            } else {
                offerServices.sendOffer(req, response);
            }
        } else {

            switch (req.getParameter("action")) {
                case "getOffersByEmployer":
                    offerServices.sendOffersByEmployer(req, response);
                    break;
                case "createOffer":
                    offerServices.createOffer(req, response);
                    break;
                default:
                    break;
            }

        }


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {

        switch (req.getParameter("action")) {
            case "getOffersByEmployer":
                offerServices.sendOffersByEmployer(req, response);
                break;
            case "createOffer":
                offerServices.createOffer(req, response);
                break;
            default:
                break;
        }


    }

}
