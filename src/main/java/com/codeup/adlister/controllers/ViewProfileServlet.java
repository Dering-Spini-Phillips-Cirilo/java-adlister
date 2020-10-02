package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("redirect", "/profile");
            response.sendRedirect("/login");
            return;
        }

        // grabs all ads then loops through them and will separate them if the ad is create by the current user.
        User user = (User) request.getSession().getAttribute("user");
        List<Ad> allAds = DaoFactory.getAdsDao().all();
        List<Ad> bucket = new ArrayList<>();

        for (Ad ad : allAds) {
            if (ad.getUserId() == user.getId()) {
                bucket.add(ad);
            }
        }
        request.getSession().setAttribute("userAds",bucket);

        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
