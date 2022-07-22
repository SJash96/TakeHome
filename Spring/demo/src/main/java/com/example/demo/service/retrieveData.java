package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.modifiedData;
import com.example.demo.model.sampleData;
import com.example.demo.repository.dataRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

@Service
public class retrieveData {

    Logger logger = LoggerFactory.getLogger(retrieveData.class);

    @Autowired
    private dataRepository dataRepository;

    public List<sampleData> getAllData() {
        return dataRepository.findAll();
    }

    public List<sampleData> getCentroidData() {
        return dataRepository.getCentroidBased();
    }

    public List<modifiedData> getCentroidDataCalculated() {
        List<sampleData> sampleDatas = dataRepository.getCentroidBased();
        List<modifiedData> modifiedDatasList = new ArrayList<>();
        modifiedData modifiedData = new modifiedData();
        modifiedData.setTotalPopulation(0);
        modifiedData.setTotalIncome(0);
        for (sampleData sampleData : sampleDatas) {
            modifiedData.setTotalPopulation(modifiedData.getTotalPopulation() + sampleData.getPopulation());
            modifiedData.setTotalIncome(modifiedData.getTotalIncome() + sampleData.getIncome());
        }
        modifiedData.setTotalIncome(modifiedData.getTotalIncome() / sampleDatas.size());
        modifiedData.setFrom("Database Query");

        List<sampleData> sampleDatas2 = dataRepository.findAll();
        modifiedData modifiedData2 = new modifiedData();
        modifiedData2.setTotalPopulation(0);
        modifiedData2.setTotalIncome(0);
        for (sampleData sampleData : sampleDatas2) {
            Geometry point = new GeometryFactory().createPoint(new Coordinate(-96.781508, 33.045352)).buffer(0.02);
            boolean bool = sampleData.getSpatialObj().getCentroid().within(point);
            if (bool) {
                modifiedData2.setTotalPopulation(modifiedData2.getTotalPopulation() + sampleData.getPopulation());
                modifiedData2.setTotalIncome(modifiedData2.getTotalIncome() + sampleData.getIncome());
            }
        }
        modifiedData2.setTotalIncome(modifiedData2.getTotalIncome() / sampleDatas2.size());
        modifiedData2.setFrom("Java");

        modifiedDatasList.add(modifiedData);
        modifiedDatasList.add(modifiedData2);
        return modifiedDatasList;
    }

}
