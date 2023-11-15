package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.UpdateCategory;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.InsertUser;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.SelectUser;
import ggnc.guatechancesapi.Models.DataBase.UsersDAOs.UpdateUser;
import ggnc.guatechancesapi.Models.Domain.Category;
import ggnc.guatechancesapi.Models.Domain.User;
import ggnc.guatechancesapi.Utils.ErrorOcurredException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UsersService {


    public void sendUser(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            User user = new SelectUser().getUserByID(new User(req.getParameter("cui")));
            System.out.println("getting as: " + user.toString());
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            objectMapper.writeValue(response.getWriter(), user);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ErrorOcurredException e) {
            e.printStackTrace();
        }
    }

    public void sendAllUsers(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<User> users = new SelectUser().getAllUsers();
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), users);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String userID= req.getParameter("userID");


        try {
            User user = new SelectUser().getUserByID(new User(userID));
            new UpdateUser().updateIsActiveUser(user);
            objectMapper.writeValue(response.getWriter(),"Actualizada correctamente");
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            objectMapper.writeValue(response.getWriter(),"Error en la actualizacion de la categoria" + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

    }

}
