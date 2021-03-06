package ua.com.repairagency.services;

import org.apache.log4j.Logger;
import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.dao.interfaces.ICommentDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/** Service class for loading lists of entities from the database. */
public class LoadListService {

    private static final Logger log = Logger.getLogger(LoadListService.class);

    /** Loads comments. */
    public static void loadComments(HttpServletRequest request) {
        log.info("Trying to load comments list.");

        int pageNum = 1;
        int total = 5;
        int start = 1;

        if(request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));

        if(pageNum == 1){}
        else{
            start = pageNum - 1;
            start = (pageNum - 1) * total + 1;
        }
        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
        List<Comment> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = commentDAO.getNumberOfRecords();
            list = commentDAO.getComments(start, total);

            // the returned list might be empty, in which case we don't need it
            if (list.isEmpty()) {
                log.warn("The list was empty, now set to null.");
                list = null;
            }
        } catch (SQLException ex) {
            log.error("Problem getting comments list:", ex);
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("commentsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
        log.info("The loadComments method finished successfully.");
    }

    /** Loads applications. */
    public static void loadApplications(HttpServletRequest request) {
        log.info("Trying to load applications list.");

        int pageNum = 1;
        int total = 5;
        int start = 1;

        if(request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));

        if(pageNum == 1){}
        else{
            start = pageNum - 1;
            start = start * total + 1;
        }

        IApplicationDAO applicationDAO = DAOFactory.getMySQLApplicationDAO();
        List<Application> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = applicationDAO.getNumberOfRecords();
            list = applicationDAO.getApplications(start, total);

            // the returned list might be empty, in which case we don't need it
            if (list.isEmpty()) {
                log.warn("The list was empty, now set to null.");
                list = null;
            }
        } catch (SQLException ex) {
            log.error("Problem getting applications list:", ex);
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("applicationsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
        log.info("The loadApplications method finished successfully.");
    }

    /** Loads accepted applications. */
    public static void loadAcceptedApps(HttpServletRequest request) {
        log.info("Trying to load accepted applications list.");

        int pageNum = 1;
        int total = 5;
        int start = 1;

        if(request.getParameter("pageNum") != null)
            pageNum = Integer.parseInt(request.getParameter("pageNum"));

        if(pageNum == 1){}
        else{
            start = pageNum - 1;
            start = start * total + 1;
        }

        IAcceptedApplicationDAO acceptedApplicationDAO = DAOFactory.getMySQLAcceptedApplicationDAO();
        List<AcceptedApplication> list = null;
        int numOfRecords = 0;

        try {
            numOfRecords = acceptedApplicationDAO.getNumberOfRecords();
            list = acceptedApplicationDAO.getAcceptedApplications(start, total);

            // the returned list might be empty, in which case we don't need it
            if (list.isEmpty()) {
                log.warn("The list was empty, now set to null.");
                list = null;
            }
        } catch (SQLException ex) {
            log.error("Problem getting accepted applications list:", ex);
        }

        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / total);
        request.setAttribute("acceptedAppsList", list);
        request.setAttribute("numOfPages", numOfPages);
        request.setAttribute("pageNum", pageNum);
        log.info("The loadAcceptedApps method finished successfully.");
    }
}
