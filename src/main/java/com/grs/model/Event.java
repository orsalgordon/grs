package com.grs.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @Column
    private Long hostId;
    @Column
    private String eventName;
    @Column
    private String eventDescription;
    @Column
    private String eventType;
}
