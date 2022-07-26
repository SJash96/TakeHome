package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.modelData;

@Repository
public interface dataRepository extends JpaRepository<modelData, String> {
    
    @Query(value = "SELECT * FROM dfw_demo WHERE ST_Within(ST_Centroid(\"SpatialObj\"), CAST(ST_Buffer(CAST(ST_MakePoint(-96.781508, 33.045352)AS geography), 2000)AS geometry));", nativeQuery = true)
    List<modelData> getCentroidBased();

    @Query(value = "SELECT \"Key\", income, population, ST_Centroid(\"SpatialObj\") as \"SpatialObj\" FROM dfw_demo WHERE ST_Within(ST_Centroid(\"SpatialObj\"), CAST(ST_Buffer(CAST(ST_MakePoint(-96.781508, 33.045352)AS geography), 2000)AS geometry));", nativeQuery = true)
    List<modelData> getCentroidDotes();
}
