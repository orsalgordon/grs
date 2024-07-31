package com.grs.repository;

import com.grs.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {

    List<Gift> findAllByEventId(Long eventId);
}
