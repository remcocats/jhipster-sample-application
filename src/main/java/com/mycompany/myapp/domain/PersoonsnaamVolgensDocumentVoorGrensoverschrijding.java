package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PersoonsnaamVolgensDocumentVoorGrensoverschrijding.
 */
@Entity
@Table(name = "persoonsnaam_volgens_document_voor_grensoverschrijding")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersoonsnaamVolgensDocumentVoorGrensoverschrijding implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voornamen")
    private String voornamen;

    @Column(name = "achternaam")
    private String achternaam;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornamen() {
        return voornamen;
    }

    public PersoonsnaamVolgensDocumentVoorGrensoverschrijding voornamen(String voornamen) {
        this.voornamen = voornamen;
        return this;
    }

    public void setVoornamen(String voornamen) {
        this.voornamen = voornamen;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public PersoonsnaamVolgensDocumentVoorGrensoverschrijding achternaam(String achternaam) {
        this.achternaam = achternaam;
        return this;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersoonsnaamVolgensDocumentVoorGrensoverschrijding)) {
            return false;
        }
        return id != null && id.equals(((PersoonsnaamVolgensDocumentVoorGrensoverschrijding) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersoonsnaamVolgensDocumentVoorGrensoverschrijding{" +
            "id=" + getId() +
            ", voornamen='" + getVoornamen() + "'" +
            ", achternaam='" + getAchternaam() + "'" +
            "}";
    }
}
