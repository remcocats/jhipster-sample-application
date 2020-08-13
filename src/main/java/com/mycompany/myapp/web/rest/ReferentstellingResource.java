package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Referentstelling;
import com.mycompany.myapp.repository.ReferentstellingRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Referentstelling}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReferentstellingResource {

    private final Logger log = LoggerFactory.getLogger(ReferentstellingResource.class);

    private static final String ENTITY_NAME = "referentstelling";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReferentstellingRepository referentstellingRepository;

    public ReferentstellingResource(ReferentstellingRepository referentstellingRepository) {
        this.referentstellingRepository = referentstellingRepository;
    }

    /**
     * {@code POST  /referentstellings} : Create a new referentstelling.
     *
     * @param referentstelling the referentstelling to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new referentstelling, or with status {@code 400 (Bad Request)} if the referentstelling has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/referentstellings")
    public ResponseEntity<Referentstelling> createReferentstelling(@RequestBody Referentstelling referentstelling) throws URISyntaxException {
        log.debug("REST request to save Referentstelling : {}", referentstelling);
        if (referentstelling.getId() != null) {
            throw new BadRequestAlertException("A new referentstelling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Referentstelling result = referentstellingRepository.save(referentstelling);
        return ResponseEntity.created(new URI("/api/referentstellings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /referentstellings} : Updates an existing referentstelling.
     *
     * @param referentstelling the referentstelling to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referentstelling,
     * or with status {@code 400 (Bad Request)} if the referentstelling is not valid,
     * or with status {@code 500 (Internal Server Error)} if the referentstelling couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/referentstellings")
    public ResponseEntity<Referentstelling> updateReferentstelling(@RequestBody Referentstelling referentstelling) throws URISyntaxException {
        log.debug("REST request to update Referentstelling : {}", referentstelling);
        if (referentstelling.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Referentstelling result = referentstellingRepository.save(referentstelling);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, referentstelling.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /referentstellings} : get all the referentstellings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of referentstellings in body.
     */
    @GetMapping("/referentstellings")
    public List<Referentstelling> getAllReferentstellings() {
        log.debug("REST request to get all Referentstellings");
        return referentstellingRepository.findAll();
    }

    /**
     * {@code GET  /referentstellings/:id} : get the "id" referentstelling.
     *
     * @param id the id of the referentstelling to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the referentstelling, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/referentstellings/{id}")
    public ResponseEntity<Referentstelling> getReferentstelling(@PathVariable Long id) {
        log.debug("REST request to get Referentstelling : {}", id);
        Optional<Referentstelling> referentstelling = referentstellingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(referentstelling);
    }

    /**
     * {@code DELETE  /referentstellings/:id} : delete the "id" referentstelling.
     *
     * @param id the id of the referentstelling to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/referentstellings/{id}")
    public ResponseEntity<Void> deleteReferentstelling(@PathVariable Long id) {
        log.debug("REST request to delete Referentstelling : {}", id);
        referentstellingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
