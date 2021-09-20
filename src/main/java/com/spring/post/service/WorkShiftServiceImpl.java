package com.spring.post.service;

import com.spring.post.entity.WorkShift;
import com.spring.post.repository.kotlin.WorkShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkShiftServiceImpl implements WorkShiftService {
    @Autowired
    private WorkShiftRepository workShiftRepository;
    @Override
    public List<WorkShift> getAllWorkShift() {
        return null;
    }

    @Override
    public WorkShift saveWorkShift(WorkShift employee) {
        return null;
    }

    @Override
    public WorkShift getWorkShiftById(int id) {
        return null;
    }

    @Override
    public boolean deleteWorkShiftById(int id) {
        return false;
    }

    @Override
    public boolean isWorkShiftOpenedOnSpecialDay(String stringDate) throws ParseException {
        Date dateObject;
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateObject = simpleDateFormat.parse(stringDate);
//        workShiftRepository;
    }
}
