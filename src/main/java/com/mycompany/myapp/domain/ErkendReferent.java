package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ErkendReferent.
 */
@Entity
@Table(name = "erkend_referent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ErkendReferent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referent_id")
    private String referentId;

    @Column(name = "naam")
    private String naam;

    @Column(name = "rsin")
    private String rsin;

    @Column(name = "kvk")
    private String kvk;

    @Column(name = "klant_nummer")
    private String klantNummer;

    @Column(name = "datum_uitschrijving_kvk")
    private String datumUitschrijvingKvk;

    @OneToMany(mappedBy = "erkendReferent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Erkenning> erkennings = new HashSet<>();

    @OneToMany(mappedBy = "erkendReferent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Toetsresultaat> toetsresultaats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "erkendReferents", allowSetters = true)
    private Toetsresultaat toetsresultaat;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferentId() {
        return referentId;
    }

    public ErkendReferent referentId(String referentId) {
        this.referentId = referentId;
        return this;
    }

    public void setReferentId(String referentId) {
        this.referentId = referentId;
    }

    public String getNaam() {
        return naam;
    }

    public ErkendReferent naam(String naam) {
        this.naam = naam;
        return this;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getRsin() {
        return rsin;
    }

    public ErkendReferent rsin(String rsin) {
        this.rsin = rsin;
        return this;
    }

    public void setRsin(String rsin) {
        this.rsin = rsin;
    }

    public String getKvk() {
        return kvk;
    }

    public ErkendReferent kvk(String kvk) {
        this.kvk = kvk;
        return this;
    }

    public void setKvk(String kvk) {
        this.kvk = kvk;
    }

    public String getKlantNummer() {
        return klantNummer;
    }

    public ErkendReferent klantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
        return this;
    }

    public void setKlantNummer(String klantNummer) {
        this.klantNummer = klantNummer;
    }

    public String getDatumUitschrijvingKvk() {
        return datumUitschrijvingKvk;
    }

    public ErkendReferent datumUitschrijvingKvk(String datumUitschrijvingKvk) {
        this.datumUitschrijvingKvk = datumUitschrijvingKvk;
        return this;
    }

    public void setDatumUitschrijvingKvk(String datumUitschrijvingKvk) {
        this.datumUitschrijvingKvk = datumUitschrijvingKvk;
    }

    public Set<Erkenning> getErkennings() {
        return erkennings;
    }

    public ErkendReferent erkennings(Set<Erkenning> erkennings) {
        this.erkennings = erkennings;
        return this;
    }

    public ErkendReferent addErkenning(Erkenning erkenning) {
        this.erkennings.add(erkenning);
        erkenning.setErkendReferent(this);
        return this;
    }

    public ErkendReferent removeErkenning(Erkenning erkenning) {
        this.erkennings.remove(erkenning);
        erkenning.setErkendReferent(null);
        return this;
    }

    public void setErkennings(Set<Erkenning> erkennings) {
        this.erkennings = erkennings;
    }

    public Set<Toetsresultaat> getToetsresultaats() {
        return toetsresultaats;
    }

    public ErkendReferent toetsresultaats(Set<Toetsresultaat> toetsresultaats) {
        this.toetsresultaats = toetsresultaats;
        return this;
    }

    public ErkendReferent addToetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaats.add(toetsresultaat);
        toetsresultaat.setErkendReferent(this);
        return this;
    }

    public ErkendReferent removeToetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaats.remove(toetsresultaat);
        toetsresultaat.setErkendReferent(null);
        return this;
    }

    public void setToetsresultaats(Set<Toetsresultaat> toetsresultaats) {
        this.toetsresultaats = toetsresultaats;
    }

    public Toetsresultaat getToetsresultaat() {
        return toetsresultaat;
    }

    public ErkendReferent toetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaat = toetsresultaat;
        return this;
    }

    public void setToetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaat = toetsresultaat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErkendReferent)) {
            return false;
        }
        return id != null && id.equals(((ErkendReferent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ErkendReferent{" +
            "id=" + getId() +
            ", referentId='" + getReferentId() + "'" +
            ", naam='" + getNaam() + "'" +
            ", rsin='" + getRsin() + "'" +
            ", kvk='" + getKvk() + "'" +
            ", klantNummer='" + getKlantNummer() + "'" +
            ", datumUitschrijvingKvk='" + getDatumUitschrijvingKvk() + "'" +
            "}";
    }
}
