package com.nasdev.estate.contoller;

import com.nasdev.estate.converter.NeighborhoodConverter;
import com.nasdev.estate.dto.NeighborhoodDto;
import com.nasdev.estate.model.Neighborhood;
import com.nasdev.estate.service.NeighborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final NeighborhoodConverter neighborhoodConverter;

    public NeighborhoodController(NeighborhoodService neighborhoodService, NeighborhoodConverter neighborhoodConverter) {
        this.neighborhoodService = neighborhoodService;
        this.neighborhoodConverter = neighborhoodConverter;
    }

    @GetMapping
    public ResponseEntity<Set<NeighborhoodDto>> findAll(){
        return ResponseEntity.ok(neighborhoodService.findAll()
                .stream()
                .map(neighborhoodConverter::toNeighborhoodDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<NeighborhoodDto> findByName (@PathVariable String name){
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto(neighborhoodService.findByName(name)));
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDto> save (@RequestBody NeighborhoodDto neighborhoodDto){
        Neighborhood neighborhood = neighborhoodConverter.toNeighborhood(neighborhoodDto);
        Neighborhood savedNeighborhood = neighborhoodService.save(neighborhood);
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto(savedNeighborhood));
        //return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto(neighborhoodService.save(neighborhoodConverter.toNeighborhood(neighborhoodDto))));
    }
}
