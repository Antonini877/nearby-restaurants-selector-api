package com.api.app.controller;

import com.api.app.dto.RestaurantResponse;
import com.api.app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;
    
    /**
     * Finds nearby restaurants with distance limit
     * @param lat Reference point latitude
     * @param lon Reference point longitude
     * @param maxDistance Maximum distance in meters (optional)
     * @param limit Maximum number of restaurants to return (optional, default: 10)
     * @return List of restaurants ordered by distance
     */
    @GetMapping("/nearby")
    public ResponseEntity<List<RestaurantResponse>> findNearbyRestaurants(
            @RequestParam("lat") Double lat,
            @RequestParam("lon") Double lon,
            @RequestParam(value = "maxDistance", required = false) Double maxDistance,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        
        // Parameter validation
        if (lat == null || lon == null) {
            return ResponseEntity.badRequest().build();
        }
        
        if (lat < -90 || lat > 90) {
            return ResponseEntity.badRequest().build();
        }
        
        if (lon < -180 || lon > 180) {
            return ResponseEntity.badRequest().build();
        }
        
        if (limit != null && (limit <= 0 || limit > 100)) {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            List<RestaurantResponse> restaurants;
            
            if (maxDistance != null && maxDistance > 0) {
                // Search with distance limit
                restaurants = restaurantService.findNearbyRestaurants(lat, lon, maxDistance, limit);
            } else {
                // Search without distance limit
                restaurants = restaurantService.findNearbyRestaurants(lat, lon, limit);
            }
            
            return ResponseEntity.ok(restaurants);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
} 