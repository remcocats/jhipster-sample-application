package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Nationaliteit;
import com.mycompany.myapp.repository.NationaliteitRepository;

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
 * Integration tests for the {@link NationaliteitResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NationaliteitResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private NationaliteitRepository nationaliteitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNationaliteitMockMvc;

    private Nationaliteit nationaliteit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nationaliteit createEntity(EntityManager em) {
        Nationaliteit nationaliteit = new Nationaliteit()
            .code(DEFAULT_CODE);
        return nationaliteit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nationaliteit createUpdatedEntity(EntityManager em) {
        Nationaliteit nationaliteit = new Nationaliteit()
            .code(UPDATED_CODE);
        return nationaliteit;
    }

    @BeforeEach
    public void initTest() {
        nationaliteit = createEntity(em);
    }

    @Test
    @Transactional
    public void createNationaliteit() throws Exception {
        int databaseSizeBeforeCreate = nationaliteitRepository.findAll().size();
        // Create the Nationaliteit
        restNationaliteitMockMvc.perform(post("/api/nationaliteits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationaliteit)))
            .andExpect(status().isCreated());

        // Validate the Nationaliteit in the database
        List<Nationaliteit> nationaliteitList = nationaliteitRepository.findAll();
        assertThat(nationaliteitList).hasSize(databaseSizeBeforeCreate + 1);
        Nationaliteit testNationaliteit = nationaliteitList.get(nationaliteitList.size() - 1);
        assertThat(testNationaliteit.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createNationaliteitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nationaliteitRepository.findAll().size();

        // Create the Nationaliteit with an existing ID
        nationaliteit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNationaliteitMockMvc.perform(post("/api/nationaliteits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationaliteit)))
            .andExpect(status().isBadRequest());

        // Validate the Nationaliteit in the database
        List<Nationaliteit> nationaliteitList = nationaliteitRepository.findAll();
        assertThat(nationaliteitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNationaliteits() throws Exception {
        // Initialize the database
        nationaliteitRepository.saveAndFlush(nationaliteit);

        // Get all the nationaliteitList
        restNationaliteitMockMvc.perform(get("/api/nationaliteits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nationaliteit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getNationaliteit() throws Exception {
        // Initialize the database
        nationaliteitRepository.saveAndFlush(nationaliteit);

        // Get the nationaliteit
        restNationaliteitMockMvc.perform(get("/api/nationaliteits/{id}", nationaliteit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nationaliteit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingNationaliteit() throws Exception {
        // Get the nationaliteit
        restNationaliteitMockMvc.perform(get("/api/nationaliteits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNationaliteit() throws Exception {
        // Initialize the database
        nationaliteitRepository.saveAndFlush(nationaliteit);

        int databaseSizeBeforeUpdate = nationaliteitRepository.findAll().size();

        // Update the nationaliteit
        Nationaliteit updatedNationaliteit = nationaliteitRepository.findById(nationaliteit.getId()).get();
        // Disconnect from session so that the updates on updatedNationaliteit are not directly saved in db
        em.detach(updatedNationaliteit);
        updatedNationaliteit
            .code(UPDATED_CODE);

        restNationaliteitMockMvc.perform(put("/api/nationaliteits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNationaliteit)))
            .andExpect(status().isOk());

        // Validate the Nationaliteit in the database
        List<Nationaliteit> nationaliteitList = nationaliteitRepository.findAll();
        assertThat(nationaliteitList).hasSize(databaseSizeBeforeUpdate);
        Nationaliteit testNationaliteit = nationaliteitList.get(nationaliteitList.size() - 1);
        assertThat(testNationaliteit.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingNationaliteit() throws Exception {
        int databaseSizeBeforeUpdate = nationaliteitRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNationaliteitMockMvc.perform(put("/api/nationaliteits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nationaliteit)))
            .andExpect(status().isBadRequest());

        // Validate the Nationaliteit in the database
        List<Nationaliteit> nationaliteitList = nationaliteitRepository.findAll();
        assertThat(nationaliteitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNationaliteit() throws Exception {
        // Initialize the database
        nationaliteitRepository.saveAndFlush(nationaliteit);

        int databaseSizeBeforeDelete = nationaliteitRepository.findAll().size();

        // Delete the nationaliteit
        restNationaliteitMockMvc.perform(delete("/api/nationaliteits/{id}", nationaliteit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nationaliteit> nationaliteitList = nationaliteitRepository.findAll();
        assertThat(nationaliteitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
