package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.interfaces.ICurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurvePointService implements ICurvePointService {
    @Autowired
    CurvePointRepository curvePointRepository;
    
    @Override
    public Iterable<CurvePoint> getCurvePointList() {
        return curvePointRepository.findAll();
    }
    
    @Override
    public Optional<CurvePoint> getCurvePoint(Integer id) {
        return curvePointRepository.findById(id);
    }
    
    @Override
    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
    
    @Override
    public void updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }
    
    @Override
    public void createCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }
}
