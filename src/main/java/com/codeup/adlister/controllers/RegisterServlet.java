package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.codeup.adlister.util.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");


        session.setAttribute("currentUserExists", false);
        session.setAttribute("passwordsDoNotMatch", false);
        session.setAttribute("unsafePassword", false);


        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty();

        if (inputHasErrors) {
            response.sendRedirect("/register");
            return;
        }

        List<String> allCurrentUsernames = DaoFactory.getUsersDao().currentUsernames();
        if (Validate.userNameExists(allCurrentUsernames, username)) {
            session.setAttribute("currentUserExists", true);
            response.sendRedirect("/register");
            return;
        }

        if ((!password.equals(passwordConfirmation))) {
            session.setAttribute("passwordsDoNotMatch", true);
            response.sendRedirect("/register");
            return;
        }

        if (!Validate.safePassword(password)) {
            session.setAttribute("unsafePassword", true);
            response.sendRedirect("/register");
            return;
        }

        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
