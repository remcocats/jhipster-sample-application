package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ErkendReferent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ErkendReferent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ErkendReferentRepository extends JpaRepository<ErkendReferent, Long> {
}
