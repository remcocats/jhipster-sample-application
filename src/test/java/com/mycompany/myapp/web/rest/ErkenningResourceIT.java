package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Erkenning;
import com.mycompany.myapp.repository.ErkenningRepository;

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
 * Integration tests for the {@link ErkenningResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ErkenningResourceIT {

    private static final String DEFAULT_VERBLIJFSDOEL = "AAAAAAAAAA";
    private static final String UPDATED_VERBLIJFSDOEL = "BBBBBBBBBB";

    private static final String DEFAULT_AANVANGSDATUM = "AAAAAAAAAA";
    private static final String UPDATED_AANVANGSDATUM = "BBBBBBBBBB";

    private static final String DEFAULT_EINDDATUM = "AAAAAAAAAA";
    private static final String UPDATED_EINDDATUM = "BBBBBBBBBB";

    @Autowired
    private ErkenningRepository erkenningRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restErkenningMockMvc;

    private Erkenning erkenning;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Erkenning createEntity(EntityManager em) {
        Erkenning erkenning = new Erkenning()
            .verblijfsdoel(DEFAULT_VERBLIJFSDOEL)
            .aanvangsdatum(DEFAULT_AANVANGSDATUM)
            .einddatum(DEFAULT_EINDDATUM);
        return erkenning;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Erkenning createUpdatedEntity(EntityManager em) {
        Erkenning erkenning = new Erkenning()
            .verblijfsdoel(UPDATED_VERBLIJFSDOEL)
            .aanvangsdatum(UPDATED_AANVANGSDATUM)
            .einddatum(UPDATED_EINDDATUM);
        return erkenning;
    }

    @BeforeEach
    public void initTest() {
        erkenning = createEntity(em);
    }

    @Test
    @Transactional
    public void createErkenning() throws Exception {
        int databaseSizeBeforeCreate = erkenningRepository.findAll().size();
        // Create the Erkenning
        restErkenningMockMvc.perform(post("/api/erkennings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkenning)))
            .andExpect(status().isCreated());

        // Validate the Erkenning in the database
        List<Erkenning> erkenningList = erkenningRepository.findAll();
        assertThat(erkenningList).hasSize(databaseSizeBeforeCreate + 1);
        Erkenning testErkenning = erkenningList.get(erkenningList.size() - 1);
        assertThat(testErkenning.getVerblijfsdoel()).isEqualTo(DEFAULT_VERBLIJFSDOEL);
        assertThat(testErkenning.getAanvangsdatum()).isEqualTo(DEFAULT_AANVANGSDATUM);
        assertThat(testErkenning.getEinddatum()).isEqualTo(DEFAULT_EINDDATUM);
    }

    @Test
    @Transactional
    public void createErkenningWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = erkenningRepository.findAll().size();

        // Create the Erkenning with an existing ID
        erkenning.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restErkenningMockMvc.perform(post("/api/erkennings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkenning)))
            .andExpect(status().isBadRequest());

        // Validate the Erkenning in the database
        List<Erkenning> erkenningList = erkenningRepository.findAll();
        assertThat(erkenningList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllErkennings() throws Exception {
        // Initialize the database
        erkenningRepository.saveAndFlush(erkenning);

        // Get all the erkenningList
        restErkenningMockMvc.perform(get("/api/erkennings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(erkenning.getId().intValue())))
            .andExpect(jsonPath("$.[*].verblijfsdoel").value(hasItem(DEFAULT_VERBLIJFSDOEL)))
            .andExpect(jsonPath("$.[*].aanvangsdatum").value(hasItem(DEFAULT_AANVANGSDATUM)))
            .andExpect(jsonPath("$.[*].einddatum").value(hasItem(DEFAULT_EINDDATUM)));
    }
    
    @Test
    @Transactional
    public void getErkenning() throws Exception {
        // Initialize the database
        erkenningRepository.saveAndFlush(erkenning);

        // Get the erkenning
        restErkenningMockMvc.perform(get("/api/erkennings/{id}", erkenning.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(erkenning.getId().intValue()))
            .andExpect(jsonPath("$.verblijfsdoel").value(DEFAULT_VERBLIJFSDOEL))
            .andExpect(jsonPath("$.aanvangsdatum").value(DEFAULT_AANVANGSDATUM))
            .andExpect(jsonPath("$.einddatum").value(DEFAULT_EINDDATUM));
    }
    @Test
    @Transactional
    public void getNonExistingErkenning() throws Exception {
        // Get the erkenning
        restErkenningMockMvc.perform(get("/api/erkennings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateErkenning() throws Exception {
        // Initialize the database
        erkenningRepository.saveAndFlush(erkenning);

        int databaseSizeBeforeUpdate = erkenningRepository.findAll().size();

        // Update the erkenning
        Erkenning updatedErkenning = erkenningRepository.findById(erkenning.getId()).get();
        // Disconnect from session so that the updates on updatedErkenning are not directly saved in db
        em.detach(updatedErkenning);
        updatedErkenning
            .verblijfsdoel(UPDATED_VERBLIJFSDOEL)
            .aanvangsdatum(UPDATED_AANVANGSDATUM)
            .einddatum(UPDATED_EINDDATUM);

        restErkenningMockMvc.perform(put("/api/erkennings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedErkenning)))
            .andExpect(status().isOk());

        // Validate the Erkenning in the database
        List<Erkenning> erkenningList = erkenningRepository.findAll();
        assertThat(erkenningList).hasSize(databaseSizeBeforeUpdate);
        Erkenning testErkenning = erkenningList.get(erkenningList.size() - 1);
        assertThat(testErkenning.getVerblijfsdoel()).isEqualTo(UPDATED_VERBLIJFSDOEL);
        assertThat(testErkenning.getAanvangsdatum()).isEqualTo(UPDATED_AANVANGSDATUM);
        assertThat(testErkenning.getEinddatum()).isEqualTo(UPDATED_EINDDATUM);
    }

    @Test
    @Transactional
    public void updateNonExistingErkenning() throws Exception {
        int databaseSizeBeforeUpdate = erkenningRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restErkenningMockMvc.perform(put("/api/erkennings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkenning)))
            .andExpect(status().isBadRequest());

        // Validate the Erkenning in the database
        List<Erkenning> erkenningList = erkenningRepository.findAll();
        assertThat(erkenningList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteErkenning() throws Exception {
        // Initialize the database
        erkenningRepository.saveAndFlush(erkenning);

        int databaseSizeBeforeDelete = erkenningRepository.findAll().size();

        // Delete the erkenning
        restErkenningMockMvc.perform(delete("/api/erkennings/{id}", erkenning.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Erkenning> erkenningList = erkenningRepository.findAll();
        assertThat(erkenningList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
