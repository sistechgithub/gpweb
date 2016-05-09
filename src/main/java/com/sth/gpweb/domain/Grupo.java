package com.sth.gpweb.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Grupo.
 */
@Entity
@Table(name = "grupo")
@Document(indexName = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "nm_grupo", length = 35, nullable = false)
    private String nmGrupo;

    @Max(value = 100)
    @Column(name = "vl_comissao", precision=10, scale=2)
    private BigDecimal vlComissao;

    @Max(value = 100)
    @Column(name = "vl_desconto", precision=10, scale=2)
    private BigDecimal vlDesconto;

    @Column(name = "fl_promo")
    private Boolean flPromo;

    @Column(name = "fl_desco")
    private Boolean flDesco;

    @Column(name = "dt_promo")
    private LocalDate dtPromo;

    @Column(name = "dt_operacao")
    private LocalDate dtOperacao;

    @Column(name = "fl_sem_contagem")
    private Boolean flSemContagem;

    @Column(name = "fl_envio")
    private Boolean flEnvio;

    @Column(name = "nn_novo")
    private Integer nnNovo;

    @Column(name = "nn_day")
    private Integer nnDay;

    @Column(name = "nn_day_week")
    private String nnDayWeek;

    @Column(name = "nn_type")
    private Integer nnType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public BigDecimal getVlComissao() {
        return vlComissao;
    }

    public void setVlComissao(BigDecimal vlComissao) {
        this.vlComissao = vlComissao;
    }

    public BigDecimal getVlDesconto() {
        return vlDesconto;
    }

    public void setVlDesconto(BigDecimal vlDesconto) {
        this.vlDesconto = vlDesconto;
    }

    public Boolean isFlPromo() {
        return flPromo;
    }

    public void setFlPromo(Boolean flPromo) {
        this.flPromo = flPromo;
    }

    public Boolean isFlDesco() {
        return flDesco;
    }

    public void setFlDesco(Boolean flDesco) {
        this.flDesco = flDesco;
    }

    public LocalDate getDtPromo() {
        return dtPromo;
    }

    public void setDtPromo(LocalDate dtPromo) {
        this.dtPromo = dtPromo;
    }

    public LocalDate getDtOperacao() {
        return dtOperacao;
    }

    public void setDtOperacao(LocalDate dtOperacao) {
        this.dtOperacao = dtOperacao;
    }

    public Boolean isFlSemContagem() {
        return flSemContagem;
    }

    public void setFlSemContagem(Boolean flSemContagem) {
        this.flSemContagem = flSemContagem;
    }

    public Boolean isFlEnvio() {
        return flEnvio;
    }

    public void setFlEnvio(Boolean flEnvio) {
        this.flEnvio = flEnvio;
    }

    public Integer getNnNovo() {
        return nnNovo;
    }

    public void setNnNovo(Integer nnNovo) {
        this.nnNovo = nnNovo;
    }

    public Integer getNnDay() {
        return nnDay;
    }

    public void setNnDay(Integer nnDay) {
        this.nnDay = nnDay;
    }

    public String getNnDayWeek() {
        return nnDayWeek;
    }

    public void setNnDayWeek(String nnDayWeek) {
        this.nnDayWeek = nnDayWeek;
    }

    public Integer getNnType() {
        return nnType;
    }

    public void setNnType(Integer nnType) {
        this.nnType = nnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grupo grupo = (Grupo) o;
        if(grupo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grupo{" +
            "id=" + id +
            ", nmGrupo='" + nmGrupo + "'" +
            ", vlComissao='" + vlComissao + "'" +
            ", vlDesconto='" + vlDesconto + "'" +
            ", flPromo='" + flPromo + "'" +
            ", flDesco='" + flDesco + "'" +
            ", dtPromo='" + dtPromo + "'" +
            ", dtOperacao='" + dtOperacao + "'" +
            ", flSemContagem='" + flSemContagem + "'" +
            ", flEnvio='" + flEnvio + "'" +
            ", nnNovo='" + nnNovo + "'" +
            ", nnDay='" + nnDay + "'" +
            ", nnDayWeek='" + nnDayWeek + "'" +
            ", nnType='" + nnType + "'" +
            '}';
    }
}
