package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Package")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id")
    private int packageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @OneToMany(mappedBy = "cartPackage", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Cart> cartList;

    @OneToMany(mappedBy = "servicePackage", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonManagedReference
    private List<ServiceOfPackage> serviceOfPackageList;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "price")
    private float price;

    @Column(name = "description", length = 250)
    private String description;
}
