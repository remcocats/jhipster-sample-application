package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Verblijf;
import com.mycompany.myapp.repository.VerblijfRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Verblijf}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VerblijfResource {

    private final Logger log = LoggerFactory.getLogger(VerblijfResource.class);

    private static final String ENTITY_NAME = "verblijf";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VerblijfRepository verblijfRepository;

    public VerblijfResource(VerblijfRepository verblijfRepository) {
        this.verblijfRepository = verblijfRepository;
    }

    /**
     * {@code POST  /verblijfs} : Create a new verblijf.
     *
     * @param verblijf the verblijf to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new verblijf, or with status {@code 400 (Bad Request)} if the verblijf has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/verblijfs")
    public ResponseEntity<Verblijf> createVerblijf(@RequestBody Verblijf verblijf) throws URISyntaxException {
        log.debug("REST request to save Verblijf : {}", verblijf);
        if (verblijf.getId() != null) {
            throw new BadRequestAlertException("A new verblijf cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Verblijf result = verblijfRepository.save(verblijf);
        return ResponseEntity.created(new URI("/api/verblijfs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /verblijfs} : Updates an existing verblijf.
     *
     * @param verblijf the verblijf to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated verblijf,
     * or with status {@code 400 (Bad Request)} if the verblijf is not valid,
     * or with status {@code 500 (Internal Server Error)} if the verblijf couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/verblijfs")
    public ResponseEntity<Verblijf> updateVerblijf(@RequestBody Verblijf verblijf) throws URISyntaxException {
        log.debug("REST request to update Verblijf : {}", verblijf);
        if (verblijf.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Verblijf result = verblijfRepository.save(verblijf);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, verblijf.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /verblijfs} : get all the verblijfs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of verblijfs in body.
     */
    @GetMapping("/verblijfs")
    public ResponseEntity<List<Verblijf>> getAllVerblijfs(Pageable pageable) {
        log.debug("REST request to get a page of Verblijfs");
        Page<Verblijf> page = verblijfRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /verblijfs/:id} : get the "id" verblijf.
     *
     * @param id the id of the verblijf to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the verblijf, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/verblijfs/{id}")
    public ResponseEntity<Verblijf> getVerblijf(@PathVariable Long id) {
        log.debug("REST request to get Verblijf : {}", id);
        Optional<Verblijf> verblijf = verblijfRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(verblijf);
    }

    /**
     * {@code DELETE  /verblijfs/:id} : delete the "id" verblijf.
     *
     * @param id the id of the verblijf to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/verblijfs/{id}")
    public ResponseEntity<Void> deleteVerblijf(@PathVariable Long id) {
        log.debug("REST request to delete Verblijf : {}", id);
        verblijfRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
