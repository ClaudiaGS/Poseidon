package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.CurvePoint;

import java.util.Optional;

public interface ICurvePointService {
    /**
     *
     * @return Iterable<CurvePoint>
     */
    public Iterable<CurvePoint>getCurvePointList();
    
    /**
     *
     * @param id
     * @return Optional<CurvePoint>
     */
    public Optional<CurvePoint> getCurvePoint(Integer id);
    
    /**
     *
     * @param id
     * @return void
     */
    public void deleteCurvePoint(Integer id);
    
    /**
     *
     * @param curvePoint
     * @return void
     */
    public void updateCurvePoint(CurvePoint curvePoint);
    
    /**
     *
     * @param curvePoint
     * @return void
     */
    public void createCurvePoint(CurvePoint curvePoint);
}
