package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Toetsresultaat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Toetsresultaat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToetsresultaatRepository extends JpaRepository<Toetsresultaat, Long> {
}
