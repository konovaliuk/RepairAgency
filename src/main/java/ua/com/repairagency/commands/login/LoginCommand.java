package ua.com.repairagency.commands.login;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import ua.com.repairagency.services.LoginService;

import static ua.com.repairagency.services.UserTypeService.getUserTypeByUserName;

/** Class for the login command. */
public class LoginCommand implements ICommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    /** Checks user's credentials and redirects to main page in case of successful validation. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userType = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        ConfigurationManagerService config = ConfigurationManagerService.getInstance();
        MessageManagerService messages = MessageManagerService.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {

            if (LoginService.authenticateUser(login, password)){

                // gets logged in user's role to store in session
                userType = getUserTypeByUserName(login);

                session.setAttribute("user", login);
                session.setAttribute("user_type", userType);

                page = config.getProperty(ConfigurationManagerService.MAIN_PAGE);
            } else {
                request.setAttribute("error",
                        messages.getProperty(MessageManagerService.LOGIN_ERROR_MESSAGE));
                page = config.getProperty(ConfigurationManagerService.ERROR_PAGE);
            }
        } else {
            page = config.getProperty(ConfigurationManagerService.LOGIN_PAGE);
        }

        return page;
    }
}