package com.database.medicine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer price;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId")
    private Set<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceId")
    private Set<ServicesDoctors> servicesDoctors;

}
