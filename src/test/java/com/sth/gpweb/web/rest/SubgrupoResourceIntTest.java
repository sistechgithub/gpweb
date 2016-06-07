package com.sth.gpweb.web.rest;

import com.sth.gpweb.GpwebApp;
import com.sth.gpweb.domain.Subgrupo;
import com.sth.gpweb.repository.SubgrupoRepository;
import com.sth.gpweb.service.SubgrupoService;
import com.sth.gpweb.repository.search.SubgrupoSearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the SubgrupoResource REST controller.
 *
 * @see SubgrupoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GpwebApp.class)
@WebAppConfiguration
@IntegrationTest
public class SubgrupoResourceIntTest {

    private static final String DEFAULT_NM_SUBGRUPO = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String UPDATED_NM_SUBGRUPO = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";

    private static final BigDecimal DEFAULT_VL_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VL_VALOR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VL_CUSTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VL_CUSTO = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_OPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_OPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FL_ENVIO = false;
    private static final Boolean UPDATED_FL_ENVIO = true;

    private static final Integer DEFAULT_NN_NOVO = 1;
    private static final Integer UPDATED_NN_NOVO = 2;

    @Inject
    private SubgrupoRepository subgrupoRepository;

    @Inject
    private SubgrupoService subgrupoService;

    @Inject
    private SubgrupoSearchRepository subgrupoSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSubgrupoMockMvc;

    private Subgrupo subgrupo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SubgrupoResource subgrupoResource = new SubgrupoResource();
        ReflectionTestUtils.setField(subgrupoResource, "subgrupoService", subgrupoService);
        this.restSubgrupoMockMvc = MockMvcBuilders.standaloneSetup(subgrupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        subgrupoSearchRepository.deleteAll();
        subgrupo = new Subgrupo();
        subgrupo.setNmSubgrupo(DEFAULT_NM_SUBGRUPO);
        subgrupo.setVlValor(DEFAULT_VL_VALOR);
        subgrupo.setVlCusto(DEFAULT_VL_CUSTO);
        subgrupo.setDtOperacao(DEFAULT_DT_OPERACAO);
        subgrupo.setFlEnvio(DEFAULT_FL_ENVIO);
        subgrupo.setNnNovo(DEFAULT_NN_NOVO);
    }

