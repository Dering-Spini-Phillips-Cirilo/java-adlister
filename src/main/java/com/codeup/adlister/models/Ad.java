package com.codeup.adlister.models;

import java.sql.Date;
import java.util.ArrayList;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private Date date;
    private ArrayList<Category> categories;

    public Ad(long id, long userId, String title, String description, Date date) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Ad(long userId, String title, String description, ArrayList<Category> categories) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        System.out.println(this.getTitle());
        for(Category category : categories) {
            System.out.println(category.getName());
        }
        System.out.println("-------------------");
        this.categories = categories;
    }
}
