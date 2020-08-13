package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Vreemdeling.
 */
@Entity
@Table(name = "vreemdeling")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vreemdeling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vreemdeling_id")
    private String vreemdelingId;

    @Column(name = "vnummer")
    private String vnummer;

    @Column(name = "burgerservicenummer")
    private String burgerservicenummer;

    @OneToOne
    @JoinColumn(unique = true)
    private PersoonsnaamVolgensDocumentVoorGrensoverschrijding naam;

    @OneToMany(mappedBy = "vreemdeling")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Toetsresultaat> toetsresultaats = new HashSet<>();

    @OneToMany(mappedBy = "vreemdeling")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Nationaliteit> nationaliteits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVreemdelingId() {
        return vreemdelingId;
    }

    public Vreemdeling vreemdelingId(String vreemdelingId) {
        this.vreemdelingId = vreemdelingId;
        return this;
    }

    public void setVreemdelingId(String vreemdelingId) {
        this.vreemdelingId = vreemdelingId;
    }

    public String getVnummer() {
        return vnummer;
    }

    public Vreemdeling vnummer(String vnummer) {
        this.vnummer = vnummer;
        return this;
    }

    public void setVnummer(String vnummer) {
        this.vnummer = vnummer;
    }

    public String getBurgerservicenummer() {
        return burgerservicenummer;
    }

    public Vreemdeling burgerservicenummer(String burgerservicenummer) {
        this.burgerservicenummer = burgerservicenummer;
        return this;
    }

    public void setBurgerservicenummer(String burgerservicenummer) {
        this.burgerservicenummer = burgerservicenummer;
    }

    public PersoonsnaamVolgensDocumentVoorGrensoverschrijding getNaam() {
        return naam;
    }

    public Vreemdeling naam(PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding) {
        this.naam = persoonsnaamVolgensDocumentVoorGrensoverschrijding;
        return this;
    }

    public void setNaam(PersoonsnaamVolgensDocumentVoorGrensoverschrijding persoonsnaamVolgensDocumentVoorGrensoverschrijding) {
        this.naam = persoonsnaamVolgensDocumentVoorGrensoverschrijding;
    }

    public Set<Toetsresultaat> getToetsresultaats() {
        return toetsresultaats;
    }

    public Vreemdeling toetsresultaats(Set<Toetsresultaat> toetsresultaats) {
        this.toetsresultaats = toetsresultaats;
        return this;
    }

    public Vreemdeling addToetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaats.add(toetsresultaat);
        toetsresultaat.setVreemdeling(this);
        return this;
    }

    public Vreemdeling removeToetsresultaat(Toetsresultaat toetsresultaat) {
        this.toetsresultaats.remove(toetsresultaat);
        toetsresultaat.setVreemdeling(null);
        return this;
    }

    public void setToetsresultaats(Set<Toetsresultaat> toetsresultaats) {
        this.toetsresultaats = toetsresultaats;
    }

    public Set<Nationaliteit> getNationaliteits() {
        return nationaliteits;
    }

    public Vreemdeling nationaliteits(Set<Nationaliteit> nationaliteits) {
        this.nationaliteits = nationaliteits;
        return this;
    }

    public Vreemdeling addNationaliteit(Nationaliteit nationaliteit) {
        this.nationaliteits.add(nationaliteit);
        nationaliteit.setVreemdeling(this);
        return this;
    }

    public Vreemdeling removeNationaliteit(Nationaliteit nationaliteit) {
        this.nationaliteits.remove(nationaliteit);
        nationaliteit.setVreemdeling(null);
        return this;
    }

    public void setNationaliteits(Set<Nationaliteit> nationaliteits) {
        this.nationaliteits = nationaliteits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vreemdeling)) {
            return false;
        }
        return id != null && id.equals(((Vreemdeling) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vreemdeling{" +
            "id=" + getId() +
            ", vreemdelingId='" + getVreemdelingId() + "'" +
            ", vnummer='" + getVnummer() + "'" +
            ", burgerservicenummer='" + getBurgerservicenummer() + "'" +
            "}";
    }
}
