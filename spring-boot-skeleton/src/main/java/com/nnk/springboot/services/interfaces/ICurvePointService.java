package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.domain.CurvePoint;

import java.util.Optional;

public interface ICurvePointService {
    public Iterable<CurvePoint>getCurvePointList();
    public Optional<CurvePoint> getCurvePoint(Integer id);
    public void deleteCurvePoint(Integer id);
    public void updateCurvePoint(CurvePoint curvePoint);
    public void createCurvePoint(CurvePoint curvePoint);
}
