package com.example.Examen02.service;

import com.example.Examen02.dto.CreateParkingInteractionDto;

public interface ParkingInteractionService {

    String reservePlace(CreateParkingInteractionDto reservePlace) throws RuntimeException;

    String confirmReservationByOrderId(Long orderId);

    String takePlace(CreateParkingInteractionDto takePlace);

    String releaseParkingPlace(Long orderId);
}
