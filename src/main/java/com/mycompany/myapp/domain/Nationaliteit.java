package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Nationaliteit.
 */
@Entity
@Table(name = "nationaliteit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nationaliteit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JsonIgnoreProperties(value = "nationaliteits", allowSetters = true)
    private Vreemdeling vreemdeling;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Nationaliteit code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Vreemdeling getVreemdeling() {
        return vreemdeling;
    }

    public Nationaliteit vreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
        return this;
    }

    public void setVreemdeling(Vreemdeling vreemdeling) {
        this.vreemdeling = vreemdeling;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nationaliteit)) {
            return false;
        }
        return id != null && id.equals(((Nationaliteit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nationaliteit{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
