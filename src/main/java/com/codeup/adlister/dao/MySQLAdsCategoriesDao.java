package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsCategoriesDao extends MySQLDao implements AdsCategories {
    public MySQLAdsCategoriesDao(Config config) {
        super(config);
    }

    // This will take an Ad id and a category Id and put them into the Ads_categories database together
    @Override
    public long joinAdsToCategories (long adId, long catId) {
        try {
            String insertQuery = "INSERT INTO ads_categories(ad_id, category_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adId);
            stmt.setLong(2, catId);
            return (long) stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in adding to ad_categories", e);
        }
    }

    // Returns a list of all categories associated with the adId that is passed in
    @Override
    public List<Category> getCategoriesByAdId(long adId) {
        try {
            String query = "SELECT * FROM ads_categories WHERE ad_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,adId);
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch(SQLException e) {
            throw new RuntimeException("Error in getting categories for ad_id",e);
        }
    }

    @Override
    public List<Ad> getAdsByCategoryId(long categoryId) {
        return null;
    }

    // returns a Category object from a category Id
    private Category extractCategory(ResultSet rs) throws SQLException {
        return DaoFactory.getCategoriesDao().getCategoryById(rs.getLong("category_id"));
    }

    // Loops through a ResultSet and creates a category for each set
    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }
}
