package com.api.app.dto;

public class RestaurantResponse {
    private Integer id;
    private String name;
    private Double lat;
    private Double lon;
    private String streetName;
    private String neighborhood;
    private Double distance; // in meters
    
    public RestaurantResponse() {}
    
    public RestaurantResponse(Integer id, String name, Double lat, Double lon, 
                            String streetName, String neighborhood, Double distance) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.distance = distance;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getLat() {
        return lat;
    }
    
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public Double getLon() {
        return lon;
    }
    
    public void setLon(Double lon) {
        this.lon = lon;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public String getNeighborhood() {
        return neighborhood;
    }
    
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    public Double getDistance() {
        return distance;
    }
    
    public void setDistance(Double distance) {
        this.distance = distance;
    }
} 