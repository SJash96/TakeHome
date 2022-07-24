package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.modifiedData;
import com.example.demo.dto.frontend.frontendData;
import com.example.demo.model.modelData;
import com.example.demo.repository.dataRepository;
import com.example.demo.service.retrieveData;

@RestController()

public class mainController {

    @Autowired
    private retrieveData retrieveData;

    @Autowired
    private dataRepository dataRepository;
    
    @GetMapping("/AllData")
    public List<modelData> viewAllData() {
        return retrieveData.getAllData();
    }

    @GetMapping("/CentroidData")
    public List<modelData> viewCentroidData() {
        return retrieveData.getCentroidData();
    }

    @GetMapping("/CentroidCalculated")
    public List<modifiedData> viewCentroidBased(){
        return retrieveData.getCentroidDataCalculated();
    }

    // For Front End
    @GetMapping("/AllDataFront")
    public List<frontendData> viewAllDataFront() {
        return retrieveData.getDataFront(dataRepository.findAll());
    }

    @GetMapping("/CentroidDataFront")
    public List<frontendData> viewCentroidDataFront() {
        return retrieveData.getDataFront(dataRepository.getCentroidBased());
    }

    @GetMapping("/CentroidDataDotsFront")
    public List<frontendData> viewCentroidDotsFront() {
        return retrieveData.getDataFront(dataRepository.getCentroidDotes());
    }
}
