package com.nasdev.estate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true,nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "cities_neighborhoods",
    joinColumns = @JoinColumn(name = "city_id"),
    inverseJoinColumns = @JoinColumn(name = "neighborhood_id"))
    private Set<Neighborhood> neighborhoods;
}
