package com.sth.gpweb.web.rest;

import com.sth.gpweb.GpwebApp;
import com.sth.gpweb.domain.Sub_grupo;
import com.sth.gpweb.repository.Sub_grupoRepository;
import com.sth.gpweb.service.Sub_grupoService;
import com.sth.gpweb.repository.search.Sub_grupoSearchRepository;

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
import java.math.BigDecimal;;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the Sub_grupoResource REST controller.
 *
 * @see Sub_grupoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GpwebApp.class)
@WebAppConfiguration
@IntegrationTest
public class Sub_grupoResourceIntTest {

    private static final String DEFAULT_NM_SUB_GRUPO = "AAAAA";
    private static final String UPDATED_NM_SUB_GRUPO = "BBBBB";

    private static final BigDecimal DEFAULT_VL_CUSTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VL_CUSTO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VL_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_VL_VALOR = new BigDecimal(2);

    private static final LocalDate DEFAULT_DT_OPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_OPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FL_ENVIO = false;
    private static final Boolean UPDATED_FL_ENVIO = true;

    private static final Integer DEFAULT_NN_NOVO = 1;
    private static final Integer UPDATED_NN_NOVO = 2;

    @Inject
    private Sub_grupoRepository sub_grupoRepository;

    @Inject
    private Sub_grupoService sub_grupoService;

    @Inject
    private Sub_grupoSearchRepository sub_grupoSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSub_grupoMockMvc;

    private Sub_grupo sub_grupo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Sub_grupoResource sub_grupoResource = new Sub_grupoResource();
        ReflectionTestUtils.setField(sub_grupoResource, "sub_grupoService", sub_grupoService);
        this.restSub_grupoMockMvc = MockMvcBuilders.standaloneSetup(sub_grupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        sub_grupoSearchRepository.deleteAll();
        sub_grupo = new Sub_grupo();
        sub_grupo.setNm_sub_grupo(DEFAULT_NM_SUB_GRUPO);
        sub_grupo.setVl_custo(DEFAULT_VL_CUSTO);
        sub_grupo.setVl_valor(DEFAULT_VL_VALOR);
        sub_grupo.setDt_operacao(DEFAULT_DT_OPERACAO);
        sub_grupo.setFl_envio(DEFAULT_FL_ENVIO);
        sub_grupo.setNn_novo(DEFAULT_NN_NOVO);
    }

