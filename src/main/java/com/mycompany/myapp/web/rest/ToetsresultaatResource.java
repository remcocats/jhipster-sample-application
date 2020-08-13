package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Toetsresultaat;
import com.mycompany.myapp.repository.ToetsresultaatRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Toetsresultaat}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ToetsresultaatResource {

    private final Logger log = LoggerFactory.getLogger(ToetsresultaatResource.class);

    private static final String ENTITY_NAME = "toetsresultaat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToetsresultaatRepository toetsresultaatRepository;

    public ToetsresultaatResource(ToetsresultaatRepository toetsresultaatRepository) {
        this.toetsresultaatRepository = toetsresultaatRepository;
    }

    /**
     * {@code POST  /toetsresultaats} : Create a new toetsresultaat.
     *
     * @param toetsresultaat the toetsresultaat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toetsresultaat, or with status {@code 400 (Bad Request)} if the toetsresultaat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/toetsresultaats")
    public ResponseEntity<Toetsresultaat> createToetsresultaat(@RequestBody Toetsresultaat toetsresultaat) throws URISyntaxException {
        log.debug("REST request to save Toetsresultaat : {}", toetsresultaat);
        if (toetsresultaat.getId() != null) {
            throw new BadRequestAlertException("A new toetsresultaat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Toetsresultaat result = toetsresultaatRepository.save(toetsresultaat);
        return ResponseEntity.created(new URI("/api/toetsresultaats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /toetsresultaats} : Updates an existing toetsresultaat.
     *
     * @param toetsresultaat the toetsresultaat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toetsresultaat,
     * or with status {@code 400 (Bad Request)} if the toetsresultaat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toetsresultaat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/toetsresultaats")
    public ResponseEntity<Toetsresultaat> updateToetsresultaat(@RequestBody Toetsresultaat toetsresultaat) throws URISyntaxException {
        log.debug("REST request to update Toetsresultaat : {}", toetsresultaat);
        if (toetsresultaat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Toetsresultaat result = toetsresultaatRepository.save(toetsresultaat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toetsresultaat.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /toetsresultaats} : get all the toetsresultaats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toetsresultaats in body.
     */
    @GetMapping("/toetsresultaats")
    public List<Toetsresultaat> getAllToetsresultaats() {
        log.debug("REST request to get all Toetsresultaats");
        return toetsresultaatRepository.findAll();
    }

    /**
     * {@code GET  /toetsresultaats/:id} : get the "id" toetsresultaat.
     *
     * @param id the id of the toetsresultaat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toetsresultaat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/toetsresultaats/{id}")
    public ResponseEntity<Toetsresultaat> getToetsresultaat(@PathVariable Long id) {
        log.debug("REST request to get Toetsresultaat : {}", id);
        Optional<Toetsresultaat> toetsresultaat = toetsresultaatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(toetsresultaat);
    }

    /**
     * {@code DELETE  /toetsresultaats/:id} : delete the "id" toetsresultaat.
     *
     * @param id the id of the toetsresultaat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/toetsresultaats/{id}")
    public ResponseEntity<Void> deleteToetsresultaat(@PathVariable Long id) {
        log.debug("REST request to delete Toetsresultaat : {}", id);
        toetsresultaatRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
