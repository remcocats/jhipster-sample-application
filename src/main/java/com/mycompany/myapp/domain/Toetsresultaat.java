package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Toetsresultaat.
 */
@Entity
@Table(name = "toetsresultaat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Toetsresultaat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "omschrijving")
    private String omschrijving;

    @Column(name = "waarde")
    private Boolean waarde;

    @ManyToOne
    @JsonIgnoreProperties(value = "toetsresultaats", allowSetters = true)
    private ErkendReferent erkendReferent;

    @ManyToOne
    @JsonIgnoreProperties(value = "toetsresultaats", allowSetters = true)
    private Vreemdeling vreemdeling;

    @ManyToOne
    @JsonIgnoreProperties(value = "toetsresultaats", allowSetters = true)
    private ErkendReferent erkendReferent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Toetsresultaat omschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
        return this;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Boolean isWaarde() {
        return waarde;
    }

    public Toetsresultaat waarde(Boolean waarde) {
        this.waarde = waarde;
        return this;
    }

    public void setWaarde(Boolean waarde) {
        this.waarde = waarde;
    }

    public ErkendReferent getErkendReferent() {
        return erkendReferent;
    }

    public Toetsresultaat erkendReferent(ErkendReferent erkendReferent) {
        this.erkendReferent = erkendReferent;
        return this;
    }

    public void setErkendReferent(ErkendReferent erkendReferent) {
        this.erkendReferent = erkendReferent;
    }

    public Vreemdeling getVreemdeling() {
        return vreemdeling;
    }

    public Toetsresultaat vreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
        return this;
    }

    public void setVreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
    }

    public ErkendReferent getErkendReferent() {
        return erkendReferent;
    }

    public Toetsresultaat erkendReferent(ErkendReferent erkendReferent) {
        this.erkendReferent = erkendReferent;
        return this;
    }

    public void setErkendReferent(ErkendReferent erkendReferent) {
        this.erkendReferent = erkendReferent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Toetsresultaat)) {
            return false;
        }
        return id != null && id.equals(((Toetsresultaat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Toetsresultaat{" +
            "id=" + getId() +
            ", omschrijving='" + getOmschrijving() + "'" +
            ", waarde='" + isWaarde() + "'" +
            "}";
    }
}
