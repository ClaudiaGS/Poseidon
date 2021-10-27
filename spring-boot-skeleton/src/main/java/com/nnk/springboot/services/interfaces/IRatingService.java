package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;

import java.util.Optional;

public interface IRatingService {
    public Iterable<Rating>getRatingList();
    public Optional<Rating> getRating(Integer id);
    public void deleteRating(Integer id);
    public void updateRating(Rating rating);
    public void createRating(Rating rating);
}
