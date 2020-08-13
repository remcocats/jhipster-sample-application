package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Kenniswerk;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Kenniswerk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KenniswerkRepository extends JpaRepository<Kenniswerk, Long> {
}
