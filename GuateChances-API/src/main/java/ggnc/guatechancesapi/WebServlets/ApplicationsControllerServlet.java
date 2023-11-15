package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import ggnc.guatechancesapi.WebServices.ApplicationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "controllerApplications", value = "/controller/application")
public class ApplicationsControllerServlet extends HttpServlet {

    private ApplicationService applicationsService = new ApplicationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "addApplication":
                applicationsService.addApplication(req, resp);
                break;
            case "removeApplication":
                //applicationsService.removeApplication(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action") != null) {
            switch (req.getParameter("action")) {
                case "getApplications":
                    applicationsService.sendAllApplications(req, resp);
                    break;
                    case "getApplicationsByOffer":
                    applicationsService.sendApplicationsByOffer(req, resp);
                    break;
                default:
                    break;
            }
        } else {
            if (req.getParameter("idCode") == null) {
//                applicationsService.sendAllApplications(req, resp);
            } else {
                applicationsService.sendApplication(req, resp);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idApplication") != null) {
            applicationsService.deleteApplication(req, resp);
        }
    }

}
