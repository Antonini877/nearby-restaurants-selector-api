package com.api.app.repository;

import com.api.app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    
    /**
     * Finds nearby restaurants using the Haversine formula
     * @param lat Reference point latitude
     * @param lon Reference point longitude
     * @param maxDistance Maximum distance in meters
     * @param limit Maximum number of restaurants to return
     * @return List of restaurants ordered by distance
     */
    @Query(value = "SELECT *, " +
                   "(6371000 * acos(cos(radians(:lat)) * cos(radians(lat)) * " +
                   "cos(radians(lon) - radians(:lon)) + sin(radians(:lat)) * " +
                   "sin(radians(lat)))) AS distance " +
                   "FROM restaurants " +
                   "HAVING distance <= :maxDistance " +
                   "ORDER BY distance " +
                   "LIMIT :limit", nativeQuery = true)
    List<Restaurant> findNearbyRestaurants(
        @Param("lat") Double lat,
        @Param("lon") Double lon,
        @Param("maxDistance") Double maxDistance,
        @Param("limit") Integer limit
    );
    
    /**
     * Finds nearby restaurants without distance limit
     * @param lat Reference point latitude
     * @param lon Reference point longitude
     * @param limit Maximum number of restaurants to return
     * @return List of restaurants ordered by distance
     */
    @Query(value = "SELECT *, " +
                   "(6371000 * acos(cos(radians(:lat)) * cos(radians(lat)) * " +
                   "cos(radians(lon) - radians(:lon)) + sin(radians(:lat)) * " +
                   "sin(radians(lat)))) AS distance " +
                   "FROM restaurants " +
                   "ORDER BY distance " +
                   "LIMIT :limit", nativeQuery = true)
    List<Restaurant> findNearbyRestaurants(
        @Param("lat") Double lat,
        @Param("lon") Double lon,
        @Param("limit") Integer limit
    );
} 