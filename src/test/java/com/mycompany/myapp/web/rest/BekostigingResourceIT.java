package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Bekostiging;
import com.mycompany.myapp.repository.BekostigingRepository;

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
 * Integration tests for the {@link BekostigingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BekostigingResourceIT {

    private static final String DEFAULT_BURGERSERVICENUMMER = "AAAAAAAAAA";
    private static final String UPDATED_BURGERSERVICENUMMER = "BBBBBBBBBB";

    private static final Integer DEFAULT_BRUTO_MAANDSALARIS = 1;
    private static final Integer UPDATED_BRUTO_MAANDSALARIS = 2;

    @Autowired
    private BekostigingRepository bekostigingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBekostigingMockMvc;

    private Bekostiging bekostiging;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bekostiging createEntity(EntityManager em) {
        Bekostiging bekostiging = new Bekostiging()
            .burgerservicenummer(DEFAULT_BURGERSERVICENUMMER)
            .brutoMaandsalaris(DEFAULT_BRUTO_MAANDSALARIS);
        return bekostiging;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bekostiging createUpdatedEntity(EntityManager em) {
        Bekostiging bekostiging = new Bekostiging()
            .burgerservicenummer(UPDATED_BURGERSERVICENUMMER)
            .brutoMaandsalaris(UPDATED_BRUTO_MAANDSALARIS);
        return bekostiging;
    }

    @BeforeEach
    public void initTest() {
        bekostiging = createEntity(em);
    }

    @Test
    @Transactional
    public void createBekostiging() throws Exception {
        int databaseSizeBeforeCreate = bekostigingRepository.findAll().size();
        // Create the Bekostiging
        restBekostigingMockMvc.perform(post("/api/bekostigings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bekostiging)))
            .andExpect(status().isCreated());

        // Validate the Bekostiging in the database
        List<Bekostiging> bekostigingList = bekostigingRepository.findAll();
        assertThat(bekostigingList).hasSize(databaseSizeBeforeCreate + 1);
        Bekostiging testBekostiging = bekostigingList.get(bekostigingList.size() - 1);
        assertThat(testBekostiging.getBurgerservicenummer()).isEqualTo(DEFAULT_BURGERSERVICENUMMER);
        assertThat(testBekostiging.getBrutoMaandsalaris()).isEqualTo(DEFAULT_BRUTO_MAANDSALARIS);
    }

    @Test
    @Transactional
    public void createBekostigingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bekostigingRepository.findAll().size();

        // Create the Bekostiging with an existing ID
        bekostiging.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBekostigingMockMvc.perform(post("/api/bekostigings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bekostiging)))
            .andExpect(status().isBadRequest());

        // Validate the Bekostiging in the database
        List<Bekostiging> bekostigingList = bekostigingRepository.findAll();
        assertThat(bekostigingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBekostigings() throws Exception {
        // Initialize the database
        bekostigingRepository.saveAndFlush(bekostiging);

        // Get all the bekostigingList
        restBekostigingMockMvc.perform(get("/api/bekostigings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bekostiging.getId().intValue())))
            .andExpect(jsonPath("$.[*].burgerservicenummer").value(hasItem(DEFAULT_BURGERSERVICENUMMER)))
            .andExpect(jsonPath("$.[*].brutoMaandsalaris").value(hasItem(DEFAULT_BRUTO_MAANDSALARIS)));
    }
    
    @Test
    @Transactional
    public void getBekostiging() throws Exception {
        // Initialize the database
        bekostigingRepository.saveAndFlush(bekostiging);

        // Get the bekostiging
        restBekostigingMockMvc.perform(get("/api/bekostigings/{id}", bekostiging.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bekostiging.getId().intValue()))
            .andExpect(jsonPath("$.burgerservicenummer").value(DEFAULT_BURGERSERVICENUMMER))
            .andExpect(jsonPath("$.brutoMaandsalaris").value(DEFAULT_BRUTO_MAANDSALARIS));
    }
    @Test
    @Transactional
    public void getNonExistingBekostiging() throws Exception {
        // Get the bekostiging
        restBekostigingMockMvc.perform(get("/api/bekostigings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBekostiging() throws Exception {
        // Initialize the database
        bekostigingRepository.saveAndFlush(bekostiging);

        int databaseSizeBeforeUpdate = bekostigingRepository.findAll().size();

        // Update the bekostiging
        Bekostiging updatedBekostiging = bekostigingRepository.findById(bekostiging.getId()).get();
        // Disconnect from session so that the updates on updatedBekostiging are not directly saved in db
        em.detach(updatedBekostiging);
        updatedBekostiging
            .burgerservicenummer(UPDATED_BURGERSERVICENUMMER)
            .brutoMaandsalaris(UPDATED_BRUTO_MAANDSALARIS);

        restBekostigingMockMvc.perform(put("/api/bekostigings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBekostiging)))
            .andExpect(status().isOk());

        // Validate the Bekostiging in the database
        List<Bekostiging> bekostigingList = bekostigingRepository.findAll();
        assertThat(bekostigingList).hasSize(databaseSizeBeforeUpdate);
        Bekostiging testBekostiging = bekostigingList.get(bekostigingList.size() - 1);
        assertThat(testBekostiging.getBurgerservicenummer()).isEqualTo(UPDATED_BURGERSERVICENUMMER);
        assertThat(testBekostiging.getBrutoMaandsalaris()).isEqualTo(UPDATED_BRUTO_MAANDSALARIS);
    }

    @Test
    @Transactional
    public void updateNonExistingBekostiging() throws Exception {
        int databaseSizeBeforeUpdate = bekostigingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBekostigingMockMvc.perform(put("/api/bekostigings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bekostiging)))
            .andExpect(status().isBadRequest());

        // Validate the Bekostiging in the database
        List<Bekostiging> bekostigingList = bekostigingRepository.findAll();
        assertThat(bekostigingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBekostiging() throws Exception {
        // Initialize the database
        bekostigingRepository.saveAndFlush(bekostiging);

        int databaseSizeBeforeDelete = bekostigingRepository.findAll().size();

        // Delete the bekostiging
        restBekostigingMockMvc.perform(delete("/api/bekostigings/{id}", bekostiging.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bekostiging> bekostigingList = bekostigingRepository.findAll();
        assertThat(bekostigingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
