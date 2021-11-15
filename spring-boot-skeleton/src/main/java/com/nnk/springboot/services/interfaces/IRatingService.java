package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.Rating;

import java.util.Optional;

public interface IRatingService {
    
    /**
     *
     * @return Iterable<Rating>
     */
    public Iterable<Rating>getRatingList();
    /**
     *
     * @param id
     * @return Optional<Rating>
     */
    public Optional<Rating> getRating(Integer id);
    
    /**
     *
     * @param id
     * @return void
     */
    public void deleteRating(Integer id);
    
    /**
     *
     * @param rating
     * @return void
     */
    public void updateRating(Rating rating);
    
    /**
     *
     * @param rating
     * @return void
     */
    public void createRating(Rating rating);
}
