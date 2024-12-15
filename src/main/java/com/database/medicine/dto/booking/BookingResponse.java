package com.database.medicine.dto.booking;

import com.database.medicine.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Integer id;
    private String userName;
    private String doctorName;
    private String serviceName;
    private Integer price;
    private LocalDateTime date;

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.userName = booking.getUserId().getFirstName() + " " + booking.getUserId().getLastName();
        this.doctorName = booking.getDoctorId().getName();
        this.serviceName = booking.getServiceId().getName();
        this.date = booking.getDate();
        this.price = booking.getServiceId().getPrice();
    }
}
