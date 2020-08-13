package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Erkenning.
 */
@Entity
@Table(name = "erkenning")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Erkenning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verblijfsdoel")
    private String verblijfsdoel;

    @Column(name = "aanvangsdatum")
    private String aanvangsdatum;

    @Column(name = "einddatum")
    private String einddatum;

    @ManyToOne
    @JsonIgnoreProperties(value = "erkennings", allowSetters = true)
    private ErkendReferent erkendReferent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerblijfsdoel() {
        return verblijfsdoel;
    }

    public Erkenning verblijfsdoel(String verblijfsdoel) {
        this.verblijfsdoel = verblijfsdoel;
        return this;
    }

    public void setVerblijfsdoel(String verblijfsdoel) {
        this.verblijfsdoel = verblijfsdoel;
    }

    public String getAanvangsdatum() {
        return aanvangsdatum;
    }

    public Erkenning aanvangsdatum(String aanvangsdatum) {
        this.aanvangsdatum = aanvangsdatum;
        return this;
    }

    public void setAanvangsdatum(String aanvangsdatum) {
        this.aanvangsdatum = aanvangsdatum;
    }

    public String getEinddatum() {
        return einddatum;
    }

    public Erkenning einddatum(String einddatum) {
        this.einddatum = einddatum;
        return this;
    }

    public void setEinddatum(String einddatum) {
        this.einddatum = einddatum;
    }

    public ErkendReferent getErkendReferent() {
        return erkendReferent;
    }

    public Erkenning erkendReferent(ErkendReferent erkendReferent) {
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
        if (!(o instanceof Erkenning)) {
            return false;
        }
        return id != null && id.equals(((Erkenning) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Erkenning{" +
            "id=" + getId() +
            ", verblijfsdoel='" + getVerblijfsdoel() + "'" +
            ", aanvangsdatum='" + getAanvangsdatum() + "'" +
            ", einddatum='" + getEinddatum() + "'" +
            "}";
    }
}
