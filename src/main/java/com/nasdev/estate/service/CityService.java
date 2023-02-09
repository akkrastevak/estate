package com.nasdev.estate.service;

import com.nasdev.estate.model.City;

import java.util.Set;

public interface CityService {

    City findById (Long id);

    City save (City city);

    Set<City> findAll();

    City update (City city, Long id);

    void detachCityNeighborhood(Long id, Set<Long> neighborhoodIds);
}
