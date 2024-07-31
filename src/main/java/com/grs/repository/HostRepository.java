package com.grs.repository;

import com.grs.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {

    Host findByEmail(String email);
}
