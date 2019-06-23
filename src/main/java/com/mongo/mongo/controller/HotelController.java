package com.mongo.mongo.controller;


import com.mongo.mongo.domain.Hotel;
import com.mongo.mongo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
