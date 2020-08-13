package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Verblijf.
 */
@Entity
@Table(name = "verblijf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Verblijf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verblijf_id")
    private String verblijfId;

    @Column(name = "aanvangsdatum")
    private String aanvangsdatum;

    @Column(name = "einddatum")
    private String einddatum;

    @OneToOne
    @JoinColumn(unique = true)
    private Verblijfsdoel verblijfsdoel;

    @OneToOne
    @JoinColumn(unique = true)
    private Vreemdeling vreemdeling;

    @OneToOne
    @JoinColumn(unique = true)
    private Referentstelling referentstelling;

    @OneToOne
    @JoinColumn(unique = true)
    private Kenniswerk kenniswerk;

    @OneToMany(mappedBy = "verblijf")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Bekostiging> bekostigings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerblijfId() {
        return verblijfId;
    }

    public Verblijf verblijfId(String verblijfId) {
        this.verblijfId = verblijfId;
        return this;
    }

    public void setVerblijfId(String verblijfId) {
        this.verblijfId = verblijfId;
    }

    public String getAanvangsdatum() {
        return aanvangsdatum;
    }

    public Verblijf aanvangsdatum(String aanvangsdatum) {
        this.aanvangsdatum = aanvangsdatum;
        return this;
    }

    public void setAanvangsdatum(String aanvangsdatum) {
        this.aanvangsdatum = aanvangsdatum;
    }

    public String getEinddatum() {
        return einddatum;
    }

    public Verblijf einddatum(String einddatum) {
        this.einddatum = einddatum;
        return this;
    }

    public void setEinddatum(String einddatum) {
        this.einddatum = einddatum;
    }

    public Verblijfsdoel getVerblijfsdoel() {
        return verblijfsdoel;
    }

    public Verblijf verblijfsdoel(Verblijfsdoel verblijfsdoel) {
        this.verblijfsdoel = verblijfsdoel;
        return this;
    }

    public void setVerblijfsdoel(Verblijfsdoel verblijfsdoel) {
        this.verblijfsdoel = verblijfsdoel;
    }

    public Vreemdeling getVreemdeling() {
        return vreemdeling;
    }

    public Verblijf vreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
        return this;
    }

    public void setVreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
    }

    public Referentstelling getReferentstelling() {
        return referentstelling;
    }

    public Verblijf referentstelling(Referentstelling referentstelling) {
        this.referentstelling = referentstelling;
        return this;
    }

    public void setReferentstelling(Referentstelling referentstelling) {
        this.referentstelling = referentstelling;
    }

    public Kenniswerk getKenniswerk() {
        return kenniswerk;
    }

    public Verblijf kenniswerk(Kenniswerk kenniswerk) {
        this.kenniswerk = kenniswerk;
        return this;
    }

    public void setKenniswerk(Kenniswerk kenniswerk) {
        this.kenniswerk = kenniswerk;
    }

    public Set<Bekostiging> getBekostigings() {
        return bekostigings;
    }

    public Verblijf bekostigings(Set<Bekostiging> bekostigings) {
        this.bekostigings = bekostigings;
        return this;
    }

    public Verblijf addBekostiging(Bekostiging bekostiging) {
        this.bekostigings.add(bekostiging);
        bekostiging.setVerblijf(this);
        return this;
    }

    public Verblijf removeBekostiging(Bekostiging bekostiging) {
        this.bekostigings.remove(bekostiging);
        bekostiging.setVerblijf(null);
        return this;
    }

    public void setBekostigings(Set<Bekostiging> bekostigings) {
        this.bekostigings = bekostigings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Verblijf)) {
            return false;
        }
        return id != null && id.equals(((Verblijf) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Verblijf{" +
            "id=" + getId() +
            ", verblijfId='" + getVerblijfId() + "'" +
            ", aanvangsdatum='" + getAanvangsdatum() + "'" +
            ", einddatum='" + getEinddatum() + "'" +
            "}";
    }
}
