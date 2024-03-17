package com.example.Examen02.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParkingPlaceEnums {
    STANDART("standart parking place"),
    FOR_DISABLED("for disabled people parking place"),
    FOR_FAMILY("for family with children parking place"),
    FOR_ELECTROVEHICLES("for EV parking place")
    ;

    String DESCRIPTION;
}