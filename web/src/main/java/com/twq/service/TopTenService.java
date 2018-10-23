package com.twq.service;

import com.twq.model.TopTenMovie;

import java.util.List;

public interface TopTenService {

    public List<TopTenMovie> getTopTenMovie(int year);

}
