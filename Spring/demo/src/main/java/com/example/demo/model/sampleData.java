package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

import lombok.Data;

@Data
@Entity
@Table(name = "dfw_demo")
public class sampleData {
    
    @Id
    @Column(name = "\"Key\"")
    private String Key;
    @Column(name = "income")
    private Integer income;
    @Column(name = "population")
    private Integer population;
    @Column(name = "\"SpatialObj\"")
    private Geometry SpatialObj;
}
