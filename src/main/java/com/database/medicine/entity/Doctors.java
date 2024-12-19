package com.database.medicine.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ServicesDoctors> servicesDoctors;

    @JsonIgnore
    @OneToMany(mappedBy = "doctorId")
    private Set<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branches branchId;

}
