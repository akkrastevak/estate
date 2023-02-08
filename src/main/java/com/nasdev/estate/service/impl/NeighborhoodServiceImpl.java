package com.nasdev.estate.service.impl;

import com.nasdev.estate.exeption.ResourceNotFoundException;
import com.nasdev.estate.model.Neighborhood;
import com.nasdev.estate.repository.NeighborhoodRepository;
import com.nasdev.estate.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        return neighborhoodRepository.save(neighborhood);
    }

    @Override
    public Neighborhood findByName(String name) {
        return neighborhoodRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException (String.format("Neighborhood with that name: %s doesn't exists", name)));
    }

    @Override
    public Set<Neighborhood> findAll() {
        return new HashSet<>(neighborhoodRepository.findAll());
    }
}
