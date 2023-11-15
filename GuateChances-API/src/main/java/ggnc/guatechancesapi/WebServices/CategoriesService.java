package ggnc.guatechancesapi.WebServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.SelectCategory;
import ggnc.guatechancesapi.Models.DataBase.CategoryDAOs.UpdateCategory;
import ggnc.guatechancesapi.Models.Domain.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.List;

public class CategoriesService {


    public void sendAllCategories(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Category> categories = new SelectCategory().getAllCategories();
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        objectMapper.writeValue(response.getWriter(), categories);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    public void updateStatusCategory(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        int catId= Integer.parseInt(req.getParameter("catID"));
        Category category = new SelectCategory().getCategory(new Category(catId));

        try {
            new UpdateCategory().updateIsActiveCategory(category);
            objectMapper.writeValue(response.getWriter(),"Actualizada correctamente");
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            objectMapper.writeValue(response.getWriter(),"Error en la actualizacion de la categoria" + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }



    }

}
