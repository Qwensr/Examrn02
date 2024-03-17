package com.example.Examen02.service;

import com.example.Examen02.dto.CreateParkingPlaceDto;
import com.example.Examen02.dto.ParkingPlaceDto;
import com.example.Examen02.enums.ParkingPlaceEnums;

import java.util.List;

public interface ParkingPlaceService {
    List<ParkingPlaceDto> findAll();
    ParkingPlaceDto findParkingLot(Long id);

    List<ParkingPlaceDto> findParkingLotType(ParkingPlaceEnums placeEnums);

    List<ParkingPlaceDto> findNotReservedLot();

    CreateParkingPlaceDto create(CreateParkingPlaceDto parkingPlaceToCreate) throws RuntimeException;

    void deleteById(Long id);

}
