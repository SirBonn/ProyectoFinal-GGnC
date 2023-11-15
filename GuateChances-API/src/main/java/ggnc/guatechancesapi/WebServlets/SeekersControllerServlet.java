package ggnc.guatechancesapi.WebServlets;

import ggnc.guatechancesapi.WebServices.SeekersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "controllerSeekers", value = "/controller/usr/seekers")
@jakarta.servlet.annotation.MultipartConfig
public class SeekersControllerServlet extends HttpServlet {

    private SeekersService seekersService = new SeekersService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("action")) {
            case "savePDF":
                seekersService.savePDF(req, resp);
                break;
            case "addCategory":
                seekersService.addCategory(req, resp);
                break;
            case "removeCategory":
                seekersService.removeCategory(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("action") != null) {
            switch (req.getParameter("action")) {
                case "isPDFfileUploaded":
                    seekersService.isPDFfileUploaded(req, resp);
                    break;
                case "getCategoriesList":
                    seekersService.getCategoriesList(req, resp);
                    break;
                case "getDiffCategoriesList":
                    seekersService.getDiffCategoriesList(req, resp);
                    break;
                case "getOffersByCategories":
                    seekersService.getOffersByCategories(req, resp);
                    break;
                default:
                    break;
            }
        } else {
            if (req.getParameter("idCode") == null) {
                //seekersService.sendAllSeekers(req, resp);
            } else {
                seekersService.sendSeeker(req, resp);
            }
        }

    }
}
