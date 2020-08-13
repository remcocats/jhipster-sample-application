package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Kenniswerk.
 */
@Entity
@Table(name = "kenniswerk")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kenniswerk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "functie")
    private String functie;

    @Column(name = "bruto_maandsalaris")
    private Integer brutoMaandsalaris;

    @Column(name = "ufo_code")
    private String ufoCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctie() {
        return functie;
    }

    public Kenniswerk functie(String functie) {
        this.functie = functie;
        return this;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public Integer getBrutoMaandsalaris() {
        return brutoMaandsalaris;
    }

    public Kenniswerk brutoMaandsalaris(Integer brutoMaandsalaris) {
        this.brutoMaandsalaris = brutoMaandsalaris;
        return this;
    }

    public void setBrutoMaandsalaris(Integer brutoMaandsalaris) {
        this.brutoMaandsalaris = brutoMaandsalaris;
    }

    public String getUfoCode() {
        return ufoCode;
    }

    public Kenniswerk ufoCode(String ufoCode) {
        this.ufoCode = ufoCode;
        return this;
    }

    public void setUfoCode(String ufoCode) {
        this.ufoCode = ufoCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kenniswerk)) {
            return false;
        }
        return id != null && id.equals(((Kenniswerk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Kenniswerk{" +
            "id=" + getId() +
            ", functie='" + getFunctie() + "'" +
            ", brutoMaandsalaris=" + getBrutoMaandsalaris() +
            ", ufoCode='" + getUfoCode() + "'" +
            "}";
    }
}
