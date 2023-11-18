package ggnc.guatechancesapi.WebServlets;


import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ggnc.guatechancesapi.Utils.FileMagnament;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "filesController", value = "/controller/files")
@jakarta.servlet.annotation.MultipartConfig
public class filesControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("JSONfile");
        String fileName = filePart.getSubmittedFileName();
        boolean isJson = fileName.toLowerCase().endsWith(".json");
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        FileMagnament filemanager = null;
        System.out.println("--------------------");
        System.out.println(filePart.getContentType());
        System.out.println(filePart.getSubmittedFileName());
        System.out.println(filePart.getHeader("Content-disposition"));
        System.out.println("--------------------");

        if (isJson) {
            InputStream fileContent = filePart.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {

                sb.append(line);
            }

            filemanager = new FileMagnament(sb.toString(), reader);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            //resp.getWriter().println("se ha leido el archivo json:\n");
            //resp.getWriter().println(sb.toString());
        } else {
            resp.getWriter().println("El archivo no es un json");
        }

        try {
            filemanager.loadData();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("succes", "Se ha insertado la data");
            objectNode.put("failed", filemanager.getFileReaded().getExceptions());
            resp.getWriter().print(objectNode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error en la lectura del archivo", filemanager.getFileReaded().getExceptions());
            resp.getWriter().print(objectNode.toString());
        }
    }
}