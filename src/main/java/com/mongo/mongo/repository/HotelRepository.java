package com.mongo.mongo.repository;

import com.mongo.mongo.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{address.city.?0}")
    List<Hotel> findByCity(String city);
}
