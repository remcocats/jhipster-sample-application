package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Bekostiging;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bekostiging entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BekostigingRepository extends JpaRepository<Bekostiging, Long> {
}
