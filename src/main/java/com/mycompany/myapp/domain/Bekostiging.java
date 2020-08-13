package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Bekostiging.
 */
@Entity
@Table(name = "bekostiging")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bekostiging implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "burgerservicenummer")
    private String burgerservicenummer;

    @Column(name = "bruto_maandsalaris")
    private Integer brutoMaandsalaris;

    @ManyToOne
    @JsonIgnoreProperties(value = "bekostigings", allowSetters = true)
    private Verblijf verblijf;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBurgerservicenummer() {
        return burgerservicenummer;
    }

    public Bekostiging burgerservicenummer(String burgerservicenummer) {
        this.burgerservicenummer = burgerservicenummer;
        return this;
    }

    public void setBurgerservicenummer(String burgerservicenummer) {
        this.burgerservicenummer = burgerservicenummer;
    }

    public Integer getBrutoMaandsalaris() {
        return brutoMaandsalaris;
    }

    public Bekostiging brutoMaandsalaris(Integer brutoMaandsalaris) {
        this.brutoMaandsalaris = brutoMaandsalaris;
        return this;
    }

    public void setBrutoMaandsalaris(Integer brutoMaandsalaris) {
        this.brutoMaandsalaris = brutoMaandsalaris;
    }

    public Verblijf getVerblijf() {
        return verblijf;
    }

    public Bekostiging verblijf(Verblijf verblijf) {
        this.verblijf = verblijf;
        return this;
    }

    public void setVerblijf(Verblijf verblijf) {
        this.verblijf = verblijf;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bekostiging)) {
            return false;
        }
        return id != null && id.equals(((Bekostiging) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bekostiging{" +
            "id=" + getId() +
            ", burgerservicenummer='" + getBurgerservicenummer() + "'" +
            ", brutoMaandsalaris=" + getBrutoMaandsalaris() +
            "}";
    }
}
