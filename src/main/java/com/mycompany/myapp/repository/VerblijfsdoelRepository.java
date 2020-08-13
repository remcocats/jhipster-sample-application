package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Verblijfsdoel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Verblijfsdoel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerblijfsdoelRepository extends JpaRepository<Verblijfsdoel, Long> {
}
