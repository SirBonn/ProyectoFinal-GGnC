package ggnc.guatechancesapi.WebServlets;


import java.io.*;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertUser;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.http.entity.ContentType;

@WebServlet(name = "signinservlet", value = "/signin")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {

            System.out.println(request);
            System.out.println(request.getContentType());
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            System.out.println("eee" + user.toString());

            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), user);
            new InsertUser().insertUser(user);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", e.getMessage());
            response.getWriter().print(objectNode.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
