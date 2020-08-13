package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Referentstelling;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Referentstelling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferentstellingRepository extends JpaRepository<Referentstelling, Long> {
}
