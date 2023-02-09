package com.nasdev.estate.service.impl;

import com.nasdev.estate.exeption.ResourceNotFoundException;
import com.nasdev.estate.model.City;
import com.nasdev.estate.model.Neighborhood;
import com.nasdev.estate.repository.CityRepository;
import com.nasdev.estate.service.CityService;
import com.nasdev.estate.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final NeighborhoodService neighborhoodService;

    public CityServiceImpl(CityRepository cityRepository, NeighborhoodService neighborhoodService) {
        this.cityRepository = cityRepository;
        this.neighborhoodService = neighborhoodService;
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("City with id %d can not be found.", id)));
    }

    @Override
    public City save(City city) {

        Set<Neighborhood> neighborhoods = new HashSet<>();
        for(Neighborhood neighborhood : city.getNeighborhoods()){
            Neighborhood foundNeighborhood = neighborhoodService.findById(neighborhood.getId());
            neighborhoods.add(foundNeighborhood);
        }
        return cityRepository.save(City.builder()
                .name(city.getName())
                .neighborhoods(neighborhoods)
                .build());
    }

    @Override
    public Set<City> findAll() {
        return new HashSet<>(cityRepository.findAll());
    }

    @Override
    public City update(City city, Long id) {
        City foundCity = findById(id);
        City cityToUpdate = City.builder()
                .id(foundCity.getId())
                .name(city.getName())
                .neighborhoods(city.getNeighborhoods())
                .build();

        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void detachCityNeighborhood(Long id, Set<Long> neighborhoodIds) {
        City foundCity = findById(id);
        foundCity.getNeighborhoods().removeIf(neighborhood -> neighborhoodIds.contains(neighborhood.getId()));
    }
}
