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
@Table(name = "Location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private int locationId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "host_id", referencedColumnName = "host_id")
    private Host host;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Package> packageList;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ServiceBirthday> serviceBirthdays;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "name", length = 250)
    private String name;
}
