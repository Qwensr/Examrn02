package com.example.Examen02.entity;


import com.example.Examen02.enums.ParkingPlaceEnums;
import com.example.Examen02.enums.ParkingPlaceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parkingLot;
    private ParkingPlaceEnums placeEnums;
    private ParkingPlaceStatus status;
}
