package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Vreemdeling;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Vreemdeling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VreemdelingRepository extends JpaRepository<Vreemdeling, Long> {
}
