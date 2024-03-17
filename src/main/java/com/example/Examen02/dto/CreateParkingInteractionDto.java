package com.example.Examen02.dto;

import com.example.Examen02.entity.ParkingPlace;
import com.example.Examen02.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateParkingInteractionDto {
    private User user;
    private ParkingPlace parkingPlace;
}
