package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    
    public Iterable<Rating>getRatingList(){
        return ratingRepository.findAll();
    }
    public Optional<Rating> getRating(Integer id){
        return ratingRepository.findById(id);
    }
    public void deleteRating(Integer id){
        ratingRepository.deleteById(id);
    }
    public void updateRating(Rating rating){
        Optional<Rating> existingRating=ratingRepository.findById(rating.getId());
        existingRating.equals(rating);
    }
    public void createRating(Rating rating){
        ratingRepository.save(rating);
    }
}
