package com.api.app.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 150)
    private String name;
    
    @Column(name = "lat", nullable = false, precision = 8, scale = 6)
    private Double lat;
    
    @Column(name = "lon", nullable = false, precision = 8, scale = 6)
    private Double lon;
    
    @Column(name = "street_name", nullable = false, length = 70)
    private String streetName;
    
    @Column(name = "neighborhood", nullable = false, length = 70)
    private String neighborhood;
    
    // Construtores
    public Restaurant() {}
    
    public Restaurant(String name, Double lat, Double lon, String streetName, String neighborhood) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
    }
    
    // Getters e Setters
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
} 