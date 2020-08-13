package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Bekostiging;
import com.mycompany.myapp.repository.BekostigingRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Bekostiging}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BekostigingResource {

    private final Logger log = LoggerFactory.getLogger(BekostigingResource.class);

    private static final String ENTITY_NAME = "bekostiging";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BekostigingRepository bekostigingRepository;

    public BekostigingResource(BekostigingRepository bekostigingRepository) {
        this.bekostigingRepository = bekostigingRepository;
    }

    /**
     * {@code POST  /bekostigings} : Create a new bekostiging.
     *
     * @param bekostiging the bekostiging to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bekostiging, or with status {@code 400 (Bad Request)} if the bekostiging has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bekostigings")
    public ResponseEntity<Bekostiging> createBekostiging(@RequestBody Bekostiging bekostiging) throws URISyntaxException {
        log.debug("REST request to save Bekostiging : {}", bekostiging);
        if (bekostiging.getId() != null) {
            throw new BadRequestAlertException("A new bekostiging cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bekostiging result = bekostigingRepository.save(bekostiging);
        return ResponseEntity.created(new URI("/api/bekostigings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bekostigings} : Updates an existing bekostiging.
     *
     * @param bekostiging the bekostiging to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bekostiging,
     * or with status {@code 400 (Bad Request)} if the bekostiging is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bekostiging couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bekostigings")
    public ResponseEntity<Bekostiging> updateBekostiging(@RequestBody Bekostiging bekostiging) throws URISyntaxException {
        log.debug("REST request to update Bekostiging : {}", bekostiging);
        if (bekostiging.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bekostiging result = bekostigingRepository.save(bekostiging);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bekostiging.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bekostigings} : get all the bekostigings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bekostigings in body.
     */
    @GetMapping("/bekostigings")
    public List<Bekostiging> getAllBekostigings() {
        log.debug("REST request to get all Bekostigings");
        return bekostigingRepository.findAll();
    }

    /**
     * {@code GET  /bekostigings/:id} : get the "id" bekostiging.
     *
     * @param id the id of the bekostiging to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bekostiging, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bekostigings/{id}")
    public ResponseEntity<Bekostiging> getBekostiging(@PathVariable Long id) {
        log.debug("REST request to get Bekostiging : {}", id);
        Optional<Bekostiging> bekostiging = bekostigingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bekostiging);
    }

    /**
     * {@code DELETE  /bekostigings/:id} : delete the "id" bekostiging.
     *
     * @param id the id of the bekostiging to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bekostigings/{id}")
    public ResponseEntity<Void> deleteBekostiging(@PathVariable Long id) {
        log.debug("REST request to delete Bekostiging : {}", id);
        bekostigingRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
