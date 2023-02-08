package com.nasdev.estate.service.impl;

import com.nasdev.estate.exeption.DuplicateRecordException;
import com.nasdev.estate.exeption.ResourceNotFoundException;
import com.nasdev.estate.model.Floor;
import com.nasdev.estate.repository.FloorRepository;
import com.nasdev.estate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Floor save(Floor floor) {
        try {
            return floorRepository.save(floor);
        }catch (DataIntegrityViolationException e){
            throw new DuplicateRecordException(String.format("Floor with number %d already exists.", floor.getNumber()));
        }
    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Floor with %d does not exists.", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Floor with %d does not exists.", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updatedFloor = Floor.builder()
                .id(foundFloor.getId())
                .number(floor.getNumber())
                .build();
        return save(updatedFloor);
    }

    @Override
    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public Set<Floor> findAll() {
        return new HashSet<>(floorRepository.findAll());
    }
}
