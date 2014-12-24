package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "transport", schema = "", catalog = "test")
public class TransportEntity {
    private int id;
    private String transportType;
    private Collection<ToursEntity> toursesById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "transport_type", nullable = false, insertable = true, updatable = true, length = 50)
    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportEntity that = (TransportEntity) o;

        if (id != that.id) return false;
        if (transportType != null ? !transportType.equals(that.transportType) : that.transportType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "transportByTransportId")
    public Collection<ToursEntity> getToursesById() {
        return toursesById;
    }

    public void setToursesById(Collection<ToursEntity> toursesById) {
        this.toursesById = toursesById;
    }
}
