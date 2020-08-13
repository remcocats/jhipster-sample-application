package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Verblijfsdoel;
import com.mycompany.myapp.repository.VerblijfsdoelRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Verblijfsdoel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VerblijfsdoelResource {

    private final Logger log = LoggerFactory.getLogger(VerblijfsdoelResource.class);

    private static final String ENTITY_NAME = "verblijfsdoel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VerblijfsdoelRepository verblijfsdoelRepository;

    public VerblijfsdoelResource(VerblijfsdoelRepository verblijfsdoelRepository) {
        this.verblijfsdoelRepository = verblijfsdoelRepository;
    }

    /**
     * {@code POST  /verblijfsdoels} : Create a new verblijfsdoel.
     *
     * @param verblijfsdoel the verblijfsdoel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new verblijfsdoel, or with status {@code 400 (Bad Request)} if the verblijfsdoel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/verblijfsdoels")
    public ResponseEntity<Verblijfsdoel> createVerblijfsdoel(@RequestBody Verblijfsdoel verblijfsdoel) throws URISyntaxException {
        log.debug("REST request to save Verblijfsdoel : {}", verblijfsdoel);
        if (verblijfsdoel.getId() != null) {
            throw new BadRequestAlertException("A new verblijfsdoel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Verblijfsdoel result = verblijfsdoelRepository.save(verblijfsdoel);
        return ResponseEntity.created(new URI("/api/verblijfsdoels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /verblijfsdoels} : Updates an existing verblijfsdoel.
     *
     * @param verblijfsdoel the verblijfsdoel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated verblijfsdoel,
     * or with status {@code 400 (Bad Request)} if the verblijfsdoel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the verblijfsdoel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/verblijfsdoels")
    public ResponseEntity<Verblijfsdoel> updateVerblijfsdoel(@RequestBody Verblijfsdoel verblijfsdoel) throws URISyntaxException {
        log.debug("REST request to update Verblijfsdoel : {}", verblijfsdoel);
        if (verblijfsdoel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Verblijfsdoel result = verblijfsdoelRepository.save(verblijfsdoel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, verblijfsdoel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /verblijfsdoels} : get all the verblijfsdoels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of verblijfsdoels in body.
     */
    @GetMapping("/verblijfsdoels")
    public List<Verblijfsdoel> getAllVerblijfsdoels() {
        log.debug("REST request to get all Verblijfsdoels");
        return verblijfsdoelRepository.findAll();
    }

    /**
     * {@code GET  /verblijfsdoels/:id} : get the "id" verblijfsdoel.
     *
     * @param id the id of the verblijfsdoel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the verblijfsdoel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/verblijfsdoels/{id}")
    public ResponseEntity<Verblijfsdoel> getVerblijfsdoel(@PathVariable Long id) {
        log.debug("REST request to get Verblijfsdoel : {}", id);
        Optional<Verblijfsdoel> verblijfsdoel = verblijfsdoelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(verblijfsdoel);
    }

    /**
     * {@code DELETE  /verblijfsdoels/:id} : delete the "id" verblijfsdoel.
     *
     * @param id the id of the verblijfsdoel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/verblijfsdoels/{id}")
    public ResponseEntity<Void> deleteVerblijfsdoel(@PathVariable Long id) {
        log.debug("REST request to delete Verblijfsdoel : {}", id);
        verblijfsdoelRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
