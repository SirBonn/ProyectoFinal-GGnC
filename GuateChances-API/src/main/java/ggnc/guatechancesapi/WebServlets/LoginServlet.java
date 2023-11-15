package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectUser;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.http.entity.ContentType;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println(request);
            System.out.println(request.getContentType());
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            user = new SelectUser().SearchByUser(user);

            if (user.getUsertype() == -1) {
                throw new ErrorOcurredException("credenciales invalidas");
            }

            System.out.println("Loged as: " + user.toString());

            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), user);
            response.setStatus(HttpServletResponse.SC_CREATED);

        } catch (ErrorOcurredException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        if (req.getParameter("idCode") == null){

        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
                User user = new SelectUser().getUserByID(new User(req.getParameter("idCode")));
                System.out.println("Loged as: " + user.toString());
                response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
                objectMapper.writeValue(response.getWriter(), user);
                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (ErrorOcurredException e) {
                e.printStackTrace();
            }
        }

    }

}
