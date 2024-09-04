package com.gsl.glasgowsocialleague.infra.gateway;

import com.gsl.glasgowsocialleague.core.model.session.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionGateway extends JpaRepository<Session, Integer> {
    Page<Session> findBySportId(Integer sportId, Pageable pageable);
    Page<Session> findAll(Pageable pageable); // Provided by JpaRepository
}
