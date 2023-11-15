package ggnc.guatechancesapi.WebServlets;

import java.io.*;

import ggnc.guatechancesapi.WebServices.InterviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerInterviews", value = "/controller/interviews")
public class InterviewsControllerServlet extends HttpServlet {

    InterviewService interviewService = new InterviewService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") == null) {

            if (req.getParameter("interviewCode") == null) {
                interviewService.getAllInterviews(req, resp);
            } else {
                interviewService.getInterview(req, resp);
            }

        } else {

            switch (req.getParameter("action")) {

                case "getOfferInterviews":
                    interviewService.getOfferInterviews(req, resp);
                    break;
                case "getUserInterviews":
                    interviewService.getUserInterviews(req, resp);
                    break;
                case "getEmployerInterviews":
                    interviewService.getEmployerInterviews(req, resp);
                    break;
                case "getApplicationInterview":
                    interviewService.getApplicationInterview(req, resp);
                default:

            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