    @Test
    @Transactional
    public void createSubgrupo() throws Exception {
        int databaseSizeBeforeCreate = subgrupoRepository.findAll().size();

        // Create the Subgrupo

        restSubgrupoMockMvc.perform(post("/api/subgrupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(subgrupo)))
                .andExpect(status().isCreated());

        // Validate the Subgrupo in the database
        List<Subgrupo> subgrupos = subgrupoRepository.findAll();
        assertThat(subgrupos).hasSize(databaseSizeBeforeCreate + 1);
        Subgrupo testSubgrupo = subgrupos.get(subgrupos.size() - 1);
        assertThat(testSubgrupo.getNmSubgrupo()).isEqualTo(DEFAULT_NM_SUBGRUPO);
        assertThat(testSubgrupo.getVlValor()).isEqualTo(DEFAULT_VL_VALOR);
        assertThat(testSubgrupo.getVlCusto()).isEqualTo(DEFAULT_VL_CUSTO);
        assertThat(testSubgrupo.getDtOperacao()).isEqualTo(DEFAULT_DT_OPERACAO);
        assertThat(testSubgrupo.isFlEnvio()).isEqualTo(DEFAULT_FL_ENVIO);
        assertThat(testSubgrupo.getNnNovo()).isEqualTo(DEFAULT_NN_NOVO);

        // Validate the Subgrupo in ElasticSearch
        Subgrupo subgrupoEs = subgrupoSearchRepository.findOne(testSubgrupo.getId());
        assertThat(subgrupoEs).isEqualToComparingFieldByField(testSubgrupo);
    }

    @Test
    @Transactional
    public void checkNmSubgrupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = subgrupoRepository.findAll().size();
        // set the field null
        subgrupo.setNmSubgrupo(null);

        // Create the Subgrupo, which fails.

        restSubgrupoMockMvc.perform(post("/api/subgrupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(subgrupo)))
                .andExpect(status().isBadRequest());

        List<Subgrupo> subgrupos = subgrupoRepository.findAll();
        assertThat(subgrupos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubgrupos() throws Exception {
        // Initialize the database
        subgrupoRepository.saveAndFlush(subgrupo);

        // Get all the subgrupos
        restSubgrupoMockMvc.perform(get("/api/subgrupos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(subgrupo.getId().intValue())))
                .andExpect(jsonPath("$.[*].nmSubgrupo").value(hasItem(DEFAULT_NM_SUBGRUPO.toString())))
                .andExpect(jsonPath("$.[*].vlValor").value(hasItem(DEFAULT_VL_VALOR.intValue())))
                .andExpect(jsonPath("$.[*].vlCusto").value(hasItem(DEFAULT_VL_CUSTO.intValue())))
                .andExpect(jsonPath("$.[*].dtOperacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
                .andExpect(jsonPath("$.[*].flEnvio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
                .andExpect(jsonPath("$.[*].nnNovo").value(hasItem(DEFAULT_NN_NOVO)));
    }

    @Test
    @Transactional
    public void getSubgrupo() throws Exception {
        // Initialize the database
        subgrupoRepository.saveAndFlush(subgrupo);

        // Get the subgrupo
        restSubgrupoMockMvc.perform(get("/api/subgrupos/{id}", subgrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(subgrupo.getId().intValue()))
            .andExpect(jsonPath("$.nmSubgrupo").value(DEFAULT_NM_SUBGRUPO.toString()))
            .andExpect(jsonPath("$.vlValor").value(DEFAULT_VL_VALOR.intValue()))
            .andExpect(jsonPath("$.vlCusto").value(DEFAULT_VL_CUSTO.intValue()))
            .andExpect(jsonPath("$.dtOperacao").value(DEFAULT_DT_OPERACAO.toString()))
            .andExpect(jsonPath("$.flEnvio").value(DEFAULT_FL_ENVIO.booleanValue()))
            .andExpect(jsonPath("$.nnNovo").value(DEFAULT_NN_NOVO));
    }

    @Test
    @Transactional
    public void getNonExistingSubgrupo() throws Exception {
        // Get the subgrupo
        restSubgrupoMockMvc.perform(get("/api/subgrupos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubgrupo() throws Exception {
        // Initialize the database
        subgrupoService.save(subgrupo);

        int databaseSizeBeforeUpdate = subgrupoRepository.findAll().size();

        // Update the subgrupo
        Subgrupo updatedSubgrupo = new Subgrupo();
        updatedSubgrupo.setId(subgrupo.getId());
        updatedSubgrupo.setNmSubgrupo(UPDATED_NM_SUBGRUPO);
        updatedSubgrupo.setVlValor(UPDATED_VL_VALOR);
        updatedSubgrupo.setVlCusto(UPDATED_VL_CUSTO);
        updatedSubgrupo.setDtOperacao(UPDATED_DT_OPERACAO);
        updatedSubgrupo.setFlEnvio(UPDATED_FL_ENVIO);
        updatedSubgrupo.setNnNovo(UPDATED_NN_NOVO);

        restSubgrupoMockMvc.perform(put("/api/subgrupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedSubgrupo)))
                .andExpect(status().isOk());

        // Validate the Subgrupo in the database
        List<Subgrupo> subgrupos = subgrupoRepository.findAll();
        assertThat(subgrupos).hasSize(databaseSizeBeforeUpdate);
        Subgrupo testSubgrupo = subgrupos.get(subgrupos.size() - 1);
        assertThat(testSubgrupo.getNmSubgrupo()).isEqualTo(UPDATED_NM_SUBGRUPO);
        assertThat(testSubgrupo.getVlValor()).isEqualTo(UPDATED_VL_VALOR);
        assertThat(testSubgrupo.getVlCusto()).isEqualTo(UPDATED_VL_CUSTO);
        assertThat(testSubgrupo.getDtOperacao()).isEqualTo(UPDATED_DT_OPERACAO);
        assertThat(testSubgrupo.isFlEnvio()).isEqualTo(UPDATED_FL_ENVIO);
        assertThat(testSubgrupo.getNnNovo()).isEqualTo(UPDATED_NN_NOVO);

        // Validate the Subgrupo in ElasticSearch
        Subgrupo subgrupoEs = subgrupoSearchRepository.findOne(testSubgrupo.getId());
        assertThat(subgrupoEs).isEqualToComparingFieldByField(testSubgrupo);
    }

    @Test
    @Transactional
    public void deleteSubgrupo() throws Exception {
        // Initialize the database
        subgrupoService.save(subgrupo);

        int databaseSizeBeforeDelete = subgrupoRepository.findAll().size();

        // Get the subgrupo
        restSubgrupoMockMvc.perform(delete("/api/subgrupos/{id}", subgrupo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean subgrupoExistsInEs = subgrupoSearchRepository.exists(subgrupo.getId());
        assertThat(subgrupoExistsInEs).isFalse();

        // Validate the database is empty
        List<Subgrupo> subgrupos = subgrupoRepository.findAll();
        assertThat(subgrupos).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSubgrupo() throws Exception {
        // Initialize the database
        subgrupoService.save(subgrupo);

        // Search the subgrupo
        restSubgrupoMockMvc.perform(get("/api/_search/subgrupos?query=id:" + subgrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subgrupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nmSubgrupo").value(hasItem(DEFAULT_NM_SUBGRUPO.toString())))
            .andExpect(jsonPath("$.[*].vlValor").value(hasItem(DEFAULT_VL_VALOR.intValue())))
            .andExpect(jsonPath("$.[*].vlCusto").value(hasItem(DEFAULT_VL_CUSTO.intValue())))
            .andExpect(jsonPath("$.[*].dtOperacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
            .andExpect(jsonPath("$.[*].flEnvio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
            .andExpect(jsonPath("$.[*].nnNovo").value(hasItem(DEFAULT_NN_NOVO)));
    }
}
