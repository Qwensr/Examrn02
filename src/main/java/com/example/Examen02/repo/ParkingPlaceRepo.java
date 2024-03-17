package com.example.Examen02.repo;


import com.example.Examen02.entity.ParkingPlace;
import com.example.Examen02.enums.ParkingPlaceEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingPlaceRepo extends JpaRepository<ParkingPlace, Long> {
    @Query("select p from ParkingPlace p " +
            "where p.status = com.example.Examen02.enums.ParkingPlaceStatus.EMPTY")
    List<ParkingPlace> findAllNotReserved();

    @Query("select p from ParkingPlace p " +
            "where p.placeEnums = :type ")
    List <ParkingPlace> findParkingPlaceByType(@Param("type") ParkingPlaceEnums parkingPlaceEnums);
    @Query("select p from  ParkingPlace p " +
            "where p.status = com.example.Examen02.enums.ParkingPlaceStatus.EMPTY and p.id = :id")
    ParkingPlace findAvailablePlaceById(@Param("id")Long id);
}
