package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "countries", schema = "", catalog = "test")
public class CountriesEntity {
    private int id;
    private String country;
    private Collection<CitiesEntity> citiesesById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country", nullable = false, insertable = true, updatable = true, length = 30)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesEntity that = (CountriesEntity) o;

        if (id != that.id) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "countriesByCountryId")
    public Collection<CitiesEntity> getCitiesesById() {
        return citiesesById;
    }

    public void setCitiesesById(Collection<CitiesEntity> citiesesById) {
        this.citiesesById = citiesesById;
    }
}
