package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.interfaces.IRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    @Autowired
    RatingRepository ratingRepository;
    
    private static final Logger logger = LogManager.getLogger("RatingService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRatingService#getRatingList()
     */
    @Override
    public Iterable<Rating> getRatingList() {
        Iterable<Rating> ratingIterable;
        ratingIterable = ratingRepository.findAll();
        logger.info("Rating list is " + asJsonString(ratingIterable));
        return ratingIterable;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRatingService#getRating(Integer)
     */
    @Override
    public Optional<Rating> getRating(Integer id) {
        Optional<Rating> ratingOptional;
        ratingOptional = ratingRepository.findById(id);
        logger.info("Rating for id=" + id + " is:" + asJsonString(ratingOptional.get()));
        return ratingOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRatingService#deleteRating(Integer)
     */
    @Override
    public void deleteRating(Integer id) {
        logger.info("Deleting rating with id:" + id);
        ratingRepository.deleteById(id);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRatingService#updateRating(Rating)
     */
    @Override
    public void updateRating(Rating rating) {
        logger.info("Updating rating " + rating);
        ratingRepository.save(rating);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.IRatingService#createRating(Rating)
     */
    @Override
    public void createRating(Rating rating) {
        logger.info("Creating rating "+rating);
        ratingRepository.save(rating);
    }
    
    /**
     * @param obj
     * @return String
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
