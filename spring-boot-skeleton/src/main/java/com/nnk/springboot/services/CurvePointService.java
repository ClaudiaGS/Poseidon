package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurvePointService {
    @Autowired
    CurvePointRepository curvePointRepository;
    
    public Iterable<CurvePoint>getCurvePointList(){
        return curvePointRepository.findAll();
    }
    public Optional<CurvePoint> getCurvePoint(Integer id){
        return curvePointRepository.findById(id);
    }
    public void deleteCurvePoint(Integer id){
        curvePointRepository.deleteById(id);
    }
    public void updateCurvePoint(CurvePoint curvePoint){
        Optional<CurvePoint> existingCurvePoint=curvePointRepository.findById(curvePoint.getId());
        existingCurvePoint.equals(curvePoint);
    }
    public void createCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }
}
