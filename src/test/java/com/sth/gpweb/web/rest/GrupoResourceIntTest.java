package com.sth.gpweb.web.rest;

import com.sth.gpweb.GpwebApp;
import com.sth.gpweb.domain.Grupo;
import com.sth.gpweb.repository.GrupoRepository;
import com.sth.gpweb.service.GrupoService;
import com.sth.gpweb.repository.search.GrupoSearchRepository;

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
 * Test class for the GrupoResource REST controller.
 *
 * @see GrupoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GpwebApp.class)
@WebAppConfiguration
@IntegrationTest
public class GrupoResourceIntTest {

    private static final String DEFAULT_NM_GRUPO = "A";
    private static final String UPDATED_NM_GRUPO = "B";

    private static final BigDecimal DEFAULT_VL_COMISSAO = new BigDecimal(100);
    private static final BigDecimal UPDATED_VL_COMISSAO = new BigDecimal(99);

    private static final BigDecimal DEFAULT_VL_DESCONTO = new BigDecimal(100);
    private static final BigDecimal UPDATED_VL_DESCONTO = new BigDecimal(99);

    private static final Boolean DEFAULT_FL_PROMO = false;
    private static final Boolean UPDATED_FL_PROMO = true;

    private static final Boolean DEFAULT_FL_DESCO = false;
    private static final Boolean UPDATED_FL_DESCO = true;

    private static final LocalDate DEFAULT_DT_PROMO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_PROMO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_OPERACAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_OPERACAO = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_FL_SEM_CONTAGEM = false;
    private static final Boolean UPDATED_FL_SEM_CONTAGEM = true;

    private static final Boolean DEFAULT_FL_ENVIO = false;
    private static final Boolean UPDATED_FL_ENVIO = true;

    private static final Integer DEFAULT_NN_NOVO = 1;
    private static final Integer UPDATED_NN_NOVO = 2;

    private static final Integer DEFAULT_NN_DIA = 1;
    private static final Integer UPDATED_NN_DIA = 2;
    private static final String DEFAULT_NN_DIA_SEMANA = "AAAAA";
    private static final String UPDATED_NN_DIA_SEMANA = "BBBBB";

    private static final Integer DEFAULT_NN_TIPO = 1;
    private static final Integer UPDATED_NN_TIPO = 2;

    @Inject
    private GrupoRepository grupoRepository;

    @Inject
    private GrupoService grupoService;

    @Inject
    private GrupoSearchRepository grupoSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGrupoMockMvc;

    private Grupo grupo;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GrupoResource grupoResource = new GrupoResource();
        ReflectionTestUtils.setField(grupoResource, "grupoService", grupoService);
        this.restGrupoMockMvc = MockMvcBuilders.standaloneSetup(grupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        grupoSearchRepository.deleteAll();
        grupo = new Grupo();
        grupo.setNmGrupo(DEFAULT_NM_GRUPO);
        grupo.setVlComissao(DEFAULT_VL_COMISSAO);
        grupo.setVlDesconto(DEFAULT_VL_DESCONTO);
        grupo.setFlPromo(DEFAULT_FL_PROMO);
        grupo.setFlDesco(DEFAULT_FL_DESCO);
        grupo.setDtPromo(DEFAULT_DT_PROMO);
        grupo.setDtOperacao(DEFAULT_DT_OPERACAO);
        grupo.setFlSemContagem(DEFAULT_FL_SEM_CONTAGEM);
        grupo.setFlEnvio(DEFAULT_FL_ENVIO);
        grupo.setNnNovo(DEFAULT_NN_NOVO);
        grupo.setNnDia(DEFAULT_NN_DIA);
        grupo.setNnDiaSemana(DEFAULT_NN_DIA_SEMANA);
        grupo.setNnTipo(DEFAULT_NN_TIPO);
    }

