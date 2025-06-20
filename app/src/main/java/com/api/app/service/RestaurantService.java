package com.api.app.service;

import com.api.app.dto.RestaurantResponse;
import com.api.app.model.Restaurant;
import com.api.app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    /**
     * Finds nearby restaurants with distance limit
     * @param lat Reference point latitude
     * @param lon Reference point longitude
     * @param maxDistance Maximum distance in meters
     * @param limit Maximum number of restaurants to return
     * @return List of restaurants with calculated distance
     */
    public List<RestaurantResponse> findNearbyRestaurants(Double lat, Double lon, 
                                                        Double maxDistance, Integer limit) {
        List<Restaurant> restaurants = restaurantRepository.findNearbyRestaurants(lat, lon, maxDistance, limit);
        return convertToResponse(restaurants, lat, lon);
    }
    
    /**
     * Finds nearby restaurants without distance limit
     * @param lat Reference point latitude
     * @param lon Reference point longitude
     * @param limit Maximum number of restaurants to return
     * @return List of restaurants with calculated distance
     */
    public List<RestaurantResponse> findNearbyRestaurants(Double lat, Double lon, Integer limit) {
        List<Restaurant> restaurants = restaurantRepository.findNearbyRestaurants(lat, lon, limit);
        return convertToResponse(restaurants, lat, lon);
    }
    
    /**
     * Converts Restaurant list to RestaurantResponse with calculated distance
     */
    private List<RestaurantResponse> convertToResponse(List<Restaurant> restaurants, 
                                                     Double userLat, Double userLon) {
        return restaurants.stream()
            .map(restaurant -> {
                Coordinates userCoords = new Coordinates(userLat, userLon);
                Coordinates restaurantCoords = new Coordinates(restaurant.getLat(), restaurant.getLon());
                double distance = userCoords.calculateDistance(restaurantCoords) * 1000; // convert to meters
                
                return new RestaurantResponse(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getLat(),
                    restaurant.getLon(),
                    restaurant.getStreetName(),
                    restaurant.getNeighborhood(),
                    distance
                );
            })
            .collect(Collectors.toList());
    }
} 