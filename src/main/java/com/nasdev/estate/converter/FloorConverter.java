package com.nasdev.estate.converter;

import com.nasdev.estate.dto.FloorDto;
import com.nasdev.estate.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDto toFloorDto (Floor floor){
        return FloorDto.builder()
                .number(floor.getNumber())
                .build();
    }

    public Floor toFloor(FloorDto floorDto){
        return Floor.builder()
                .number(floorDto.getNumber())
                .build();
    }
}
