package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import ggnc.guatechancesapi.WebServices.CategoriesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerCategories", value = "/controller/categories")
public class CategoriesControllerServlet extends HttpServlet {

    private CategoriesService categoriesService = new CategoriesService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        if (req.getParameter("id") == null) {
            categoriesService.sendAllCategories(req, response);
        } else {
            //categoriesService.sendCategory(req, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("catID") == null) {
            //
        } else {
            categoriesService.updateStatusCategory(req, resp);
            
        }

    }
}
