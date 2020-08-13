package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Erkenning;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Erkenning entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ErkenningRepository extends JpaRepository<Erkenning, Long> {
}
