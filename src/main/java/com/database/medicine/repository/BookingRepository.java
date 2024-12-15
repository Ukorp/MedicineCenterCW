package com.database.medicine.repository;

import com.database.medicine.entity.Booking;
import com.database.medicine.entity.Doctors;
import com.database.medicine.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByDoctorId(Doctors doctorId);

    @Query("select count(b) from Booking b")
    Integer countAll();

    List<Booking> findByUserId(User userId);

    @Query("select b from Booking b where (?1 = b.userId and b.date < current timestamp) order by b.date desc")
    List<Booking> findBookingsByUserIdArchive(@RequestParam("userId") User userId);

    @Query("select b from Booking b where (?1 = b.userId and b.date >= current timestamp) order by b.date asc")
    List<Booking> findBookingsByUserIdRelevant(@RequestParam("userId") User userId);


    List<Booking> findBookingsByUserIdAndDateBefore(User userId, LocalDateTime date);

}