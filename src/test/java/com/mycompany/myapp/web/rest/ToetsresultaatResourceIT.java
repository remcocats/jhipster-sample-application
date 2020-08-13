package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Toetsresultaat;
import com.mycompany.myapp.repository.ToetsresultaatRepository;

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
 * Integration tests for the {@link ToetsresultaatResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ToetsresultaatResourceIT {

    private static final String DEFAULT_OMSCHRIJVING = "AAAAAAAAAA";
    private static final String UPDATED_OMSCHRIJVING = "BBBBBBBBBB";

    private static final Boolean DEFAULT_WAARDE = false;
    private static final Boolean UPDATED_WAARDE = true;

    @Autowired
    private ToetsresultaatRepository toetsresultaatRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restToetsresultaatMockMvc;

    private Toetsresultaat toetsresultaat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Toetsresultaat createEntity(EntityManager em) {
        Toetsresultaat toetsresultaat = new Toetsresultaat()
            .omschrijving(DEFAULT_OMSCHRIJVING)
            .waarde(DEFAULT_WAARDE);
        return toetsresultaat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Toetsresultaat createUpdatedEntity(EntityManager em) {
        Toetsresultaat toetsresultaat = new Toetsresultaat()
            .omschrijving(UPDATED_OMSCHRIJVING)
            .waarde(UPDATED_WAARDE);
        return toetsresultaat;
    }

    @BeforeEach
    public void initTest() {
        toetsresultaat = createEntity(em);
    }

    @Test
    @Transactional
    public void createToetsresultaat() throws Exception {
        int databaseSizeBeforeCreate = toetsresultaatRepository.findAll().size();
        // Create the Toetsresultaat
        restToetsresultaatMockMvc.perform(post("/api/toetsresultaats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(toetsresultaat)))
            .andExpect(status().isCreated());

        // Validate the Toetsresultaat in the database
        List<Toetsresultaat> toetsresultaatList = toetsresultaatRepository.findAll();
        assertThat(toetsresultaatList).hasSize(databaseSizeBeforeCreate + 1);
        Toetsresultaat testToetsresultaat = toetsresultaatList.get(toetsresultaatList.size() - 1);
        assertThat(testToetsresultaat.getOmschrijving()).isEqualTo(DEFAULT_OMSCHRIJVING);
        assertThat(testToetsresultaat.isWaarde()).isEqualTo(DEFAULT_WAARDE);
    }

    @Test
    @Transactional
    public void createToetsresultaatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = toetsresultaatRepository.findAll().size();

        // Create the Toetsresultaat with an existing ID
        toetsresultaat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restToetsresultaatMockMvc.perform(post("/api/toetsresultaats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(toetsresultaat)))
            .andExpect(status().isBadRequest());

        // Validate the Toetsresultaat in the database
        List<Toetsresultaat> toetsresultaatList = toetsresultaatRepository.findAll();
        assertThat(toetsresultaatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllToetsresultaats() throws Exception {
        // Initialize the database
        toetsresultaatRepository.saveAndFlush(toetsresultaat);

        // Get all the toetsresultaatList
        restToetsresultaatMockMvc.perform(get("/api/toetsresultaats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toetsresultaat.getId().intValue())))
            .andExpect(jsonPath("$.[*].omschrijving").value(hasItem(DEFAULT_OMSCHRIJVING)))
            .andExpect(jsonPath("$.[*].waarde").value(hasItem(DEFAULT_WAARDE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getToetsresultaat() throws Exception {
        // Initialize the database
        toetsresultaatRepository.saveAndFlush(toetsresultaat);

        // Get the toetsresultaat
        restToetsresultaatMockMvc.perform(get("/api/toetsresultaats/{id}", toetsresultaat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(toetsresultaat.getId().intValue()))
            .andExpect(jsonPath("$.omschrijving").value(DEFAULT_OMSCHRIJVING))
            .andExpect(jsonPath("$.waarde").value(DEFAULT_WAARDE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingToetsresultaat() throws Exception {
        // Get the toetsresultaat
        restToetsresultaatMockMvc.perform(get("/api/toetsresultaats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateToetsresultaat() throws Exception {
        // Initialize the database
        toetsresultaatRepository.saveAndFlush(toetsresultaat);

        int databaseSizeBeforeUpdate = toetsresultaatRepository.findAll().size();

        // Update the toetsresultaat
        Toetsresultaat updatedToetsresultaat = toetsresultaatRepository.findById(toetsresultaat.getId()).get();
        // Disconnect from session so that the updates on updatedToetsresultaat are not directly saved in db
        em.detach(updatedToetsresultaat);
        updatedToetsresultaat
            .omschrijving(UPDATED_OMSCHRIJVING)
            .waarde(UPDATED_WAARDE);

        restToetsresultaatMockMvc.perform(put("/api/toetsresultaats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedToetsresultaat)))
            .andExpect(status().isOk());

        // Validate the Toetsresultaat in the database
        List<Toetsresultaat> toetsresultaatList = toetsresultaatRepository.findAll();
        assertThat(toetsresultaatList).hasSize(databaseSizeBeforeUpdate);
        Toetsresultaat testToetsresultaat = toetsresultaatList.get(toetsresultaatList.size() - 1);
        assertThat(testToetsresultaat.getOmschrijving()).isEqualTo(UPDATED_OMSCHRIJVING);
        assertThat(testToetsresultaat.isWaarde()).isEqualTo(UPDATED_WAARDE);
    }

    @Test
    @Transactional
    public void updateNonExistingToetsresultaat() throws Exception {
        int databaseSizeBeforeUpdate = toetsresultaatRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToetsresultaatMockMvc.perform(put("/api/toetsresultaats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(toetsresultaat)))
            .andExpect(status().isBadRequest());

        // Validate the Toetsresultaat in the database
        List<Toetsresultaat> toetsresultaatList = toetsresultaatRepository.findAll();
        assertThat(toetsresultaatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteToetsresultaat() throws Exception {
        // Initialize the database
        toetsresultaatRepository.saveAndFlush(toetsresultaat);

        int databaseSizeBeforeDelete = toetsresultaatRepository.findAll().size();

        // Delete the toetsresultaat
        restToetsresultaatMockMvc.perform(delete("/api/toetsresultaats/{id}", toetsresultaat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Toetsresultaat> toetsresultaatList = toetsresultaatRepository.findAll();
        assertThat(toetsresultaatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
