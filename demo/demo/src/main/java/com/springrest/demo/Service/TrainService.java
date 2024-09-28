package com.springrest.demo.Service;

import com.springrest.demo.Entity.Train;
import com.springrest.demo.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public Train addTrain(Train train) {
        train.setAvailableSeats(train.getTotalSeats());
        return trainRepository.save(train);
    }

    public List<Train> getAvailableTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}