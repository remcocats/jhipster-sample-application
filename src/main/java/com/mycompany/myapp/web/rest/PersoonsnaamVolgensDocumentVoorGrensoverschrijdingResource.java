package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PersoonsnaamVolgensDocumentVoorGrensoverschrijding;
import com.mycompany.myapp.repository.PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PersoonsnaamVolgensDocumentVoorGrensoverschrijding}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersoonsnaamVolgensDocumentVoorGrensoverschrijdingResource {

    private final Logger log = LoggerFactory.getLogger(PersoonsnaamVolgensDocumentVoorGrensoverschrijdingResource.class);

    private static final String ENTITY_NAME = "persoonsnaamVolgensDocumentVoorGrensoverschrijding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository;

    public PersoonsnaamVolgensDocumentVoorGrensoverschrijdingResource(PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository) {
        this.persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository;
    }

    /**
     * {@code POST  /persoonsnaam-volgens-document-voor-grensoverschrijdings} : Create a new persoonsnaamVolgensDocumentVoorGrensoverschrijding.
     *
     * @param persoonsnaamVolgensDocumentVoorGrensoverschrijding the persoonsnaamVolgensDocumentVoorGrensoverschrijding to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new persoonsnaamVolgensDocumentVoorGrensoverschrijding, or with status {@code 400 (Bad Request)} if the persoonsnaamVolgensDocumentVoorGrensoverschrijding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/persoonsnaam-volgens-document-voor-grensoverschrijdings")
    public ResponseEntity<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> createPersoonsnaamVolgensDocumentVoorGrensoverschrijding(@RequestBody PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding) throws URISyntaxException {
        log.debug("REST request to save PersoonsnaamVolgensDocumentVoorGrensoverschrijding : {}", persoonsnaamVolgensDocumentVoorGrensoverschrijding);
        if (persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId() != null) {
            throw new BadRequestAlertException("A new persoonsnaamVolgensDocumentVoorGrensoverschrijding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding result = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.save(persoonsnaamVolgensDocumentVoorGrensoverschrijding);
        return ResponseEntity.created(new URI("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /persoonsnaam-volgens-document-voor-grensoverschrijdings} : Updates an existing persoonsnaamVolgensDocumentVoorGrensoverschrijding.
     *
     * @param persoonsnaamVolgensDocumentVoorGrensoverschrijding the persoonsnaamVolgensDocumentVoorGrensoverschrijding to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated persoonsnaamVolgensDocumentVoorGrensoverschrijding,
     * or with status {@code 400 (Bad Request)} if the persoonsnaamVolgensDocumentVoorGrensoverschrijding is not valid,
     * or with status {@code 500 (Internal Server Error)} if the persoonsnaamVolgensDocumentVoorGrensoverschrijding couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/persoonsnaam-volgens-document-voor-grensoverschrijdings")
    public ResponseEntity<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> updatePersoonsnaamVolgensDocumentVoorGrensoverschrijding(@RequestBody PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding) throws URISyntaxException {
        log.debug("REST request to update PersoonsnaamVolgensDocumentVoorGrensoverschrijding : {}", persoonsnaamVolgensDocumentVoorGrensoverschrijding);
        if (persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding result = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.save(persoonsnaamVolgensDocumentVoorGrensoverschrijding);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /persoonsnaam-volgens-document-voor-grensoverschrijdings} : get all the persoonsnaamVolgensDocumentVoorGrensoverschrijdings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of persoonsnaamVolgensDocumentVoorGrensoverschrijdings in body.
     */
    @GetMapping("/persoonsnaam-volgens-document-voor-grensoverschrijdings")
    public List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> getAllPersoonsnaamVolgensDocumentVoorGrensoverschrijdings() {
        log.debug("REST request to get all PersoonsnaamVolgensDocumentVoorGrensoverschrijdings");
        return persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
    }

    /**
     * {@code GET  /persoonsnaam-volgens-document-voor-grensoverschrijdings/:id} : get the "id" persoonsnaamVolgensDocumentVoorGrensoverschrijding.
     *
     * @param id the id of the persoonsnaamVolgensDocumentVoorGrensoverschrijding to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the persoonsnaamVolgensDocumentVoorGrensoverschrijding, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/persoonsnaam-volgens-document-voor-grensoverschrijdings/{id}")
    public ResponseEntity<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> getPersoonsnaamVolgensDocumentVoorGrensoverschrijding(@PathVariable Long id) {
        log.debug("REST request to get PersoonsnaamVolgensDocumentVoorGrensoverschrijding : {}", id);
        Optional<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijding = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(persoonsnaamVolgensDocumentVoorGrensoverschrijding);
    }

    /**
     * {@code DELETE  /persoonsnaam-volgens-document-voor-grensoverschrijdings/:id} : delete the "id" persoonsnaamVolgensDocumentVoorGrensoverschrijding.
     *
     * @param id the id of the persoonsnaamVolgensDocumentVoorGrensoverschrijding to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/persoonsnaam-volgens-document-voor-grensoverschrijdings/{id}")
    public ResponseEntity<Void> deletePersoonsnaamVolgensDocumentVoorGrensoverschrijding(@PathVariable Long id) {
        log.debug("REST request to delete PersoonsnaamVolgensDocumentVoorGrensoverschrijding : {}", id);
        persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
