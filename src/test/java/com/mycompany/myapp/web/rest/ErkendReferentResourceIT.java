package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.ErkendReferent;
import com.mycompany.myapp.repository.ErkendReferentRepository;

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
 * Integration tests for the {@link ErkendReferentResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ErkendReferentResourceIT {

    private static final String DEFAULT_REFERENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_REFERENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAAM = "AAAAAAAAAA";
    private static final String UPDATED_NAAM = "BBBBBBBBBB";

    private static final String DEFAULT_RSIN = "AAAAAAAAAA";
    private static final String UPDATED_RSIN = "BBBBBBBBBB";

    private static final String DEFAULT_KVK = "AAAAAAAAAA";
    private static final String UPDATED_KVK = "BBBBBBBBBB";

    private static final String DEFAULT_KLANT_NUMMER = "AAAAAAAAAA";
    private static final String UPDATED_KLANT_NUMMER = "BBBBBBBBBB";

    private static final String DEFAULT_DATUM_UITSCHRIJVING_KVK = "AAAAAAAAAA";
    private static final String UPDATED_DATUM_UITSCHRIJVING_KVK = "BBBBBBBBBB";

    @Autowired
    private ErkendReferentRepository erkendReferentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restErkendReferentMockMvc;

    private ErkendReferent erkendReferent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErkendReferent createEntity(EntityManager em) {
        ErkendReferent erkendReferent = new ErkendReferent()
            .referentId(DEFAULT_REFERENT_ID)
            .naam(DEFAULT_NAAM)
            .rsin(DEFAULT_RSIN)
            .kvk(DEFAULT_KVK)
            .klantNummer(DEFAULT_KLANT_NUMMER)
            .datumUitschrijvingKvk(DEFAULT_DATUM_UITSCHRIJVING_KVK);
        return erkendReferent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErkendReferent createUpdatedEntity(EntityManager em) {
        ErkendReferent erkendReferent = new ErkendReferent()
            .referentId(UPDATED_REFERENT_ID)
            .naam(UPDATED_NAAM)
            .rsin(UPDATED_RSIN)
            .kvk(UPDATED_KVK)
            .klantNummer(UPDATED_KLANT_NUMMER)
            .datumUitschrijvingKvk(UPDATED_DATUM_UITSCHRIJVING_KVK);
        return erkendReferent;
    }

    @BeforeEach
    public void initTest() {
        erkendReferent = createEntity(em);
    }

    @Test
    @Transactional
    public void createErkendReferent() throws Exception {
        int databaseSizeBeforeCreate = erkendReferentRepository.findAll().size();
        // Create the ErkendReferent
        restErkendReferentMockMvc.perform(post("/api/erkend-referents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkendReferent)))
            .andExpect(status().isCreated());

        // Validate the ErkendReferent in the database
        List<ErkendReferent> erkendReferentList = erkendReferentRepository.findAll();
        assertThat(erkendReferentList).hasSize(databaseSizeBeforeCreate + 1);
        ErkendReferent testErkendReferent = erkendReferentList.get(erkendReferentList.size() - 1);
        assertThat(testErkendReferent.getReferentId()).isEqualTo(DEFAULT_REFERENT_ID);
        assertThat(testErkendReferent.getNaam()).isEqualTo(DEFAULT_NAAM);
        assertThat(testErkendReferent.getRsin()).isEqualTo(DEFAULT_RSIN);
        assertThat(testErkendReferent.getKvk()).isEqualTo(DEFAULT_KVK);
        assertThat(testErkendReferent.getKlantNummer()).isEqualTo(DEFAULT_KLANT_NUMMER);
        assertThat(testErkendReferent.getDatumUitschrijvingKvk()).isEqualTo(DEFAULT_DATUM_UITSCHRIJVING_KVK);
    }

    @Test
    @Transactional
    public void createErkendReferentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = erkendReferentRepository.findAll().size();

        // Create the ErkendReferent with an existing ID
        erkendReferent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restErkendReferentMockMvc.perform(post("/api/erkend-referents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkendReferent)))
            .andExpect(status().isBadRequest());

        // Validate the ErkendReferent in the database
        List<ErkendReferent> erkendReferentList = erkendReferentRepository.findAll();
        assertThat(erkendReferentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllErkendReferents() throws Exception {
        // Initialize the database
        erkendReferentRepository.saveAndFlush(erkendReferent);

        // Get all the erkendReferentList
        restErkendReferentMockMvc.perform(get("/api/erkend-referents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(erkendReferent.getId().intValue())))
            .andExpect(jsonPath("$.[*].referentId").value(hasItem(DEFAULT_REFERENT_ID)))
            .andExpect(jsonPath("$.[*].naam").value(hasItem(DEFAULT_NAAM)))
            .andExpect(jsonPath("$.[*].rsin").value(hasItem(DEFAULT_RSIN)))
            .andExpect(jsonPath("$.[*].kvk").value(hasItem(DEFAULT_KVK)))
            .andExpect(jsonPath("$.[*].klantNummer").value(hasItem(DEFAULT_KLANT_NUMMER)))
            .andExpect(jsonPath("$.[*].datumUitschrijvingKvk").value(hasItem(DEFAULT_DATUM_UITSCHRIJVING_KVK)));
    }
    
    @Test
    @Transactional
    public void getErkendReferent() throws Exception {
        // Initialize the database
        erkendReferentRepository.saveAndFlush(erkendReferent);

        // Get the erkendReferent
        restErkendReferentMockMvc.perform(get("/api/erkend-referents/{id}", erkendReferent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(erkendReferent.getId().intValue()))
            .andExpect(jsonPath("$.referentId").value(DEFAULT_REFERENT_ID))
            .andExpect(jsonPath("$.naam").value(DEFAULT_NAAM))
            .andExpect(jsonPath("$.rsin").value(DEFAULT_RSIN))
            .andExpect(jsonPath("$.kvk").value(DEFAULT_KVK))
            .andExpect(jsonPath("$.klantNummer").value(DEFAULT_KLANT_NUMMER))
            .andExpect(jsonPath("$.datumUitschrijvingKvk").value(DEFAULT_DATUM_UITSCHRIJVING_KVK));
    }
    @Test
    @Transactional
    public void getNonExistingErkendReferent() throws Exception {
        // Get the erkendReferent
        restErkendReferentMockMvc.perform(get("/api/erkend-referents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateErkendReferent() throws Exception {
        // Initialize the database
        erkendReferentRepository.saveAndFlush(erkendReferent);

        int databaseSizeBeforeUpdate = erkendReferentRepository.findAll().size();

        // Update the erkendReferent
        ErkendReferent updatedErkendReferent = erkendReferentRepository.findById(erkendReferent.getId()).get();
        // Disconnect from session so that the updates on updatedErkendReferent are not directly saved in db
        em.detach(updatedErkendReferent);
        updatedErkendReferent
            .referentId(UPDATED_REFERENT_ID)
            .naam(UPDATED_NAAM)
            .rsin(UPDATED_RSIN)
            .kvk(UPDATED_KVK)
            .klantNummer(UPDATED_KLANT_NUMMER)
            .datumUitschrijvingKvk(UPDATED_DATUM_UITSCHRIJVING_KVK);

        restErkendReferentMockMvc.perform(put("/api/erkend-referents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedErkendReferent)))
            .andExpect(status().isOk());

        // Validate the ErkendReferent in the database
        List<ErkendReferent> erkendReferentList = erkendReferentRepository.findAll();
        assertThat(erkendReferentList).hasSize(databaseSizeBeforeUpdate);
        ErkendReferent testErkendReferent = erkendReferentList.get(erkendReferentList.size() - 1);
        assertThat(testErkendReferent.getReferentId()).isEqualTo(UPDATED_REFERENT_ID);
        assertThat(testErkendReferent.getNaam()).isEqualTo(UPDATED_NAAM);
        assertThat(testErkendReferent.getRsin()).isEqualTo(UPDATED_RSIN);
        assertThat(testErkendReferent.getKvk()).isEqualTo(UPDATED_KVK);
        assertThat(testErkendReferent.getKlantNummer()).isEqualTo(UPDATED_KLANT_NUMMER);
        assertThat(testErkendReferent.getDatumUitschrijvingKvk()).isEqualTo(UPDATED_DATUM_UITSCHRIJVING_KVK);
    }

    @Test
    @Transactional
    public void updateNonExistingErkendReferent() throws Exception {
        int databaseSizeBeforeUpdate = erkendReferentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restErkendReferentMockMvc.perform(put("/api/erkend-referents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(erkendReferent)))
            .andExpect(status().isBadRequest());

        // Validate the ErkendReferent in the database
        List<ErkendReferent> erkendReferentList = erkendReferentRepository.findAll();
        assertThat(erkendReferentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteErkendReferent() throws Exception {
        // Initialize the database
        erkendReferentRepository.saveAndFlush(erkendReferent);

        int databaseSizeBeforeDelete = erkendReferentRepository.findAll().size();

        // Delete the erkendReferent
        restErkendReferentMockMvc.perform(delete("/api/erkend-referents/{id}", erkendReferent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ErkendReferent> erkendReferentList = erkendReferentRepository.findAll();
        assertThat(erkendReferentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
