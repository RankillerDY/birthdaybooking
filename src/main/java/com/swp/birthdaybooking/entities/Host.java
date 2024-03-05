package com.swp.birthdaybooking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "Hosts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "host_id")
    private int hostId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Account account;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Location> locationList;

    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ScheduleDetail> scheduleDetailList;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "phone", length = 250, nullable = false)
    private String phone;

    public Host(int hostId) {
        this.hostId = hostId;
    }
}

