package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBirthday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_id")
    private int serviceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @OneToMany(mappedBy = "serviceBirthday", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ServiceDetail> serviceDetailList;

    @OneToMany(mappedBy = "serviceBirthday", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Feedback> feedbackList;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "price")
    private float price;

    @Column(name = "description", length = 250)
    private String description;
}
