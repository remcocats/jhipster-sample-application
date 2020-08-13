package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Nationaliteit;
import com.mycompany.myapp.repository.NationaliteitRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Nationaliteit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NationaliteitResource {

    private final Logger log = LoggerFactory.getLogger(NationaliteitResource.class);

    private static final String ENTITY_NAME = "nationaliteit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NationaliteitRepository nationaliteitRepository;

    public NationaliteitResource(NationaliteitRepository nationaliteitRepository) {
        this.nationaliteitRepository = nationaliteitRepository;
    }

    /**
     * {@code POST  /nationaliteits} : Create a new nationaliteit.
     *
     * @param nationaliteit the nationaliteit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nationaliteit, or with status {@code 400 (Bad Request)} if the nationaliteit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nationaliteits")
    public ResponseEntity<Nationaliteit> createNationaliteit(@RequestBody Nationaliteit nationaliteit) throws URISyntaxException {
        log.debug("REST request to save Nationaliteit : {}", nationaliteit);
        if (nationaliteit.getId() != null) {
            throw new BadRequestAlertException("A new nationaliteit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Nationaliteit result = nationaliteitRepository.save(nationaliteit);
        return ResponseEntity.created(new URI("/api/nationaliteits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nationaliteits} : Updates an existing nationaliteit.
     *
     * @param nationaliteit the nationaliteit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nationaliteit,
     * or with status {@code 400 (Bad Request)} if the nationaliteit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nationaliteit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nationaliteits")
    public ResponseEntity<Nationaliteit> updateNationaliteit(@RequestBody Nationaliteit nationaliteit) throws URISyntaxException {
        log.debug("REST request to update Nationaliteit : {}", nationaliteit);
        if (nationaliteit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Nationaliteit result = nationaliteitRepository.save(nationaliteit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nationaliteit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nationaliteits} : get all the nationaliteits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nationaliteits in body.
     */
    @GetMapping("/nationaliteits")
    public List<Nationaliteit> getAllNationaliteits() {
        log.debug("REST request to get all Nationaliteits");
        return nationaliteitRepository.findAll();
    }

    /**
     * {@code GET  /nationaliteits/:id} : get the "id" nationaliteit.
     *
     * @param id the id of the nationaliteit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nationaliteit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nationaliteits/{id}")
    public ResponseEntity<Nationaliteit> getNationaliteit(@PathVariable Long id) {
        log.debug("REST request to get Nationaliteit : {}", id);
        Optional<Nationaliteit> nationaliteit = nationaliteitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nationaliteit);
    }

    /**
     * {@code DELETE  /nationaliteits/:id} : delete the "id" nationaliteit.
     *
     * @param id the id of the nationaliteit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nationaliteits/{id}")
    public ResponseEntity<Void> deleteNationaliteit(@PathVariable Long id) {
        log.debug("REST request to delete Nationaliteit : {}", id);
        nationaliteitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
