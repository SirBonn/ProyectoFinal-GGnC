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

        if (req.getParameter("action") == null) {
        } else {
            switch (req.getParameter("action")) {
                case "createInterview":
                    interviewService.createInterview(req, resp);
                    break;
                default:
            }
        }
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
                    break;
                case "getInterviewsByDate":
                    interviewService.getOffersByDate(req, resp);
                    break;
                default:

            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("isContract") == null) {
            interviewService.updateInterview(req, resp, true);
        } else {
            interviewService.updateInterview(req, resp, false);
        }
    }

}
