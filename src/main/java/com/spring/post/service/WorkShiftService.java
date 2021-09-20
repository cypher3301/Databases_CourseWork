package com.spring.post.service;

import com.spring.post.entity.WorkShift;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface WorkShiftService {
    public List<WorkShift> getAllWorkShift();

    public WorkShift saveWorkShift(WorkShift employee);

    public WorkShift getWorkShiftById(int id);
    public boolean deleteWorkShiftById(int id);

    boolean isWorkShiftOpenedOnSpecialDay(String stringDate) throws ParseException;
}
