package ggnc.guatechancesapi.WebServlets;

import java.io.*;
import ggnc.guatechancesapi.WebServices.EmployersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerEmployers", value = "/controller/usr/employers")
public class EmployersControllerServlet extends HttpServlet{

    private EmployersService usersService = new EmployersService();
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        if (req.getParameter("idCode") == null) {
            usersService.sendAllEmployers(req, response);
        } else {
            usersService.sendEmployer(req, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersService.updateEmployer(req, resp);
    }
}