    @Test
    @Transactional
    public void createGrupo() throws Exception {
        int databaseSizeBeforeCreate = grupoRepository.findAll().size();

        // Create the Grupo

        restGrupoMockMvc.perform(post("/api/grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(grupo)))
                .andExpect(status().isCreated());

        // Validate the Grupo in the database
        List<Grupo> grupos = grupoRepository.findAll();
        assertThat(grupos).hasSize(databaseSizeBeforeCreate + 1);
        Grupo testGrupo = grupos.get(grupos.size() - 1);
        assertThat(testGrupo.getNmGrupo()).isEqualTo(DEFAULT_NM_GRUPO);
        assertThat(testGrupo.getVlComissao()).isEqualTo(DEFAULT_VL_COMISSAO);
        assertThat(testGrupo.getVlDesconto()).isEqualTo(DEFAULT_VL_DESCONTO);
        assertThat(testGrupo.isFlPromo()).isEqualTo(DEFAULT_FL_PROMO);
        assertThat(testGrupo.isFlDesco()).isEqualTo(DEFAULT_FL_DESCO);
        assertThat(testGrupo.getDtPromo()).isEqualTo(DEFAULT_DT_PROMO);
        assertThat(testGrupo.getDtOperacao()).isEqualTo(DEFAULT_DT_OPERACAO);
        assertThat(testGrupo.isFlSemContagem()).isEqualTo(DEFAULT_FL_SEM_CONTAGEM);
        assertThat(testGrupo.isFlEnvio()).isEqualTo(DEFAULT_FL_ENVIO);
        assertThat(testGrupo.getNnNovo()).isEqualTo(DEFAULT_NN_NOVO);
        assertThat(testGrupo.getNnDia()).isEqualTo(DEFAULT_NN_DIA);
        assertThat(testGrupo.getNnDiaSemana()).isEqualTo(DEFAULT_NN_DIA_SEMANA);
        assertThat(testGrupo.getNnTipo()).isEqualTo(DEFAULT_NN_TIPO);

        // Validate the Grupo in ElasticSearch
        Grupo grupoEs = grupoSearchRepository.findOne(testGrupo.getId());
        assertThat(grupoEs).isEqualToComparingFieldByField(testGrupo);
    }

    @Test
    @Transactional
    public void checkNmGrupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = grupoRepository.findAll().size();
        // set the field null
        grupo.setNmGrupo(null);

        // Create the Grupo, which fails.

        restGrupoMockMvc.perform(post("/api/grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(grupo)))
                .andExpect(status().isBadRequest());

        List<Grupo> grupos = grupoRepository.findAll();
        assertThat(grupos).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGrupos() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        // Get all the grupos
        restGrupoMockMvc.perform(get("/api/grupos?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(grupo.getId().intValue())))
                .andExpect(jsonPath("$.[*].nmGrupo").value(hasItem(DEFAULT_NM_GRUPO.toString())))
                .andExpect(jsonPath("$.[*].vlComissao").value(hasItem(DEFAULT_VL_COMISSAO.intValue())))
                .andExpect(jsonPath("$.[*].vlDesconto").value(hasItem(DEFAULT_VL_DESCONTO.intValue())))
                .andExpect(jsonPath("$.[*].flPromo").value(hasItem(DEFAULT_FL_PROMO.booleanValue())))
                .andExpect(jsonPath("$.[*].flDesco").value(hasItem(DEFAULT_FL_DESCO.booleanValue())))
                .andExpect(jsonPath("$.[*].dtPromo").value(hasItem(DEFAULT_DT_PROMO.toString())))
                .andExpect(jsonPath("$.[*].dtOperacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
                .andExpect(jsonPath("$.[*].flSemContagem").value(hasItem(DEFAULT_FL_SEM_CONTAGEM.booleanValue())))
                .andExpect(jsonPath("$.[*].flEnvio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
                .andExpect(jsonPath("$.[*].nnNovo").value(hasItem(DEFAULT_NN_NOVO)))
                .andExpect(jsonPath("$.[*].nnDia").value(hasItem(DEFAULT_NN_DIA)))
                .andExpect(jsonPath("$.[*].nnDiaSemana").value(hasItem(DEFAULT_NN_DIA_SEMANA.toString())))
                .andExpect(jsonPath("$.[*].nnTipo").value(hasItem(DEFAULT_NN_TIPO)));
    }

    @Test
    @Transactional
    public void getGrupo() throws Exception {
        // Initialize the database
        grupoRepository.saveAndFlush(grupo);

        // Get the grupo
        restGrupoMockMvc.perform(get("/api/grupos/{id}", grupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(grupo.getId().intValue()))
            .andExpect(jsonPath("$.nmGrupo").value(DEFAULT_NM_GRUPO.toString()))
            .andExpect(jsonPath("$.vlComissao").value(DEFAULT_VL_COMISSAO.intValue()))
            .andExpect(jsonPath("$.vlDesconto").value(DEFAULT_VL_DESCONTO.intValue()))
            .andExpect(jsonPath("$.flPromo").value(DEFAULT_FL_PROMO.booleanValue()))
            .andExpect(jsonPath("$.flDesco").value(DEFAULT_FL_DESCO.booleanValue()))
            .andExpect(jsonPath("$.dtPromo").value(DEFAULT_DT_PROMO.toString()))
            .andExpect(jsonPath("$.dtOperacao").value(DEFAULT_DT_OPERACAO.toString()))
            .andExpect(jsonPath("$.flSemContagem").value(DEFAULT_FL_SEM_CONTAGEM.booleanValue()))
            .andExpect(jsonPath("$.flEnvio").value(DEFAULT_FL_ENVIO.booleanValue()))
            .andExpect(jsonPath("$.nnNovo").value(DEFAULT_NN_NOVO))
            .andExpect(jsonPath("$.nnDia").value(DEFAULT_NN_DIA))
            .andExpect(jsonPath("$.nnDiaSemana").value(DEFAULT_NN_DIA_SEMANA.toString()))
            .andExpect(jsonPath("$.nnTipo").value(DEFAULT_NN_TIPO));
    }

    @Test
    @Transactional
    public void getNonExistingGrupo() throws Exception {
        // Get the grupo
        restGrupoMockMvc.perform(get("/api/grupos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupo() throws Exception {
        // Initialize the database
        grupoService.save(grupo);

        int databaseSizeBeforeUpdate = grupoRepository.findAll().size();

        // Update the grupo
        Grupo updatedGrupo = new Grupo();
        updatedGrupo.setId(grupo.getId());
        updatedGrupo.setNmGrupo(UPDATED_NM_GRUPO);
        updatedGrupo.setVlComissao(UPDATED_VL_COMISSAO);
        updatedGrupo.setVlDesconto(UPDATED_VL_DESCONTO);
        updatedGrupo.setFlPromo(UPDATED_FL_PROMO);
        updatedGrupo.setFlDesco(UPDATED_FL_DESCO);
        updatedGrupo.setDtPromo(UPDATED_DT_PROMO);
        updatedGrupo.setDtOperacao(UPDATED_DT_OPERACAO);
        updatedGrupo.setFlSemContagem(UPDATED_FL_SEM_CONTAGEM);
        updatedGrupo.setFlEnvio(UPDATED_FL_ENVIO);
        updatedGrupo.setNnNovo(UPDATED_NN_NOVO);
        updatedGrupo.setNnDia(UPDATED_NN_DIA);
        updatedGrupo.setNnDiaSemana(UPDATED_NN_DIA_SEMANA);
        updatedGrupo.setNnTipo(UPDATED_NN_TIPO);

        restGrupoMockMvc.perform(put("/api/grupos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedGrupo)))
                .andExpect(status().isOk());

        // Validate the Grupo in the database
        List<Grupo> grupos = grupoRepository.findAll();
        assertThat(grupos).hasSize(databaseSizeBeforeUpdate);
        Grupo testGrupo = grupos.get(grupos.size() - 1);
        assertThat(testGrupo.getNmGrupo()).isEqualTo(UPDATED_NM_GRUPO);
        assertThat(testGrupo.getVlComissao()).isEqualTo(UPDATED_VL_COMISSAO);
        assertThat(testGrupo.getVlDesconto()).isEqualTo(UPDATED_VL_DESCONTO);
        assertThat(testGrupo.isFlPromo()).isEqualTo(UPDATED_FL_PROMO);
        assertThat(testGrupo.isFlDesco()).isEqualTo(UPDATED_FL_DESCO);
        assertThat(testGrupo.getDtPromo()).isEqualTo(UPDATED_DT_PROMO);
        assertThat(testGrupo.getDtOperacao()).isEqualTo(UPDATED_DT_OPERACAO);
        assertThat(testGrupo.isFlSemContagem()).isEqualTo(UPDATED_FL_SEM_CONTAGEM);
        assertThat(testGrupo.isFlEnvio()).isEqualTo(UPDATED_FL_ENVIO);
        assertThat(testGrupo.getNnNovo()).isEqualTo(UPDATED_NN_NOVO);
        assertThat(testGrupo.getNnDia()).isEqualTo(UPDATED_NN_DIA);
        assertThat(testGrupo.getNnDiaSemana()).isEqualTo(UPDATED_NN_DIA_SEMANA);
        assertThat(testGrupo.getNnTipo()).isEqualTo(UPDATED_NN_TIPO);

        // Validate the Grupo in ElasticSearch
        Grupo grupoEs = grupoSearchRepository.findOne(testGrupo.getId());
        assertThat(grupoEs).isEqualToComparingFieldByField(testGrupo);
    }

    @Test
    @Transactional
    public void deleteGrupo() throws Exception {
        // Initialize the database
        grupoService.save(grupo);

        int databaseSizeBeforeDelete = grupoRepository.findAll().size();

        // Get the grupo
        restGrupoMockMvc.perform(delete("/api/grupos/{id}", grupo.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean grupoExistsInEs = grupoSearchRepository.exists(grupo.getId());
        assertThat(grupoExistsInEs).isFalse();

        // Validate the database is empty
        List<Grupo> grupos = grupoRepository.findAll();
        assertThat(grupos).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchGrupo() throws Exception {
        // Initialize the database
        grupoService.save(grupo);

        // Search the grupo
        restGrupoMockMvc.perform(get("/api/_search/grupos?query=id:" + grupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nmGrupo").value(hasItem(DEFAULT_NM_GRUPO.toString())))
            .andExpect(jsonPath("$.[*].vlComissao").value(hasItem(DEFAULT_VL_COMISSAO.intValue())))
            .andExpect(jsonPath("$.[*].vlDesconto").value(hasItem(DEFAULT_VL_DESCONTO.intValue())))
            .andExpect(jsonPath("$.[*].flPromo").value(hasItem(DEFAULT_FL_PROMO.booleanValue())))
            .andExpect(jsonPath("$.[*].flDesco").value(hasItem(DEFAULT_FL_DESCO.booleanValue())))
            .andExpect(jsonPath("$.[*].dtPromo").value(hasItem(DEFAULT_DT_PROMO.toString())))
            .andExpect(jsonPath("$.[*].dtOperacao").value(hasItem(DEFAULT_DT_OPERACAO.toString())))
            .andExpect(jsonPath("$.[*].flSemContagem").value(hasItem(DEFAULT_FL_SEM_CONTAGEM.booleanValue())))
            .andExpect(jsonPath("$.[*].flEnvio").value(hasItem(DEFAULT_FL_ENVIO.booleanValue())))
            .andExpect(jsonPath("$.[*].nnNovo").value(hasItem(DEFAULT_NN_NOVO)))
            .andExpect(jsonPath("$.[*].nnDia").value(hasItem(DEFAULT_NN_DIA)))
            .andExpect(jsonPath("$.[*].nnDiaSemana").value(hasItem(DEFAULT_NN_DIA_SEMANA.toString())))
            .andExpect(jsonPath("$.[*].nnTipo").value(hasItem(DEFAULT_NN_TIPO)));
    }
}
