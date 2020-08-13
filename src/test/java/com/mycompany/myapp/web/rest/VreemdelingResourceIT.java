package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Vreemdeling;
import com.mycompany.myapp.repository.VreemdelingRepository;

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
 * Integration tests for the {@link VreemdelingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VreemdelingResourceIT {

    private static final String DEFAULT_VREEMDELING_ID = "AAAAAAAAAA";
    private static final String UPDATED_VREEMDELING_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VNUMMER = "AAAAAAAAAA";
    private static final String UPDATED_VNUMMER = "BBBBBBBBBB";

    private static final String DEFAULT_BURGERSERVICENUMMER = "AAAAAAAAAA";
    private static final String UPDATED_BURGERSERVICENUMMER = "BBBBBBBBBB";

    @Autowired
    private VreemdelingRepository vreemdelingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVreemdelingMockMvc;

    private Vreemdeling vreemdeling;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vreemdeling createEntity(EntityManager em) {
        Vreemdeling vreemdeling = new Vreemdeling()
            .vreemdelingId(DEFAULT_VREEMDELING_ID)
            .vnummer(DEFAULT_VNUMMER)
            .burgerservicenummer(DEFAULT_BURGERSERVICENUMMER);
        return vreemdeling;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vreemdeling createUpdatedEntity(EntityManager em) {
        Vreemdeling vreemdeling = new Vreemdeling()
            .vreemdelingId(UPDATED_VREEMDELING_ID)
            .vnummer(UPDATED_VNUMMER)
            .burgerservicenummer(UPDATED_BURGERSERVICENUMMER);
        return vreemdeling;
    }

    @BeforeEach
    public void initTest() {
        vreemdeling = createEntity(em);
    }

    @Test
    @Transactional
    public void createVreemdeling() throws Exception {
        int databaseSizeBeforeCreate = vreemdelingRepository.findAll().size();
        // Create the Vreemdeling
        restVreemdelingMockMvc.perform(post("/api/vreemdelings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vreemdeling)))
            .andExpect(status().isCreated());

        // Validate the Vreemdeling in the database
        List<Vreemdeling> vreemdelingList = vreemdelingRepository.findAll();
        assertThat(vreemdelingList).hasSize(databaseSizeBeforeCreate + 1);
        Vreemdeling testVreemdeling = vreemdelingList.get(vreemdelingList.size() - 1);
        assertThat(testVreemdeling.getVreemdelingId()).isEqualTo(DEFAULT_VREEMDELING_ID);
        assertThat(testVreemdeling.getVnummer()).isEqualTo(DEFAULT_VNUMMER);
        assertThat(testVreemdeling.getBurgerservicenummer()).isEqualTo(DEFAULT_BURGERSERVICENUMMER);
    }

    @Test
    @Transactional
    public void createVreemdelingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vreemdelingRepository.findAll().size();

        // Create the Vreemdeling with an existing ID
        vreemdeling.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVreemdelingMockMvc.perform(post("/api/vreemdelings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vreemdeling)))
            .andExpect(status().isBadRequest());

        // Validate the Vreemdeling in the database
        List<Vreemdeling> vreemdelingList = vreemdelingRepository.findAll();
        assertThat(vreemdelingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVreemdelings() throws Exception {
        // Initialize the database
        vreemdelingRepository.saveAndFlush(vreemdeling);

        // Get all the vreemdelingList
        restVreemdelingMockMvc.perform(get("/api/vreemdelings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vreemdeling.getId().intValue())))
            .andExpect(jsonPath("$.[*].vreemdelingId").value(hasItem(DEFAULT_VREEMDELING_ID)))
            .andExpect(jsonPath("$.[*].vnummer").value(hasItem(DEFAULT_VNUMMER)))
            .andExpect(jsonPath("$.[*].burgerservicenummer").value(hasItem(DEFAULT_BURGERSERVICENUMMER)));
    }
    
    @Test
    @Transactional
    public void getVreemdeling() throws Exception {
        // Initialize the database
        vreemdelingRepository.saveAndFlush(vreemdeling);

        // Get the vreemdeling
        restVreemdelingMockMvc.perform(get("/api/vreemdelings/{id}", vreemdeling.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vreemdeling.getId().intValue()))
            .andExpect(jsonPath("$.vreemdelingId").value(DEFAULT_VREEMDELING_ID))
            .andExpect(jsonPath("$.vnummer").value(DEFAULT_VNUMMER))
            .andExpect(jsonPath("$.burgerservicenummer").value(DEFAULT_BURGERSERVICENUMMER));
    }
    @Test
    @Transactional
    public void getNonExistingVreemdeling() throws Exception {
        // Get the vreemdeling
        restVreemdelingMockMvc.perform(get("/api/vreemdelings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVreemdeling() throws Exception {
        // Initialize the database
        vreemdelingRepository.saveAndFlush(vreemdeling);

        int databaseSizeBeforeUpdate = vreemdelingRepository.findAll().size();

        // Update the vreemdeling
        Vreemdeling updatedVreemdeling = vreemdelingRepository.findById(vreemdeling.getId()).get();
        // Disconnect from session so that the updates on updatedVreemdeling are not directly saved in db
        em.detach(updatedVreemdeling);
        updatedVreemdeling
            .vreemdelingId(UPDATED_VREEMDELING_ID)
            .vnummer(UPDATED_VNUMMER)
            .burgerservicenummer(UPDATED_BURGERSERVICENUMMER);

        restVreemdelingMockMvc.perform(put("/api/vreemdelings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVreemdeling)))
            .andExpect(status().isOk());

        // Validate the Vreemdeling in the database
        List<Vreemdeling> vreemdelingList = vreemdelingRepository.findAll();
        assertThat(vreemdelingList).hasSize(databaseSizeBeforeUpdate);
        Vreemdeling testVreemdeling = vreemdelingList.get(vreemdelingList.size() - 1);
        assertThat(testVreemdeling.getVreemdelingId()).isEqualTo(UPDATED_VREEMDELING_ID);
        assertThat(testVreemdeling.getVnummer()).isEqualTo(UPDATED_VNUMMER);
        assertThat(testVreemdeling.getBurgerservicenummer()).isEqualTo(UPDATED_BURGERSERVICENUMMER);
    }

    @Test
    @Transactional
    public void updateNonExistingVreemdeling() throws Exception {
        int databaseSizeBeforeUpdate = vreemdelingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVreemdelingMockMvc.perform(put("/api/vreemdelings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vreemdeling)))
            .andExpect(status().isBadRequest());

        // Validate the Vreemdeling in the database
        List<Vreemdeling> vreemdelingList = vreemdelingRepository.findAll();
        assertThat(vreemdelingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVreemdeling() throws Exception {
        // Initialize the database
        vreemdelingRepository.saveAndFlush(vreemdeling);

        int databaseSizeBeforeDelete = vreemdelingRepository.findAll().size();

        // Delete the vreemdeling
        restVreemdelingMockMvc.perform(delete("/api/vreemdelings/{id}", vreemdeling.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vreemdeling> vreemdelingList = vreemdelingRepository.findAll();
        assertThat(vreemdelingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
