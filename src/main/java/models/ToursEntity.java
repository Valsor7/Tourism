package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "tours", schema = "", catalog = "test")
public class ToursEntity {
    private int id;
    private int hotelId;
    private int transportId;
    private Date departing;
    private Date returning;
    private int length;
    private int price;
    private Collection<OrdersEntity> ordersesById;
    private HotelsEntity hotelsByHotelId;
    private TransportEntity transportByTransportId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hotel_id", nullable = false, insertable = false, updatable = false)
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "transport_id", nullable = false, insertable = false, updatable = false)
    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    @Basic
    @Column(name = "departing", nullable = false, insertable = true, updatable = true)
    public Date getDeparting() {
        return departing;
    }

    public void setDeparting(Date departing) {
        this.departing = departing;
    }

    @Basic
    @Column(name = "returning", nullable = false, insertable = true, updatable = true)
    public Date getReturning() {
        return returning;
    }

    public void setReturning(Date returning) {
        this.returning = returning;
    }

    @Basic
    @Column(name = "length", nullable = false, insertable = true, updatable = true)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "price", nullable = true, insertable = true, updatable = true, precision = 0)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToursEntity that = (ToursEntity) o;

        if (hotelId != that.hotelId) return false;
        if (id != that.id) return false;
        if (length != that.length) return false;
        if (transportId != that.transportId) return false;
        if (departing != null ? !departing.equals(that.departing) : that.departing != null) return false;
        if (price != that.price) return false;
        if (returning != null ? !returning.equals(that.returning) : that.returning != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + hotelId;
        result = 31 * result + transportId;
        result = 31 * result + (departing != null ? departing.hashCode() : 0);
        result = 31 * result + (returning != null ? returning.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + price;
        return result;
    }

    @OneToMany(mappedBy = "toursByTourId")
    public Collection<OrdersEntity> getOrdersesById() {
        return ordersesById;
    }

    public void setOrdersesById(Collection<OrdersEntity> ordersesById) {
        this.ordersesById = ordersesById;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    public HotelsEntity getHotelsByHotelId() {
        return hotelsByHotelId;
    }

    public void setHotelsByHotelId(HotelsEntity hotelsByHotelId) {
        this.hotelsByHotelId = hotelsByHotelId;
    }

    @ManyToOne
    @JoinColumn(name = "transport_id", referencedColumnName = "id", nullable = false)
    public TransportEntity getTransportByTransportId() {
        return transportByTransportId;
    }

    public void setTransportByTransportId(TransportEntity transportByTransportId) {
        this.transportByTransportId = transportByTransportId;
    }
}
