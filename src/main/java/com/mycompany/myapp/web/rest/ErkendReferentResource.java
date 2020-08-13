package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ErkendReferent;
import com.mycompany.myapp.repository.ErkendReferentRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ErkendReferent}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ErkendReferentResource {

    private final Logger log = LoggerFactory.getLogger(ErkendReferentResource.class);

    private static final String ENTITY_NAME = "erkendReferent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ErkendReferentRepository erkendReferentRepository;

    public ErkendReferentResource(ErkendReferentRepository erkendReferentRepository) {
        this.erkendReferentRepository = erkendReferentRepository;
    }

    /**
     * {@code POST  /erkend-referents} : Create a new erkendReferent.
     *
     * @param erkendReferent the erkendReferent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new erkendReferent, or with status {@code 400 (Bad Request)} if the erkendReferent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/erkend-referents")
    public ResponseEntity<ErkendReferent> createErkendReferent(@RequestBody ErkendReferent erkendReferent) throws URISyntaxException {
        log.debug("REST request to save ErkendReferent : {}", erkendReferent);
        if (erkendReferent.getId() != null) {
            throw new BadRequestAlertException("A new erkendReferent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ErkendReferent result = erkendReferentRepository.save(erkendReferent);
        return ResponseEntity.created(new URI("/api/erkend-referents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /erkend-referents} : Updates an existing erkendReferent.
     *
     * @param erkendReferent the erkendReferent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated erkendReferent,
     * or with status {@code 400 (Bad Request)} if the erkendReferent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the erkendReferent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/erkend-referents")
    public ResponseEntity<ErkendReferent> updateErkendReferent(@RequestBody ErkendReferent erkendReferent) throws URISyntaxException {
        log.debug("REST request to update ErkendReferent : {}", erkendReferent);
        if (erkendReferent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ErkendReferent result = erkendReferentRepository.save(erkendReferent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, erkendReferent.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /erkend-referents} : get all the erkendReferents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of erkendReferents in body.
     */
    @GetMapping("/erkend-referents")
    public ResponseEntity<List<ErkendReferent>> getAllErkendReferents(Pageable pageable) {
        log.debug("REST request to get a page of ErkendReferents");
        Page<ErkendReferent> page = erkendReferentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /erkend-referents/:id} : get the "id" erkendReferent.
     *
     * @param id the id of the erkendReferent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the erkendReferent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/erkend-referents/{id}")
    public ResponseEntity<ErkendReferent> getErkendReferent(@PathVariable Long id) {
        log.debug("REST request to get ErkendReferent : {}", id);
        Optional<ErkendReferent> erkendReferent = erkendReferentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(erkendReferent);
    }

    /**
     * {@code DELETE  /erkend-referents/:id} : delete the "id" erkendReferent.
     *
     * @param id the id of the erkendReferent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/erkend-referents/{id}")
    public ResponseEntity<Void> deleteErkendReferent(@PathVariable Long id) {
        log.debug("REST request to delete ErkendReferent : {}", id);
        erkendReferentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
