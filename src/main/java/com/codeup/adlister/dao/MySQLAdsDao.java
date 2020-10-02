package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.api.mysqla.result.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao extends MySQLDao implements Ads {
    public MySQLAdsDao(Config config) {
        super(config);
    }

    // Returns a list of all the ads in the database
    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    // Returns a single ad by the ID passed in
    @Override
    public List<Ad> getAdById(long adId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ? LIMIT 1");
            stmt.setLong(1,adId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch(SQLException e) {
            throw new RuntimeException("Error getting ad by Id", e);
        }
    }

    // Inserts a new ad into the database
    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    // Returns an Ad object from the set passed in
    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("date")
        );
    }

    // Loops through a result set and returns a list of Ads, extracting an Ad from each set using extractAd
    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
