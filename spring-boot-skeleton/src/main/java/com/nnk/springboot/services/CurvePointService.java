package com.nnk.springboot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.interfaces.ICurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurvePointService implements ICurvePointService {
    @Autowired
    CurvePointRepository curvePointRepository;
    
    private static final Logger logger = LogManager.getLogger("CurvePointService");
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ICurvePointService#getCurvePointList()
     */
    @Override
    public Iterable<CurvePoint> getCurvePointList() {
        Iterable<CurvePoint>curvePointIterable;
        curvePointIterable=curvePointRepository.findAll();
        logger.info("CurvePoint List:"+asJsonString(curvePointIterable));
        return curvePointIterable ;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ICurvePointService#getCurvePoint(Integer)
     */
    @Override
    public Optional<CurvePoint> getCurvePoint(Integer id) {
        Optional<CurvePoint>curvePointOptional;
        curvePointOptional=curvePointRepository.findById(id);
        logger.info("CurvePoint for id="+id+" is: "+asJsonString(curvePointOptional.get()));
        return curvePointOptional;
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ICurvePointService#deleteCurvePoint(Integer)
     */
    @Override
    public void deleteCurvePoint(Integer id) {
        logger.info("Deleting curve point with id "+id);
        curvePointRepository.deleteById(id);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ICurvePointService#updateCurvePoint(CurvePoint)
     */
    @Override
    public void updateCurvePoint(CurvePoint curvePoint) {
        logger.info("Updating curve point "+curvePoint);
        curvePointRepository.save(curvePoint);
    }
    
    /**
     * (non-javadoc)
     *
     * @see com.nnk.springboot.services.interfaces.ICurvePointService#createCurvePoint(CurvePoint)
     */
    @Override
    public void createCurvePoint(CurvePoint curvePoint) {
        logger.info("Creating curve point "+curvePoint);
        curvePointRepository.save(curvePoint);
    }
    
    /**
     *
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
