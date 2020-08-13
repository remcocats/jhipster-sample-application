package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Verblijf;
import com.mycompany.myapp.repository.VerblijfRepository;

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
 * Integration tests for the {@link VerblijfResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VerblijfResourceIT {

    private static final String DEFAULT_VERBLIJF_ID = "AAAAAAAAAA";
    private static final String UPDATED_VERBLIJF_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AANVANGSDATUM = "AAAAAAAAAA";
    private static final String UPDATED_AANVANGSDATUM = "BBBBBBBBBB";

    private static final String DEFAULT_EINDDATUM = "AAAAAAAAAA";
    private static final String UPDATED_EINDDATUM = "BBBBBBBBBB";

    @Autowired
    private VerblijfRepository verblijfRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVerblijfMockMvc;

    private Verblijf verblijf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verblijf createEntity(EntityManager em) {
        Verblijf verblijf = new Verblijf()
            .verblijfId(DEFAULT_VERBLIJF_ID)
            .aanvangsdatum(DEFAULT_AANVANGSDATUM)
            .einddatum(DEFAULT_EINDDATUM);
        return verblijf;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verblijf createUpdatedEntity(EntityManager em) {
        Verblijf verblijf = new Verblijf()
            .verblijfId(UPDATED_VERBLIJF_ID)
            .aanvangsdatum(UPDATED_AANVANGSDATUM)
            .einddatum(UPDATED_EINDDATUM);
        return verblijf;
    }

    @BeforeEach
    public void initTest() {
        verblijf = createEntity(em);
    }

    @Test
    @Transactional
    public void createVerblijf() throws Exception {
        int databaseSizeBeforeCreate = verblijfRepository.findAll().size();
        // Create the Verblijf
        restVerblijfMockMvc.perform(post("/api/verblijfs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijf)))
            .andExpect(status().isCreated());

        // Validate the Verblijf in the database
        List<Verblijf> verblijfList = verblijfRepository.findAll();
        assertThat(verblijfList).hasSize(databaseSizeBeforeCreate + 1);
        Verblijf testVerblijf = verblijfList.get(verblijfList.size() - 1);
        assertThat(testVerblijf.getVerblijfId()).isEqualTo(DEFAULT_VERBLIJF_ID);
        assertThat(testVerblijf.getAanvangsdatum()).isEqualTo(DEFAULT_AANVANGSDATUM);
        assertThat(testVerblijf.getEinddatum()).isEqualTo(DEFAULT_EINDDATUM);
    }

    @Test
    @Transactional
    public void createVerblijfWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = verblijfRepository.findAll().size();

        // Create the Verblijf with an existing ID
        verblijf.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVerblijfMockMvc.perform(post("/api/verblijfs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijf)))
            .andExpect(status().isBadRequest());

        // Validate the Verblijf in the database
        List<Verblijf> verblijfList = verblijfRepository.findAll();
        assertThat(verblijfList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVerblijfs() throws Exception {
        // Initialize the database
        verblijfRepository.saveAndFlush(verblijf);

        // Get all the verblijfList
        restVerblijfMockMvc.perform(get("/api/verblijfs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verblijf.getId().intValue())))
            .andExpect(jsonPath("$.[*].verblijfId").value(hasItem(DEFAULT_VERBLIJF_ID)))
            .andExpect(jsonPath("$.[*].aanvangsdatum").value(hasItem(DEFAULT_AANVANGSDATUM)))
            .andExpect(jsonPath("$.[*].einddatum").value(hasItem(DEFAULT_EINDDATUM)));
    }
    
    @Test
    @Transactional
    public void getVerblijf() throws Exception {
        // Initialize the database
        verblijfRepository.saveAndFlush(verblijf);

        // Get the verblijf
        restVerblijfMockMvc.perform(get("/api/verblijfs/{id}", verblijf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(verblijf.getId().intValue()))
            .andExpect(jsonPath("$.verblijfId").value(DEFAULT_VERBLIJF_ID))
            .andExpect(jsonPath("$.aanvangsdatum").value(DEFAULT_AANVANGSDATUM))
            .andExpect(jsonPath("$.einddatum").value(DEFAULT_EINDDATUM));
    }
    @Test
    @Transactional
    public void getNonExistingVerblijf() throws Exception {
        // Get the verblijf
        restVerblijfMockMvc.perform(get("/api/verblijfs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVerblijf() throws Exception {
        // Initialize the database
        verblijfRepository.saveAndFlush(verblijf);

        int databaseSizeBeforeUpdate = verblijfRepository.findAll().size();

        // Update the verblijf
        Verblijf updatedVerblijf = verblijfRepository.findById(verblijf.getId()).get();
        // Disconnect from session so that the updates on updatedVerblijf are not directly saved in db
        em.detach(updatedVerblijf);
        updatedVerblijf
            .verblijfId(UPDATED_VERBLIJF_ID)
            .aanvangsdatum(UPDATED_AANVANGSDATUM)
            .einddatum(UPDATED_EINDDATUM);

        restVerblijfMockMvc.perform(put("/api/verblijfs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVerblijf)))
            .andExpect(status().isOk());

        // Validate the Verblijf in the database
        List<Verblijf> verblijfList = verblijfRepository.findAll();
        assertThat(verblijfList).hasSize(databaseSizeBeforeUpdate);
        Verblijf testVerblijf = verblijfList.get(verblijfList.size() - 1);
        assertThat(testVerblijf.getVerblijfId()).isEqualTo(UPDATED_VERBLIJF_ID);
        assertThat(testVerblijf.getAanvangsdatum()).isEqualTo(UPDATED_AANVANGSDATUM);
        assertThat(testVerblijf.getEinddatum()).isEqualTo(UPDATED_EINDDATUM);
    }

    @Test
    @Transactional
    public void updateNonExistingVerblijf() throws Exception {
        int databaseSizeBeforeUpdate = verblijfRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerblijfMockMvc.perform(put("/api/verblijfs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijf)))
            .andExpect(status().isBadRequest());

        // Validate the Verblijf in the database
        List<Verblijf> verblijfList = verblijfRepository.findAll();
        assertThat(verblijfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVerblijf() throws Exception {
        // Initialize the database
        verblijfRepository.saveAndFlush(verblijf);

        int databaseSizeBeforeDelete = verblijfRepository.findAll().size();

        // Delete the verblijf
        restVerblijfMockMvc.perform(delete("/api/verblijfs/{id}", verblijf.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Verblijf> verblijfList = verblijfRepository.findAll();
        assertThat(verblijfList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
