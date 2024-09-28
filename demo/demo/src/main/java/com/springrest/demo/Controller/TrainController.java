package com.springrest.demo.Controller;


import com.springrest.demo.Entity.Train;
import com.springrest.demo.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @PostMapping
    public Train addTrain(@RequestBody Train train, @RequestHeader("API-Key") String apiKey) {
        if (!"your_secret_api_key".equals(apiKey)) {
            throw new RuntimeException("Invalid API key");
        }
        return trainService.addTrain(train);
    }

    @GetMapping("/availability")
    public List<Train> getAvailableTrains(@RequestParam String source, @RequestParam String destination) {
        return trainService.getAvailableTrains(source, destination);
    }
}