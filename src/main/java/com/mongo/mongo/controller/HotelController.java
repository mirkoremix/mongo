package com.mongo.mongo.controller;


import com.mongo.mongo.domain.Hotel;
import com.mongo.mongo.domain.QHotel;
import com.mongo.mongo.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/all")
    public List<Hotel> getAlList() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @PutMapping
    public void insert(@RequestBody Hotel hotel) {
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void update(@RequestBody Hotel hotel) {
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        hotelRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id) {
        Optional<Hotel> opt = hotelRepository.findById(id);
        Hotel hotel = opt.orElseThrow(NullPointerException::new);
        return hotel;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice) {
        List<Hotel> hotels = hotelRepository.findByPricePerNightLessThan(maxPrice);
        return hotels;
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {
        List<Hotel> hotels = hotelRepository.findByCity(city);
        return hotels;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country) {
        // create Query class qHotel
        QHotel qHotel = new QHotel("hotel");
        //with query class create filter
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);
        // pass filters to find all method
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByCountry);
        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecomended() {
        final int maxPrice = 100;
        final int minRating = 7;

        // create Query class qHotel
        QHotel qHotel = new QHotel("hotel");
        //with query class create filter
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);
        // pass filters to find all method
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
        return hotels;
    }
}