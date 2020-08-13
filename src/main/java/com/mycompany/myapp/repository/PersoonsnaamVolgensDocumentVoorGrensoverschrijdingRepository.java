package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PersoonsnaamVolgensDocumentVoorGrensoverschrijding;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PersoonsnaamVolgensDocumentVoorGrensoverschrijding entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository extends JpaRepository<PersoonsnaamVolgensDocumentVoorGrensoverschrijding, Long> {
}
