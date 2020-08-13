package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Verblijfsdoel;
import com.mycompany.myapp.repository.VerblijfsdoelRepository;

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
 * Integration tests for the {@link VerblijfsdoelResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VerblijfsdoelResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private VerblijfsdoelRepository verblijfsdoelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVerblijfsdoelMockMvc;

    private Verblijfsdoel verblijfsdoel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verblijfsdoel createEntity(EntityManager em) {
        Verblijfsdoel verblijfsdoel = new Verblijfsdoel()
            .type(DEFAULT_TYPE);
        return verblijfsdoel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verblijfsdoel createUpdatedEntity(EntityManager em) {
        Verblijfsdoel verblijfsdoel = new Verblijfsdoel()
            .type(UPDATED_TYPE);
        return verblijfsdoel;
    }

    @BeforeEach
    public void initTest() {
        verblijfsdoel = createEntity(em);
    }

    @Test
    @Transactional
    public void createVerblijfsdoel() throws Exception {
        int databaseSizeBeforeCreate = verblijfsdoelRepository.findAll().size();
        // Create the Verblijfsdoel
        restVerblijfsdoelMockMvc.perform(post("/api/verblijfsdoels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijfsdoel)))
            .andExpect(status().isCreated());

        // Validate the Verblijfsdoel in the database
        List<Verblijfsdoel> verblijfsdoelList = verblijfsdoelRepository.findAll();
        assertThat(verblijfsdoelList).hasSize(databaseSizeBeforeCreate + 1);
        Verblijfsdoel testVerblijfsdoel = verblijfsdoelList.get(verblijfsdoelList.size() - 1);
        assertThat(testVerblijfsdoel.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createVerblijfsdoelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = verblijfsdoelRepository.findAll().size();

        // Create the Verblijfsdoel with an existing ID
        verblijfsdoel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVerblijfsdoelMockMvc.perform(post("/api/verblijfsdoels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijfsdoel)))
            .andExpect(status().isBadRequest());

        // Validate the Verblijfsdoel in the database
        List<Verblijfsdoel> verblijfsdoelList = verblijfsdoelRepository.findAll();
        assertThat(verblijfsdoelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVerblijfsdoels() throws Exception {
        // Initialize the database
        verblijfsdoelRepository.saveAndFlush(verblijfsdoel);

        // Get all the verblijfsdoelList
        restVerblijfsdoelMockMvc.perform(get("/api/verblijfsdoels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verblijfsdoel.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getVerblijfsdoel() throws Exception {
        // Initialize the database
        verblijfsdoelRepository.saveAndFlush(verblijfsdoel);

        // Get the verblijfsdoel
        restVerblijfsdoelMockMvc.perform(get("/api/verblijfsdoels/{id}", verblijfsdoel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(verblijfsdoel.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingVerblijfsdoel() throws Exception {
        // Get the verblijfsdoel
        restVerblijfsdoelMockMvc.perform(get("/api/verblijfsdoels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVerblijfsdoel() throws Exception {
        // Initialize the database
        verblijfsdoelRepository.saveAndFlush(verblijfsdoel);

        int databaseSizeBeforeUpdate = verblijfsdoelRepository.findAll().size();

        // Update the verblijfsdoel
        Verblijfsdoel updatedVerblijfsdoel = verblijfsdoelRepository.findById(verblijfsdoel.getId()).get();
        // Disconnect from session so that the updates on updatedVerblijfsdoel are not directly saved in db
        em.detach(updatedVerblijfsdoel);
        updatedVerblijfsdoel
            .type(UPDATED_TYPE);

        restVerblijfsdoelMockMvc.perform(put("/api/verblijfsdoels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVerblijfsdoel)))
            .andExpect(status().isOk());

        // Validate the Verblijfsdoel in the database
        List<Verblijfsdoel> verblijfsdoelList = verblijfsdoelRepository.findAll();
        assertThat(verblijfsdoelList).hasSize(databaseSizeBeforeUpdate);
        Verblijfsdoel testVerblijfsdoel = verblijfsdoelList.get(verblijfsdoelList.size() - 1);
        assertThat(testVerblijfsdoel.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingVerblijfsdoel() throws Exception {
        int databaseSizeBeforeUpdate = verblijfsdoelRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerblijfsdoelMockMvc.perform(put("/api/verblijfsdoels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verblijfsdoel)))
            .andExpect(status().isBadRequest());

        // Validate the Verblijfsdoel in the database
        List<Verblijfsdoel> verblijfsdoelList = verblijfsdoelRepository.findAll();
        assertThat(verblijfsdoelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVerblijfsdoel() throws Exception {
        // Initialize the database
        verblijfsdoelRepository.saveAndFlush(verblijfsdoel);

        int databaseSizeBeforeDelete = verblijfsdoelRepository.findAll().size();

        // Delete the verblijfsdoel
        restVerblijfsdoelMockMvc.perform(delete("/api/verblijfsdoels/{id}", verblijfsdoel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Verblijfsdoel> verblijfsdoelList = verblijfsdoelRepository.findAll();
        assertThat(verblijfsdoelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
