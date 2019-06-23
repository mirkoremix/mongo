package com.mongo.mongo.comon;

import com.mongo.mongo.domain.Address;
import com.mongo.mongo.domain.Hotel;
import com.mongo.mongo.domain.Review;
import com.mongo.mongo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        Hotel meriot = new Hotel();
//        meriot.setId();
        meriot.setName("Merriot");
        Address a1 = new Address();
        a1.setCity("Paris");
        a1.setCountry("France");
        meriot.setAddress(a1);

        Review r1 = new Review();
        r1.setUserName("John");
        r1.setRating(8);
        r1.setApproved(false);

        Review r2 = new Review();
        r2.setUserName("Mary");
        r2.setRating(7);
        r2.setApproved(true);

        meriot.setReviews(Arrays.asList(r1, r2));


        Hotel trivago = new Hotel();
//        meriot.setId();
        trivago.setName("Trivago");
        Address a2 = new Address();
        a2.setCity("London");
        a2.setCountry("UK");
        trivago.setAddress(a2);

        Review r3 = new Review();
        r3.setUserName("Mimi");
        r3.setRating(10);
        r3.setApproved(false);

        Review r4 = new Review();
        r4.setUserName("Marijola");
        r4.setRating(3);
        r4.setApproved(true);

        trivago.setReviews(Arrays.asList(r3, r4));


        Hotel continental = new Hotel();
//        meriot.setId();
        continental.setName("Merriot");
        Address a3 = new Address();
        a3.setCity("Paris");
        a3.setCountry("France");
        continental.setAddress(a3);

        Review r5 = new Review();
        r5.setUserName("Cole");
        r5.setRating(1);
        r5.setApproved(false);

        Review r6 = new Review();
        r6.setUserName("Jole");
        r6.setRating(3);
        r6.setApproved(true);

        continental.setReviews(Arrays.asList(r5, r6));


        //drop all hotels
        hotelRepository.deleteAll();
        List<Hotel> hotels = Arrays.asList(meriot, trivago, continental);
        hotelRepository.saveAll(hotels);

    }
}
