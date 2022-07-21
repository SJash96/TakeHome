package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modifiedData;
import com.example.demo.model.sampleData;
import com.example.demo.repository.dataRepository;

@Service
public class retrieveData {

    @Autowired
    private dataRepository dataRepository;
    
    public List<sampleData> getAllData() {
        // List<sampleData> sampleDatas = dataRepository.findAll();
        // for (sampleData sampleData : sampleDatas) {
        //     sampleData.getSpatialObj().getCentroid().within(g)
        // }
        return dataRepository.findAll();
    }

    public modifiedData getCentroidData(){
        List<sampleData> sampleDatas = dataRepository.getCentroidBased();
        modifiedData modifiedData = new modifiedData();
        modifiedData.setPopulation(0);
        modifiedData.setIncome(0);
        for (sampleData sampleData : sampleDatas) {
            modifiedData.setPopulation(modifiedData.getPopulation() + sampleData.getPopulation());
            modifiedData.setIncome(modifiedData.getIncome() + sampleData.getIncome());
        }
        modifiedData.setIncome(modifiedData.getIncome() / sampleDatas.size());
        return modifiedData;
    }
}
