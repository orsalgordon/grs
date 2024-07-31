package com.grs.repository;

import com.grs.model.Event;
import com.grs.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByHostId(Long hostId);
}
