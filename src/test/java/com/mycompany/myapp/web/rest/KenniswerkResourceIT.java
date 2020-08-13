package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Kenniswerk;
import com.mycompany.myapp.repository.KenniswerkRepository;

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
 * Integration tests for the {@link KenniswerkResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KenniswerkResourceIT {

    private static final String DEFAULT_FUNCTIE = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BRUTO_MAANDSALARIS = 1;
    private static final Integer UPDATED_BRUTO_MAANDSALARIS = 2;

    private static final String DEFAULT_UFO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_UFO_CODE = "BBBBBBBBBB";

    @Autowired
    private KenniswerkRepository kenniswerkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKenniswerkMockMvc;

    private Kenniswerk kenniswerk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kenniswerk createEntity(EntityManager em) {
        Kenniswerk kenniswerk = new Kenniswerk()
            .functie(DEFAULT_FUNCTIE)
            .brutoMaandsalaris(DEFAULT_BRUTO_MAANDSALARIS)
            .ufoCode(DEFAULT_UFO_CODE);
        return kenniswerk;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kenniswerk createUpdatedEntity(EntityManager em) {
        Kenniswerk kenniswerk = new Kenniswerk()
            .functie(UPDATED_FUNCTIE)
            .brutoMaandsalaris(UPDATED_BRUTO_MAANDSALARIS)
            .ufoCode(UPDATED_UFO_CODE);
        return kenniswerk;
    }

    @BeforeEach
    public void initTest() {
        kenniswerk = createEntity(em);
    }

    @Test
    @Transactional
    public void createKenniswerk() throws Exception {
        int databaseSizeBeforeCreate = kenniswerkRepository.findAll().size();
        // Create the Kenniswerk
        restKenniswerkMockMvc.perform(post("/api/kenniswerks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kenniswerk)))
            .andExpect(status().isCreated());

        // Validate the Kenniswerk in the database
        List<Kenniswerk> kenniswerkList = kenniswerkRepository.findAll();
        assertThat(kenniswerkList).hasSize(databaseSizeBeforeCreate + 1);
        Kenniswerk testKenniswerk = kenniswerkList.get(kenniswerkList.size() - 1);
        assertThat(testKenniswerk.getFunctie()).isEqualTo(DEFAULT_FUNCTIE);
        assertThat(testKenniswerk.getBrutoMaandsalaris()).isEqualTo(DEFAULT_BRUTO_MAANDSALARIS);
        assertThat(testKenniswerk.getUfoCode()).isEqualTo(DEFAULT_UFO_CODE);
    }

    @Test
    @Transactional
    public void createKenniswerkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kenniswerkRepository.findAll().size();

        // Create the Kenniswerk with an existing ID
        kenniswerk.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKenniswerkMockMvc.perform(post("/api/kenniswerks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kenniswerk)))
            .andExpect(status().isBadRequest());

        // Validate the Kenniswerk in the database
        List<Kenniswerk> kenniswerkList = kenniswerkRepository.findAll();
        assertThat(kenniswerkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllKenniswerks() throws Exception {
        // Initialize the database
        kenniswerkRepository.saveAndFlush(kenniswerk);

        // Get all the kenniswerkList
        restKenniswerkMockMvc.perform(get("/api/kenniswerks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kenniswerk.getId().intValue())))
            .andExpect(jsonPath("$.[*].functie").value(hasItem(DEFAULT_FUNCTIE)))
            .andExpect(jsonPath("$.[*].brutoMaandsalaris").value(hasItem(DEFAULT_BRUTO_MAANDSALARIS)))
            .andExpect(jsonPath("$.[*].ufoCode").value(hasItem(DEFAULT_UFO_CODE)));
    }
    
    @Test
    @Transactional
    public void getKenniswerk() throws Exception {
        // Initialize the database
        kenniswerkRepository.saveAndFlush(kenniswerk);

        // Get the kenniswerk
        restKenniswerkMockMvc.perform(get("/api/kenniswerks/{id}", kenniswerk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kenniswerk.getId().intValue()))
            .andExpect(jsonPath("$.functie").value(DEFAULT_FUNCTIE))
            .andExpect(jsonPath("$.brutoMaandsalaris").value(DEFAULT_BRUTO_MAANDSALARIS))
            .andExpect(jsonPath("$.ufoCode").value(DEFAULT_UFO_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingKenniswerk() throws Exception {
        // Get the kenniswerk
        restKenniswerkMockMvc.perform(get("/api/kenniswerks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKenniswerk() throws Exception {
        // Initialize the database
        kenniswerkRepository.saveAndFlush(kenniswerk);

        int databaseSizeBeforeUpdate = kenniswerkRepository.findAll().size();

        // Update the kenniswerk
        Kenniswerk updatedKenniswerk = kenniswerkRepository.findById(kenniswerk.getId()).get();
        // Disconnect from session so that the updates on updatedKenniswerk are not directly saved in db
        em.detach(updatedKenniswerk);
        updatedKenniswerk
            .functie(UPDATED_FUNCTIE)
            .brutoMaandsalaris(UPDATED_BRUTO_MAANDSALARIS)
            .ufoCode(UPDATED_UFO_CODE);

        restKenniswerkMockMvc.perform(put("/api/kenniswerks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedKenniswerk)))
            .andExpect(status().isOk());

        // Validate the Kenniswerk in the database
        List<Kenniswerk> kenniswerkList = kenniswerkRepository.findAll();
        assertThat(kenniswerkList).hasSize(databaseSizeBeforeUpdate);
        Kenniswerk testKenniswerk = kenniswerkList.get(kenniswerkList.size() - 1);
        assertThat(testKenniswerk.getFunctie()).isEqualTo(UPDATED_FUNCTIE);
        assertThat(testKenniswerk.getBrutoMaandsalaris()).isEqualTo(UPDATED_BRUTO_MAANDSALARIS);
        assertThat(testKenniswerk.getUfoCode()).isEqualTo(UPDATED_UFO_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingKenniswerk() throws Exception {
        int databaseSizeBeforeUpdate = kenniswerkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKenniswerkMockMvc.perform(put("/api/kenniswerks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kenniswerk)))
            .andExpect(status().isBadRequest());

        // Validate the Kenniswerk in the database
        List<Kenniswerk> kenniswerkList = kenniswerkRepository.findAll();
        assertThat(kenniswerkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKenniswerk() throws Exception {
        // Initialize the database
        kenniswerkRepository.saveAndFlush(kenniswerk);

        int databaseSizeBeforeDelete = kenniswerkRepository.findAll().size();

        // Delete the kenniswerk
        restKenniswerkMockMvc.perform(delete("/api/kenniswerks/{id}", kenniswerk.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Kenniswerk> kenniswerkList = kenniswerkRepository.findAll();
        assertThat(kenniswerkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
