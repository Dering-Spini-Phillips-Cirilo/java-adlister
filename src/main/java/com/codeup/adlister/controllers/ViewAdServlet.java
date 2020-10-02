package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.ViewAdServlet", urlPatterns = "/ads/view")
public class ViewAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Gets the Ad id passed as a parameter and gets the associated ad with it to be displayed
        long adId = Long.parseLong(request.getParameter("adId"));
        List<Ad> listAd = DaoFactory.getAdsDao().getAdById(adId);
        Ad currentAd = listAd.get(0);

        request.getSession().setAttribute("currentAd", currentAd);
        request.getSession().setAttribute("currentUser", DaoFactory.getUsersDao().findById(currentAd.getUserId()));
        request.getRequestDispatcher("/WEB-INF/ads/view.jsp").forward(request, response);
    }
}