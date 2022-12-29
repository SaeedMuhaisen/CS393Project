package com.SaeedAndEmre.CS393Project.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Services {
    //TODO: do we must make id long?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "services")
    private List<Reservation> reservations = new ArrayList<>();

}