    @Test
    @Transactional
    public void createSub_grupo() throws Exception {
        int databaseSizeBeforeCreate = sub_grupoRepository.findAll().size();

        // Create the Sub_grupo

        restSub_grupoMockMvc.perform(post("/api/sub-grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sub_grupo)))
                .andExpect(status().isCreated());

        // Validate the Sub_grupo in the database
        List<Sub_grupo> sub_grupos = sub_grupoRepository.findAll();
        assertThat(sub_grupos).hasSize(databaseSizeBeforeCreate + 1);
        Sub_grupo testSub_grupo = sub_grupos.get(sub_grupos.size() - 1);
        assertThat(testSub_grupo.getNm_sub_grupo()).isEqualTo(DEFAULT_NM_SUB_GRUPO);
        assertThat(testSub_grupo.getVl_custo()).isEqualTo(DEFAULT_VL_CUSTO);
        assertThat(testSub_grupo.getVl_valor()).isEqualTo(DEFAULT_VL_VALOR);
        assertThat(testSub_grupo.getDt_operacao()).isEqualTo(DEFAULT_DT_OPERACAO);
        assertThat(testSub_grupo.isFl_envio()).isEqualTo(DEFAULT_FL_ENVIO);
        assertThat(testSub_grupo.getNn_novo()).isEqualTo(DEFAULT_NN_NOVO);

        // Validate the Sub_grupo in ElasticSearch
        Sub_grupo sub_grupoEs = sub_grupoSearchRepository.findOne(testSub_grupo.getId());
        assertThat(sub_grupoEs).isEqualToComparingFieldByField(testSub_grupo);
    }

    @Test
    @Transactional
    public void checkNm_sub_grupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sub_grupoRepository.findAll().size();
        // set the field null
        sub_grupo.setNm_sub_grupo(null);

        // Create the Sub_grupo, which fails.

        restSub_grupoMockMvc.perform(post("/api/sub-grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sub_grupo)))
                .andExpect(status().isBadRequest());

        List<Sub_grupo> sub_grupos = sub_grupoRepository.findAll();
        assertThat(sub_grupos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSub_grupos() throws Exception {
        // Initialize the database
        sub_grupoRepository.saveAndFlush(sub_grupo);

        // Get all the sub_grupos
        restSub_grupoMockMvc.perform(get("/api/sub-grupos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sub_grupo.getId().intValue())))
                .andExpect(jsonPath("$.[*].nm_sub_grupo").value(hasItem(DEFAULT_NM_SUB_GRUPO.toString())))
                .andExpect(jsonPath("$.[*].vl_custo").value(hasItem(DEFAULT_VL_CUSTO.intValue())))
                .andExpect(jsonPath("$.[*].vl_valor").value(hasItem(DEFAULT_VL_VALOR.intValue())))
                .andExpect(jsonPath("$.[*].dt_operacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
                .andExpect(jsonPath("$.[*].fl_envio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
                .andExpect(jsonPath("$.[*].nn_novo").value(hasItem(DEFAULT_NN_NOVO)));
    }

    @Test
    @Transactional
    public void getSub_grupo() throws Exception {
        // Initialize the database
        sub_grupoRepository.saveAndFlush(sub_grupo);

        // Get the sub_grupo
        restSub_grupoMockMvc.perform(get("/api/sub-grupos/{id}", sub_grupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sub_grupo.getId().intValue()))
            .andExpect(jsonPath("$.nm_sub_grupo").value(DEFAULT_NM_SUB_GRUPO.toString()))
            .andExpect(jsonPath("$.vl_custo").value(DEFAULT_VL_CUSTO.intValue()))
            .andExpect(jsonPath("$.vl_valor").value(DEFAULT_VL_VALOR.intValue()))
            .andExpect(jsonPath("$.dt_operacao").value(DEFAULT_DT_OPERACAO.toString()))
            .andExpect(jsonPath("$.fl_envio").value(DEFAULT_FL_ENVIO.booleanValue()))
            .andExpect(jsonPath("$.nn_novo").value(DEFAULT_NN_NOVO));
    }

    @Test
    @Transactional
    public void getNonExistingSub_grupo() throws Exception {
        // Get the sub_grupo
        restSub_grupoMockMvc.perform(get("/api/sub-grupos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSub_grupo() throws Exception {
        // Initialize the database
        sub_grupoService.save(sub_grupo);

        int databaseSizeBeforeUpdate = sub_grupoRepository.findAll().size();

        // Update the sub_grupo
        Sub_grupo updatedSub_grupo = new Sub_grupo();
        updatedSub_grupo.setId(sub_grupo.getId());
        updatedSub_grupo.setNm_sub_grupo(UPDATED_NM_SUB_GRUPO);
        updatedSub_grupo.setVl_custo(UPDATED_VL_CUSTO);
        updatedSub_grupo.setVl_valor(UPDATED_VL_VALOR);
        updatedSub_grupo.setDt_operacao(UPDATED_DT_OPERACAO);
        updatedSub_grupo.setFl_envio(UPDATED_FL_ENVIO);
        updatedSub_grupo.setNn_novo(UPDATED_NN_NOVO);

        restSub_grupoMockMvc.perform(put("/api/sub-grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedSub_grupo)))
                .andExpect(status().isOk());

        // Validate the Sub_grupo in the database
        List<Sub_grupo> sub_grupos = sub_grupoRepository.findAll();
        assertThat(sub_grupos).hasSize(databaseSizeBeforeUpdate);
        Sub_grupo testSub_grupo = sub_grupos.get(sub_grupos.size() - 1);
        assertThat(testSub_grupo.getNm_sub_grupo()).isEqualTo(UPDATED_NM_SUB_GRUPO);
        assertThat(testSub_grupo.getVl_custo()).isEqualTo(UPDATED_VL_CUSTO);
        assertThat(testSub_grupo.getVl_valor()).isEqualTo(UPDATED_VL_VALOR);
        assertThat(testSub_grupo.getDt_operacao()).isEqualTo(UPDATED_DT_OPERACAO);
        assertThat(testSub_grupo.isFl_envio()).isEqualTo(UPDATED_FL_ENVIO);
        assertThat(testSub_grupo.getNn_novo()).isEqualTo(UPDATED_NN_NOVO);

        // Validate the Sub_grupo in ElasticSearch
        Sub_grupo sub_grupoEs = sub_grupoSearchRepository.findOne(testSub_grupo.getId());
        assertThat(sub_grupoEs).isEqualToComparingFieldByField(testSub_grupo);
    }

    @Test
    @Transactional
    public void deleteSub_grupo() throws Exception {
        // Initialize the database
        sub_grupoService.save(sub_grupo);

        int databaseSizeBeforeDelete = sub_grupoRepository.findAll().size();

        // Get the sub_grupo
        restSub_grupoMockMvc.perform(delete("/api/sub-grupos/{id}", sub_grupo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean sub_grupoExistsInEs = sub_grupoSearchRepository.exists(sub_grupo.getId());
        assertThat(sub_grupoExistsInEs).isFalse();

        // Validate the database is empty
        List<Sub_grupo> sub_grupos = sub_grupoRepository.findAll();
        assertThat(sub_grupos).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSub_grupo() throws Exception {
        // Initialize the database
        sub_grupoService.save(sub_grupo);

        // Search the sub_grupo
        restSub_grupoMockMvc.perform(get("/api/_search/sub-grupos?query=id:" + sub_grupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sub_grupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nm_sub_grupo").value(hasItem(DEFAULT_NM_SUB_GRUPO.toString())))
            .andExpect(jsonPath("$.[*].vl_custo").value(hasItem(DEFAULT_VL_CUSTO.intValue())))
            .andExpect(jsonPath("$.[*].vl_valor").value(hasItem(DEFAULT_VL_VALOR.intValue())))
            .andExpect(jsonPath("$.[*].dt_operacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
            .andExpect(jsonPath("$.[*].fl_envio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
            .andExpect(jsonPath("$.[*].nn_novo").value(hasItem(DEFAULT_NN_NOVO)));
    }
}
