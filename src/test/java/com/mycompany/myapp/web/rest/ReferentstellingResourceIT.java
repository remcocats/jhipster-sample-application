package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Referentstelling;
import com.mycompany.myapp.repository.ReferentstellingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ReferentstellingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReferentstellingResourceIT {

    @Autowired
    private ReferentstellingRepository referentstellingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReferentstellingMockMvc;

    private Referentstelling referentstelling;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Referentstelling createEntity(EntityManager em) {
        Referentstelling referentstelling = new Referentstelling();
        return referentstelling;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Referentstelling createUpdatedEntity(EntityManager em) {
        Referentstelling referentstelling = new Referentstelling();
        return referentstelling;
    }

    @BeforeEach
    public void initTest() {
        referentstelling = createEntity(em);
    }

    @Test
    @Transactional
    public void createReferentstelling() throws Exception {
        int databaseSizeBeforeCreate = referentstellingRepository.findAll().size();
        // Create the Referentstelling
        restReferentstellingMockMvc.perform(post("/api/referentstellings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referentstelling)))
            .andExpect(status().isCreated());

        // Validate the Referentstelling in the database
        List<Referentstelling> referentstellingList = referentstellingRepository.findAll();
        assertThat(referentstellingList).hasSize(databaseSizeBeforeCreate + 1);
        Referentstelling testReferentstelling = referentstellingList.get(referentstellingList.size() - 1);
    }

    @Test
    @Transactional
    public void createReferentstellingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referentstellingRepository.findAll().size();

        // Create the Referentstelling with an existing ID
        referentstelling.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferentstellingMockMvc.perform(post("/api/referentstellings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referentstelling)))
            .andExpect(status().isBadRequest());

        // Validate the Referentstelling in the database
        List<Referentstelling> referentstellingList = referentstellingRepository.findAll();
        assertThat(referentstellingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReferentstellings() throws Exception {
        // Initialize the database
        referentstellingRepository.saveAndFlush(referentstelling);

        // Get all the referentstellingList
        restReferentstellingMockMvc.perform(get("/api/referentstellings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referentstelling.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getReferentstelling() throws Exception {
        // Initialize the database
        referentstellingRepository.saveAndFlush(referentstelling);

        // Get the referentstelling
        restReferentstellingMockMvc.perform(get("/api/referentstellings/{id}", referentstelling.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(referentstelling.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingReferentstelling() throws Exception {
        // Get the referentstelling
        restReferentstellingMockMvc.perform(get("/api/referentstellings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReferentstelling() throws Exception {
        // Initialize the database
        referentstellingRepository.saveAndFlush(referentstelling);

        int databaseSizeBeforeUpdate = referentstellingRepository.findAll().size();

        // Update the referentstelling
        Referentstelling updatedReferentstelling = referentstellingRepository.findById(referentstelling.getId()).get();
        // Disconnect from session so that the updates on updatedReferentstelling are not directly saved in db
        em.detach(updatedReferentstelling);

        restReferentstellingMockMvc.perform(put("/api/referentstellings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedReferentstelling)))
            .andExpect(status().isOk());

        // Validate the Referentstelling in the database
        List<Referentstelling> referentstellingList = referentstellingRepository.findAll();
        assertThat(referentstellingList).hasSize(databaseSizeBeforeUpdate);
        Referentstelling testReferentstelling = referentstellingList.get(referentstellingList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingReferentstelling() throws Exception {
        int databaseSizeBeforeUpdate = referentstellingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferentstellingMockMvc.perform(put("/api/referentstellings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(referentstelling)))
            .andExpect(status().isBadRequest());

        // Validate the Referentstelling in the database
        List<Referentstelling> referentstellingList = referentstellingRepository.findAll();
        assertThat(referentstellingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReferentstelling() throws Exception {
        // Initialize the database
        referentstellingRepository.saveAndFlush(referentstelling);

        int databaseSizeBeforeDelete = referentstellingRepository.findAll().size();

        // Delete the referentstelling
        restReferentstellingMockMvc.perform(delete("/api/referentstellings/{id}", referentstelling.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Referentstelling> referentstellingList = referentstellingRepository.findAll();
        assertThat(referentstellingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
