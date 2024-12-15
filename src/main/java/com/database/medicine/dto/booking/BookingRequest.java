package com.database.medicine.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Integer userId;
    private Integer doctorId;
    private Integer serviceId;
    private LocalDateTime date;
}
