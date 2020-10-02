package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao extends MySQLDao implements Categories {
    public MySQLCategoriesDao(Config config) {
        super(config);
    }

    @Override
    public List<Category> getAllCategories() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategoriesFromResults(rs);
        } catch(SQLException e) {
            throw new RuntimeException("Error getting all categories", e);
        }
    }

    @Override
    public Category getCategoryById(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories WHERE id = ? LIMIT 1");
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
                return null;
            return new Category(rs.getString("name"),rs.getLong("id"));
        } catch(SQLException e) {
            throw new RuntimeException("Error getting category from ID",e);
        }
    }

    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getString("name"),
                rs.getLong("id")
        );
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }

}
