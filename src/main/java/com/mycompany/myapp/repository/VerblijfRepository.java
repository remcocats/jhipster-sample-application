package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Verblijf;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Verblijf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerblijfRepository extends JpaRepository<Verblijf, Long> {
}
