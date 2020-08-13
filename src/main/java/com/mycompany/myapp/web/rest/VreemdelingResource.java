package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Vreemdeling;
import com.mycompany.myapp.repository.VreemdelingRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Vreemdeling}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VreemdelingResource {

    private final Logger log = LoggerFactory.getLogger(VreemdelingResource.class);

    private static final String ENTITY_NAME = "vreemdeling";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VreemdelingRepository vreemdelingRepository;

    public VreemdelingResource(VreemdelingRepository vreemdelingRepository) {
        this.vreemdelingRepository = vreemdelingRepository;
    }

    /**
     * {@code POST  /vreemdelings} : Create a new vreemdeling.
     *
     * @param vreemdeling the vreemdeling to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vreemdeling, or with status {@code 400 (Bad Request)} if the vreemdeling has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vreemdelings")
    public ResponseEntity<Vreemdeling> createVreemdeling(@RequestBody Vreemdeling vreemdeling) throws URISyntaxException {
        log.debug("REST request to save Vreemdeling : {}", vreemdeling);
        if (vreemdeling.getId() != null) {
            throw new BadRequestAlertException("A new vreemdeling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Vreemdeling result = vreemdelingRepository.save(vreemdeling);
        return ResponseEntity.created(new URI("/api/vreemdelings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vreemdelings} : Updates an existing vreemdeling.
     *
     * @param vreemdeling the vreemdeling to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vreemdeling,
     * or with status {@code 400 (Bad Request)} if the vreemdeling is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vreemdeling couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vreemdelings")
    public ResponseEntity<Vreemdeling> updateVreemdeling(@RequestBody Vreemdeling vreemdeling) throws URISyntaxException {
        log.debug("REST request to update Vreemdeling : {}", vreemdeling);
        if (vreemdeling.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Vreemdeling result = vreemdelingRepository.save(vreemdeling);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vreemdeling.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vreemdelings} : get all the vreemdelings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vreemdelings in body.
     */
    @GetMapping("/vreemdelings")
    public ResponseEntity<List<Vreemdeling>> getAllVreemdelings(Pageable pageable) {
        log.debug("REST request to get a page of Vreemdelings");
        Page<Vreemdeling> page = vreemdelingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vreemdelings/:id} : get the "id" vreemdeling.
     *
     * @param id the id of the vreemdeling to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vreemdeling, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vreemdelings/{id}")
    public ResponseEntity<Vreemdeling> getVreemdeling(@PathVariable Long id) {
        log.debug("REST request to get Vreemdeling : {}", id);
        Optional<Vreemdeling> vreemdeling = vreemdelingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vreemdeling);
    }

    /**
     * {@code DELETE  /vreemdelings/:id} : delete the "id" vreemdeling.
     *
     * @param id the id of the vreemdeling to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vreemdelings/{id}")
    public ResponseEntity<Void> deleteVreemdeling(@PathVariable Long id) {
        log.debug("REST request to delete Vreemdeling : {}", id);
        vreemdelingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
