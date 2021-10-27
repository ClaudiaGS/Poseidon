package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.interfaces.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    @Autowired
    RatingRepository ratingRepository;
    
    @Override
    public Iterable<Rating>getRatingList(){
        return ratingRepository.findAll();
    }
    @Override
    public Optional<Rating> getRating(Integer id){
        return ratingRepository.findById(id);
    }
    @Override
    public void deleteRating(Integer id){
        ratingRepository.deleteById(id);
    }
    @Override
    public void updateRating(Rating rating){
    ratingRepository.save(rating);
    }
    @Override
    public void createRating(Rating rating){
        ratingRepository.save(rating);
    }
}
