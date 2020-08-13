package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Nationaliteit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nationaliteit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NationaliteitRepository extends JpaRepository<Nationaliteit, Long> {
}
