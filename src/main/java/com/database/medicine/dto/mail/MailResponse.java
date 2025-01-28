package com.database.medicine.dto.mail;

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
public class MailResponse {
    private Integer id;
    private String userName;
    private String doctorName;
    private String serviceName;
    private Integer price;
    private String branchName;
    private String branchAddress;
    private LocalDateTime date;
    private String email;


    public MailResponse(Booking booking, String email) {
        this.id = booking.getId();
        this.userName = booking.getUserId().getFirstName() + " " + booking.getUserId().getLastName();
        this.doctorName = booking.getDoctorId().getName();
        this.serviceName = booking.getServiceId().getName();
        this.date = booking.getDate();
        this.price = booking.getServiceId().getPrice();
        this.branchName = booking.getDoctorId().getBranchId().getName();
        this.branchAddress = booking.getDoctorId().getBranchId().getAddress();
        this.email = email;
    }
}
