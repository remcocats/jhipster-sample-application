package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Kenniswerk;
import com.mycompany.myapp.repository.KenniswerkRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Kenniswerk}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class KenniswerkResource {

    private final Logger log = LoggerFactory.getLogger(KenniswerkResource.class);

    private static final String ENTITY_NAME = "kenniswerk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KenniswerkRepository kenniswerkRepository;

    public KenniswerkResource(KenniswerkRepository kenniswerkRepository) {
        this.kenniswerkRepository = kenniswerkRepository;
    }

    /**
     * {@code POST  /kenniswerks} : Create a new kenniswerk.
     *
     * @param kenniswerk the kenniswerk to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kenniswerk, or with status {@code 400 (Bad Request)} if the kenniswerk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kenniswerks")
    public ResponseEntity<Kenniswerk> createKenniswerk(@RequestBody Kenniswerk kenniswerk) throws URISyntaxException {
        log.debug("REST request to save Kenniswerk : {}", kenniswerk);
        if (kenniswerk.getId() != null) {
            throw new BadRequestAlertException("A new kenniswerk cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Kenniswerk result = kenniswerkRepository.save(kenniswerk);
        return ResponseEntity.created(new URI("/api/kenniswerks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kenniswerks} : Updates an existing kenniswerk.
     *
     * @param kenniswerk the kenniswerk to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kenniswerk,
     * or with status {@code 400 (Bad Request)} if the kenniswerk is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kenniswerk couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kenniswerks")
    public ResponseEntity<Kenniswerk> updateKenniswerk(@RequestBody Kenniswerk kenniswerk) throws URISyntaxException {
        log.debug("REST request to update Kenniswerk : {}", kenniswerk);
        if (kenniswerk.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Kenniswerk result = kenniswerkRepository.save(kenniswerk);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kenniswerk.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kenniswerks} : get all the kenniswerks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kenniswerks in body.
     */
    @GetMapping("/kenniswerks")
    public List<Kenniswerk> getAllKenniswerks() {
        log.debug("REST request to get all Kenniswerks");
        return kenniswerkRepository.findAll();
    }

    /**
     * {@code GET  /kenniswerks/:id} : get the "id" kenniswerk.
     *
     * @param id the id of the kenniswerk to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kenniswerk, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kenniswerks/{id}")
    public ResponseEntity<Kenniswerk> getKenniswerk(@PathVariable Long id) {
        log.debug("REST request to get Kenniswerk : {}", id);
        Optional<Kenniswerk> kenniswerk = kenniswerkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(kenniswerk);
    }

    /**
     * {@code DELETE  /kenniswerks/:id} : delete the "id" kenniswerk.
     *
     * @param id the id of the kenniswerk to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kenniswerks/{id}")
    public ResponseEntity<Void> deleteKenniswerk(@PathVariable Long id) {
        log.debug("REST request to delete Kenniswerk : {}", id);
        kenniswerkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
