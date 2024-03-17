package com.example.Examen02.dto;

import com.example.Examen02.enums.ParkingPlaceEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateParkingPlaceDto {
    private Long id;
    private String spotNumber;
    private ParkingPlaceEnums parkingPlaceEnums;
}
