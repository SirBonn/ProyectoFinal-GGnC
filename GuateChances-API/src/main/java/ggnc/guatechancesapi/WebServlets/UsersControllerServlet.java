package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import ggnc.guatechancesapi.WebServices.UsersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerUsers", value = "/controller/usr/users")
public class UsersControllerServlet extends HttpServlet {

    private UsersService usersService = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        if (req.getParameter("cui") == null) {
            usersService.sendAllUsers(req, response);
        } else {
            usersService.sendUser(req, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usersService.updateUser(req, resp);
    }
}
