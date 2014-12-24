package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "cities", schema = "", catalog = "test")
public class CitiesEntity {
    private int id;
    private String city;
    private int countryId;
    private CountriesEntity countriesByCountryId;
    private Collection<HotelsEntity> hotelsesById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city", nullable = false, insertable = true, updatable = true, length = 30)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country_id", nullable = false, insertable = false, updatable = false)
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesEntity that = (CitiesEntity) o;

        if (countryId != that.countryId) return false;
        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + countryId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    public CountriesEntity getCountriesByCountryId() {
        return countriesByCountryId;
    }

    public void setCountriesByCountryId(CountriesEntity countriesByCountryId) {
        this.countriesByCountryId = countriesByCountryId;
    }

    @OneToMany(mappedBy = "citiesByCityId")
    public Collection<HotelsEntity> getHotelsesById() {
        return hotelsesById;
    }

    public void setHotelsesById(Collection<HotelsEntity> hotelsesById) {
        this.hotelsesById = hotelsesById;
    }
}
