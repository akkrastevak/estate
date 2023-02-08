package com.nasdev.estate.service;

import com.nasdev.estate.model.Neighborhood;

import java.util.Set;

public interface NeighborhoodService {

    Neighborhood save (Neighborhood neighborhood);
    Neighborhood findByName (String name);
    Set<Neighborhood> findAll ();




}
