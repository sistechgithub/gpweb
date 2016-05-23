package com.sth.gpweb.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Sub_grupo.
 */
@Entity
@Table(name = "sub_grupo")
@Document(indexName = "sub_grupo")
public class Sub_grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nm_sub_grupo", nullable = false)
    private String nm_sub_grupo;

    @Column(name = "vl_custo", precision=10, scale=2)
    private BigDecimal vl_custo;

    @Column(name = "vl_valor", precision=10, scale=2)
    private BigDecimal vl_valor;

    @Column(name = "dt_operacao")
    private LocalDate dt_operacao;

    @Column(name = "fl_envio")
    private Boolean fl_envio;

    @Column(name = "nn_novo")
    private Integer nn_novo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNm_sub_grupo() {
        return nm_sub_grupo;
    }

    public void setNm_sub_grupo(String nm_sub_grupo) {
        this.nm_sub_grupo = nm_sub_grupo;
    }

    public BigDecimal getVl_custo() {
        return vl_custo;
    }

    public void setVl_custo(BigDecimal vl_custo) {
        this.vl_custo = vl_custo;
    }

    public BigDecimal getVl_valor() {
        return vl_valor;
    }

    public void setVl_valor(BigDecimal vl_valor) {
        this.vl_valor = vl_valor;
    }

    public LocalDate getDt_operacao() {
        return dt_operacao;
    }

    public void setDt_operacao(LocalDate dt_operacao) {
        this.dt_operacao = dt_operacao;
    }

    public Boolean isFl_envio() {
        return fl_envio;
    }

    public void setFl_envio(Boolean fl_envio) {
        this.fl_envio = fl_envio;
    }

    public Integer getNn_novo() {
        return nn_novo;
    }

    public void setNn_novo(Integer nn_novo) {
        this.nn_novo = nn_novo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sub_grupo sub_grupo = (Sub_grupo) o;
        if(sub_grupo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sub_grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sub_grupo{" +
            "id=" + id +
            ", nm_sub_grupo='" + nm_sub_grupo + "'" +
            ", vl_custo='" + vl_custo + "'" +
            ", vl_valor='" + vl_valor + "'" +
            ", dt_operacao='" + dt_operacao + "'" +
            ", fl_envio='" + fl_envio + "'" +
            ", nn_novo='" + nn_novo + "'" +
            '}';
    }
}
