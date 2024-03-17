package com.example.Examen02.service.impl;

import com.example.Examen02.dto.CreateParkingInteractionDto;
import com.example.Examen02.entity.ParkingInteraction;
import com.example.Examen02.entity.ParkingPlace;
import com.example.Examen02.enums.ParkingPlaceStatus;
import com.example.Examen02.repo.ParkingInteractionRepo;
import com.example.Examen02.repo.ParkingPlaceRepo;
import com.example.Examen02.repo.UserRepo;
import com.example.Examen02.service.ParkingInteractionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkingInteractionServiceImpl implements ParkingInteractionService {
    private final ParkingInteractionRepo parkingInteractionRepo;
    private final UserRepo userRepo;
    private final ParkingPlaceRepo parkingPlaceRepo;

    @Override
    public String reservePlace(CreateParkingInteractionDto reservePlace) throws RuntimeException{
        Long orderId;
        if (userRepo.findById(reservePlace.getParkingPlace().getId()).isPresent() &&
                parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId()) != null) {

            try {
                ParkingInteraction reserve = ParkingInteraction.builder()
                        .parkingPlace(reservePlace.getParkingPlace())
                        .user(reservePlace.getUser())
                        .build();

                orderId = parkingInteractionRepo.save(reserve).getId();
            } catch (Exception e) {
                throw new RuntimeException("Some thing went wrong!");
            }
            ParkingPlace reservedParkingPlace =
                    parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId());

            reservedParkingPlace.setStatus(ParkingPlaceStatus.RESERVED);
            parkingPlaceRepo.save(reservedParkingPlace);
            return "Your have successfully reserved a slot! Your order id: " + orderId;
        } else throw new RuntimeException("Some thing went wrong!");
    }

    @Override
    public String confirmReservationByOrderId(Long orderId) throws EntityNotFoundException{
        ParkingInteraction parkingInteraction = parkingInteractionRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id - " + orderId + " is not found"));

        ParkingPlace placeEntity = parkingPlaceRepo.findById(parkingInteraction.getParkingPlace().getId())
                .orElseThrow(()->new EntityNotFoundException("Place with id - " + parkingInteraction.getParkingPlace().getId() + " is not available"));
        placeEntity.setStatus(ParkingPlaceStatus.ENGAGED);
        parkingPlaceRepo.save(placeEntity);

        return "Your reservation has been successfully confirmed!";
    }


    @Override
    public String takePlace(CreateParkingInteractionDto takePlace) throws RuntimeException{
        Long orderId;
        if (userRepo.findById(takePlace.getUser().getId()).isPresent() &&
                parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId()) != null) {

            try {
                ParkingInteraction reserve = ParkingInteraction.builder()
                        .parkingPlace(takePlace.getParkingPlace())
                        .user(takePlace.getUser())
                        .build();

                orderId = parkingInteractionRepo.save(reserve).getId();
            } catch (Exception e) {
                throw new RuntimeException("Some thing went wrong");
            }
            ParkingPlace takeParkingPlace =
                    parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId());

            takeParkingPlace.setStatus(ParkingPlaceStatus.ENGAGED);
            parkingPlaceRepo.save(takeParkingPlace);

            return "Your interaction is successful. Remember id for Releasing Parking place " +
                    "ID: " + orderId;
        } else throw new RuntimeException("Some thing went wrong!");
    }

    @Override
    public String releaseParkingPlace(Long orderId) throws EntityNotFoundException{
        ParkingInteraction entity = parkingInteractionRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id - " + orderId + " is not found"));
        ParkingPlace parkingPlace = parkingPlaceRepo.findById(entity.getParkingPlace().getId())
                .orElseThrow(() -> new EntityNotFoundException("Place with id - " + orderId + " is not found"));

        parkingPlace.setStatus(ParkingPlaceStatus.EMPTY);
        parkingPlaceRepo.save(parkingPlace);

        return "You successfully released the parking place!";
    }

}
