package com.example.csi2999;

public class Site {
    private int site_id;
    private String name;
    private String description;
    private String cost_per_day;
    private boolean full_hookup;
    private boolean rustic;
    private boolean water_and_electric;
    private String picture_name;

    // Getters and Setters
    
    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost_per_day() {
        return cost_per_day;
    }

    public void setCost_per_day(String cost_per_day) {
        this.cost_per_day = cost_per_day;
    }

    public boolean isFull_hookup() {
        return full_hookup;
    }

    public void setFull_hookup(boolean full_hookup) {
        this.full_hookup = full_hookup;
    }

    public boolean isRustic() {
        return rustic;
    }

    public void setRustic(boolean rustic) {
        this.rustic = rustic;
    }

    public boolean isWater_and_electric() {
        return water_and_electric;
    }

    public void setWater_and_electric(boolean water_and_electric) {
        this.water_and_electric = water_and_electric;
    }

    public String getPicture_name() {
        return picture_name;
    }
    
    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }
}
