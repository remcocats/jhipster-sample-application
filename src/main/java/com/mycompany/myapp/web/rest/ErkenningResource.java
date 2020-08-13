package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Erkenning;
import com.mycompany.myapp.repository.ErkenningRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Erkenning}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ErkenningResource {

    private final Logger log = LoggerFactory.getLogger(ErkenningResource.class);

    private static final String ENTITY_NAME = "erkenning";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ErkenningRepository erkenningRepository;

    public ErkenningResource(ErkenningRepository erkenningRepository) {
        this.erkenningRepository = erkenningRepository;
    }

    /**
     * {@code POST  /erkennings} : Create a new erkenning.
     *
     * @param erkenning the erkenning to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new erkenning, or with status {@code 400 (Bad Request)} if the erkenning has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/erkennings")
    public ResponseEntity<Erkenning> createErkenning(@RequestBody Erkenning erkenning) throws URISyntaxException {
        log.debug("REST request to save Erkenning : {}", erkenning);
        if (erkenning.getId() != null) {
            throw new BadRequestAlertException("A new erkenning cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Erkenning result = erkenningRepository.save(erkenning);
        return ResponseEntity.created(new URI("/api/erkennings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /erkennings} : Updates an existing erkenning.
     *
     * @param erkenning the erkenning to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated erkenning,
     * or with status {@code 400 (Bad Request)} if the erkenning is not valid,
     * or with status {@code 500 (Internal Server Error)} if the erkenning couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/erkennings")
    public ResponseEntity<Erkenning> updateErkenning(@RequestBody Erkenning erkenning) throws URISyntaxException {
        log.debug("REST request to update Erkenning : {}", erkenning);
        if (erkenning.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Erkenning result = erkenningRepository.save(erkenning);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, erkenning.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /erkennings} : get all the erkennings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of erkennings in body.
     */
    @GetMapping("/erkennings")
    public List<Erkenning> getAllErkennings() {
        log.debug("REST request to get all Erkennings");
        return erkenningRepository.findAll();
    }

    /**
     * {@code GET  /erkennings/:id} : get the "id" erkenning.
     *
     * @param id the id of the erkenning to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the erkenning, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/erkennings/{id}")
    public ResponseEntity<Erkenning> getErkenning(@PathVariable Long id) {
        log.debug("REST request to get Erkenning : {}", id);
        Optional<Erkenning> erkenning = erkenningRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(erkenning);
    }

    /**
     * {@code DELETE  /erkennings/:id} : delete the "id" erkenning.
     *
     * @param id the id of the erkenning to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/erkennings/{id}")
    public ResponseEntity<Void> deleteErkenning(@PathVariable Long id) {
        log.debug("REST request to delete Erkenning : {}", id);
        erkenningRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
