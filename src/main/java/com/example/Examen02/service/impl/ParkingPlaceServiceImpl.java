package com.example.Examen02.service.impl;

import com.example.Examen02.dto.CreateParkingPlaceDto;
import com.example.Examen02.dto.ParkingPlaceDto;
import com.example.Examen02.entity.ParkingPlace;
import com.example.Examen02.enums.ParkingPlaceEnums;
import com.example.Examen02.enums.ParkingPlaceStatus;
import com.example.Examen02.repo.ParkingPlaceRepo;
import com.example.Examen02.service.ParkingPlaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
    private final ParkingPlaceRepo parkingPlaceRepo;
    public List<ParkingPlaceDto> findAll() {
        List<ParkingPlace> parkingPlaceEntities = parkingPlaceRepo.findAll();

        List<ParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
        for(ParkingPlace parkingPlace : parkingPlaceEntities){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .parkingLot(parkingPlace.getParkingLot())
                    .placeEnums(parkingPlace.getPlaceEnums())
                    .status(parkingPlace.getStatus())
                            .build();
            parkingPlaceDtos.add(parkingPlaceDto);
        }
        return parkingPlaceDtos;
    }

    @Override
    public ParkingPlaceDto findParkingLot(Long id) {
        ParkingPlace parkingPlace = parkingPlaceRepo.findById(id).orElseThrow(()->new EntityNotFoundException(""));

        return ParkingPlaceDto.builder()
                .id(parkingPlace.getId())
                .parkingLot(parkingPlace.getParkingLot())
                .placeEnums(parkingPlace.getPlaceEnums())
                .status(parkingPlace.getStatus())
                .build();
    }

    @Override
    public List<ParkingPlaceDto> findParkingLotType(ParkingPlaceEnums placeEnums) {
        List<ParkingPlace> parkingPlaceEntities = parkingPlaceRepo.findParkingPlaceByType(placeEnums);

        List<ParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
        for(ParkingPlace parkingPlace : parkingPlaceEntities){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .parkingLot(parkingPlace.getParkingLot())
                    .placeEnums(parkingPlace.getPlaceEnums())
                    .status(parkingPlace.getStatus())
                    .build();
            parkingPlaceDtos.add(parkingPlaceDto);
        }
        return parkingPlaceDtos;
    }

    @Override
    public List<ParkingPlaceDto> findNotReservedLot() {
        List<ParkingPlace> parkingPlaces = parkingPlaceRepo.findAllNotReserved();

        List<ParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
        for(ParkingPlace parkingPlace : parkingPlaces){
            ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .parkingLot(parkingPlace.getParkingLot())
                    .placeEnums(parkingPlace.getPlaceEnums())
                    .status(parkingPlace.getStatus())
                    .build();
            parkingPlaceDtos.add(parkingPlaceDto);
        }
        return parkingPlaceDtos;
    }

    @Override
    public CreateParkingPlaceDto create(CreateParkingPlaceDto parkingPlaceToCreate) throws RuntimeException {
        try{ ParkingPlace parkingPlaceEntity = ParkingPlace.builder()
                .parkingLot(parkingPlaceToCreate.getSpotNumber())
                .placeEnums(parkingPlaceToCreate.getParkingPlaceEnums())
                .status(ParkingPlaceStatus.EMPTY)
                .build();
            parkingPlaceRepo.save(parkingPlaceEntity);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return parkingPlaceToCreate;
    }



    @Override
    public void deleteById(Long id) {
        parkingPlaceRepo.deleteById(id);
    }
}
