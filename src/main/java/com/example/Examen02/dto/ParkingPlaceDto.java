package com.example.Examen02.dto;


import com.example.Examen02.enums.ParkingPlaceEnums;
import com.example.Examen02.enums.ParkingPlaceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingPlaceDto {
    private Long id;
    private String parkingLot;
    private ParkingPlaceEnums placeEnums;
    private ParkingPlaceStatus status;
}
