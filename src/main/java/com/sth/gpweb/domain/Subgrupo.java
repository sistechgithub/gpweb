package com.sth.gpweb.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Subgrupo.
 */
@Entity
@Table(name = "subgrupo")
@Document(indexName = "subgrupo")
public class Subgrupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 35)
    @Column(name = "nm_subgrupo", length = 35, nullable = false, unique = true)
    private String nmSubgrupo;

    @Column(name = "vl_valor", precision=18, scale=6)
    private BigDecimal vlValor;

    @Column(name = "vl_custo", precision=18, scale=6)
    private BigDecimal vlCusto;

    @Column(name = "dt_operacao")
    private LocalDate dtOperacao;

    @Column(name = "fl_envio")
    private Boolean flEnvio;

    @Column(name = "nn_novo")
    private Integer nnNovo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmSubgrupo() {
        return nmSubgrupo;
    }

    public void setNmSubgrupo(String nmSubgrupo) {
        this.nmSubgrupo = nmSubgrupo;
    }

    public BigDecimal getVlValor() {
        return vlValor;
    }

    public void setVlValor(BigDecimal vlValor) {
        this.vlValor = vlValor;
    }

    public BigDecimal getVlCusto() {
        return vlCusto;
    }

    public void setVlCusto(BigDecimal vlCusto) {
        this.vlCusto = vlCusto;
    }

    public LocalDate getDtOperacao() {
        return dtOperacao;
    }

    public void setDtOperacao(LocalDate dtOperacao) {
        this.dtOperacao = dtOperacao;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subgrupo subgrupo = (Subgrupo) o;
        if(subgrupo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, subgrupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Subgrupo{" +
            "id=" + id +
            ", nmSubgrupo='" + nmSubgrupo + "'" +
            ", vlValor='" + vlValor + "'" +
            ", vlCusto='" + vlCusto + "'" +
            ", dtOperacao='" + dtOperacao + "'" +
            ", flEnvio='" + flEnvio + "'" +
            ", nnNovo='" + nnNovo + "'" +
            '}';
    }
}
