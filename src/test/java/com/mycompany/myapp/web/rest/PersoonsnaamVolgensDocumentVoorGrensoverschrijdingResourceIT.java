package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.PersoonsnaamVolgensDocumentVoorGrensoverschrijding;
import com.mycompany.myapp.repository.PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository;

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
 * Integration tests for the {@link PersoonsnaamVolgensDocumentVoorGrensoverschrijdingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersoonsnaamVolgensDocumentVoorGrensoverschrijdingResourceIT {

    private static final String DEFAULT_VOORNAMEN = "AAAAAAAAAA";
    private static final String UPDATED_VOORNAMEN = "BBBBBBBBBB";

    private static final String DEFAULT_ACHTERNAAM = "AAAAAAAAAA";
    private static final String UPDATED_ACHTERNAAM = "BBBBBBBBBB";

    @Autowired
    private PersoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc;

    private PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersoonsnaamVolgensDocumentVoorGrensoverschrijding createEntity(EntityManager em) {
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding = new PersoonsnaamVolgensDocumentVoorGrensoverschrijding()
            .voornamen(DEFAULT_VOORNAMEN)
            .achternaam(DEFAULT_ACHTERNAAM);
        return persoonsnaamVolgensDocumentVoorGrensoverschrijding;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersoonsnaamVolgensDocumentVoorGrensoverschrijding createUpdatedEntity(EntityManager em) {
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding = new PersoonsnaamVolgensDocumentVoorGrensoverschrijding()
            .voornamen(UPDATED_VOORNAMEN)
            .achternaam(UPDATED_ACHTERNAAM);
        return persoonsnaamVolgensDocumentVoorGrensoverschrijding;
    }

    @BeforeEach
    public void initTest() {
        persoonsnaamVolgensDocumentVoorGrensoverschrijding = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        int databaseSizeBeforeCreate = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll().size();
        // Create the PersoonsnaamVolgensDocumentVoorGrensoverschrijding
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(post("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(persoonsnaamVolgensDocumentVoorGrensoverschrijding)))
            .andExpect(status().isCreated());

        // Validate the PersoonsnaamVolgensDocumentVoorGrensoverschrijding in the database
        List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijdingList = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList).hasSize(databaseSizeBeforeCreate + 1);
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding testPersoonsnaamVolgensDocumentVoorGrensoverschrijding = persoonsnaamVolgensDocumentVoorGrensoverschrijdingList.get(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList.size() - 1);
        assertThat(testPersoonsnaamVolgensDocumentVoorGrensoverschrijding.getVoornamen()).isEqualTo(DEFAULT_VOORNAMEN);
        assertThat(testPersoonsnaamVolgensDocumentVoorGrensoverschrijding.getAchternaam()).isEqualTo(DEFAULT_ACHTERNAAM);
    }

    @Test
    @Transactional
    public void createPersoonsnaamVolgensDocumentVoorGrensoverschrijdingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll().size();

        // Create the PersoonsnaamVolgensDocumentVoorGrensoverschrijding with an existing ID
        persoonsnaamVolgensDocumentVoorGrensoverschrijding.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(post("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(persoonsnaamVolgensDocumentVoorGrensoverschrijding)))
            .andExpect(status().isBadRequest());

        // Validate the PersoonsnaamVolgensDocumentVoorGrensoverschrijding in the database
        List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijdingList = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPersoonsnaamVolgensDocumentVoorGrensoverschrijdings() throws Exception {
        // Initialize the database
        persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.saveAndFlush(persoonsnaamVolgensDocumentVoorGrensoverschrijding);

        // Get all the persoonsnaamVolgensDocumentVoorGrensoverschrijdingList
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(get("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId().intValue())))
            .andExpect(jsonPath("$.[*].voornamen").value(hasItem(DEFAULT_VOORNAMEN)))
            .andExpect(jsonPath("$.[*].achternaam").value(hasItem(DEFAULT_ACHTERNAAM)));
    }
    
    @Test
    @Transactional
    public void getPersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        // Initialize the database
        persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.saveAndFlush(persoonsnaamVolgensDocumentVoorGrensoverschrijding);

        // Get the persoonsnaamVolgensDocumentVoorGrensoverschrijding
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(get("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings/{id}", persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId().intValue()))
            .andExpect(jsonPath("$.voornamen").value(DEFAULT_VOORNAMEN))
            .andExpect(jsonPath("$.achternaam").value(DEFAULT_ACHTERNAAM));
    }
    @Test
    @Transactional
    public void getNonExistingPersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        // Get the persoonsnaamVolgensDocumentVoorGrensoverschrijding
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(get("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        // Initialize the database
        persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.saveAndFlush(persoonsnaamVolgensDocumentVoorGrensoverschrijding);

        int databaseSizeBeforeUpdate = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll().size();

        // Update the persoonsnaamVolgensDocumentVoorGrensoverschrijding
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding updatedPersoonsnaamVolgensDocumentVoorGrensoverschrijding = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findById(persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId()).get();
        // Disconnect from session so that the updates on updatedPersoonsnaamVolgensDocumentVoorGrensoverschrijding are not directly saved in db
        em.detach(updatedPersoonsnaamVolgensDocumentVoorGrensoverschrijding);
        updatedPersoonsnaamVolgensDocumentVoorGrensoverschrijding
            .voornamen(UPDATED_VOORNAMEN)
            .achternaam(UPDATED_ACHTERNAAM);

        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(put("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersoonsnaamVolgensDocumentVoorGrensoverschrijding)))
            .andExpect(status().isOk());

        // Validate the PersoonsnaamVolgensDocumentVoorGrensoverschrijding in the database
        List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijdingList = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList).hasSize(databaseSizeBeforeUpdate);
        PersoonsnaamVolgensDocumentVoorGrensoverschrijding testPersoonsnaamVolgensDocumentVoorGrensoverschrijding = persoonsnaamVolgensDocumentVoorGrensoverschrijdingList.get(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList.size() - 1);
        assertThat(testPersoonsnaamVolgensDocumentVoorGrensoverschrijding.getVoornamen()).isEqualTo(UPDATED_VOORNAMEN);
        assertThat(testPersoonsnaamVolgensDocumentVoorGrensoverschrijding.getAchternaam()).isEqualTo(UPDATED_ACHTERNAAM);
    }

    @Test
    @Transactional
    public void updateNonExistingPersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        int databaseSizeBeforeUpdate = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(put("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(persoonsnaamVolgensDocumentVoorGrensoverschrijding)))
            .andExpect(status().isBadRequest());

        // Validate the PersoonsnaamVolgensDocumentVoorGrensoverschrijding in the database
        List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijdingList = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersoonsnaamVolgensDocumentVoorGrensoverschrijding() throws Exception {
        // Initialize the database
        persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.saveAndFlush(persoonsnaamVolgensDocumentVoorGrensoverschrijding);

        int databaseSizeBeforeDelete = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll().size();

        // Delete the persoonsnaamVolgensDocumentVoorGrensoverschrijding
        restPersoonsnaamVolgensDocumentVoorGrensoverschrijdingMockMvc.perform(delete("/api/persoonsnaam-volgens-document-voor-grensoverschrijdings/{id}", persoonsnaamVolgensDocumentVoorGrensoverschrijding.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersoonsnaamVolgensDocumentVoorGrensoverschrijding> persoonsnaamVolgensDocumentVoorGrensoverschrijdingList = persoonsnaamVolgensDocumentVoorGrensoverschrijdingRepository.findAll();
        assertThat(persoonsnaamVolgensDocumentVoorGrensoverschrijdingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
