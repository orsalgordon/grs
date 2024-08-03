package com.grs.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long giftId;

    @Column
    private Long eventId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String link;
    @Column(columnDefinition="bit default 1", nullable = false)
    private boolean available;
}
