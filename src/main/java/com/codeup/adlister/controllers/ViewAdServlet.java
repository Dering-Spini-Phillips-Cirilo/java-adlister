package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
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
        String title = request.getParameter("title");
        long id =Long.parseLong(request.getParameter("user_id"));
        List<Ad> allAds = DaoFactory.getAdsDao().all();
        String User = null;
        for (Ad ad : allAds) {
            if (ad.getId() == id && ad.getTitle().equals(title)) {
                request.getSession().setAttribute("id", ad.getId());
                request.getSession().setAttribute("user_id", ad.getUserId());
                request.getSession().setAttribute("title", ad.getTitle());
                request.getSession().setAttribute("description", ad.getDescription());
                User = String.valueOf(ad.getUserId());
            }
        }
        User user = DaoFactory.getUsersDao().findByUsername(User);
        request.getSession().setAttribute("username", user.getUsername());

        request.getRequestDispatcher("/WEB-INF/ads/view.jsp").forward(request, response);
    }
}
