package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.modifiedData;
import com.example.demo.model.sampleData;
import com.example.demo.service.retrieveData;

@RestController()

public class mainController {

    @Autowired
    private retrieveData retrieveData;
    
    @GetMapping("/")
    public List<sampleData> viewAllData() {
        return retrieveData.getAllData();
    }

    @GetMapping("/Centroid")
    public List<modifiedData> viewCentroidBased(){
        return retrieveData.getCentroidData();
    }
}
