package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Referentstelling.
 */
@Entity
@Table(name = "referentstelling")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Referentstelling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private ErkendReferent erkendReferent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ErkendReferent getErkendReferent() {
        return erkendReferent;
    }

    public Referentstelling erkendReferent(ErkendReferent erkendReferent) {
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
        if (!(o instanceof Referentstelling)) {
            return false;
        }
        return id != null && id.equals(((Referentstelling) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Referentstelling{" +
            "id=" + getId() +
            "}";
    }
}
