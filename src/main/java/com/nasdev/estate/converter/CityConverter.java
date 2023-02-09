package com.nasdev.estate.converter;

import com.nasdev.estate.dto.CityDto;
import com.nasdev.estate.model.City;
import com.nasdev.estate.model.Neighborhood;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CityConverter {

    public CityDto toCityDto(City city){

      //  Set<Long> neighborhoodIds = new HashSet<>();
      //  for(Neighborhood neighborhood: city.getNeighborhoods()){
      //      neighborhoodIds.add(neighborhood.getId());
        //  }
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .neighborhoodIds(city.getNeighborhoods()
                        .stream()
                        .map(Neighborhood::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public City toCity(CityDto cityDto){

        Set<Neighborhood> neighborhoods = new HashSet<>();
        for (Long neighborhoodId : cityDto.getNeighborhoodIds()){
            neighborhoods.add(Neighborhood.builder().id(neighborhoodId).build());
        }
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .neighborhoods(cityDto.getNeighborhoodIds()
                        .stream()
                        .map((NeighborhoodId) -> Neighborhood.builder()
                                .id(NeighborhoodId)
                                .build()).collect(Collectors.toSet()))
                .build();
    }
}
