package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // Validate Input - If error save the fields that aren't bad
        // show a small error above the fields that do have errors

        // validate input
        HashMap<String, Boolean> errorList = Validate.getErrorList(username,email,password,passwordConfirmation);
        boolean validInput = Validate.checkForErrors(errorList);

        if (!validInput) {
            ArrayList<String> errorMessages = Validate.getErrorMessages(errorList);
            // Display error messages in jsp partial
            request.getSession().setAttribute("errorMessages", errorMessages);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("email",email);
            if(!errorList.get("nameAvailable"))
                request.getSession().removeAttribute("username");
            if(!errorList.get("emailAvailable"))
                request.getSession().removeAttribute("email");
            response.sendRedirect("/register");
            return;
        }
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("email");

        // create and save a new user
        User user = new User(username.toLowerCase(), email.toLowerCase(), password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
