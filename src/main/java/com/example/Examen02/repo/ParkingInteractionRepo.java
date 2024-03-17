package com.example.Examen02.repo;


import com.example.Examen02.entity.ParkingInteraction;
import com.example.Examen02.entity.ParkingPlace;
import com.example.Examen02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingInteractionRepo extends JpaRepository<ParkingInteraction, Long> {
    @Modifying
    @Query("delete from ParkingInteraction ipp " +
            "where ipp.user = :user")
    void deleteUser(@Param("user") User user);

    @Modifying
    @Query("delete from ParkingInteraction ipp " +
            "where ipp.parkingPlace = :parkingPlace")
    void deleteParkingPlace(@Param("parkingPlace") ParkingPlace parkingPlace);
}
