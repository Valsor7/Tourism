package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "hotels", schema = "", catalog = "test")
public class HotelsEntity {
    private int id;
    private int cityId;
    private String hotel;
    private Integer stars;
    private String board;
    private int rooms;
    private byte service;
    private CitiesEntity citiesByCityId;
    private Collection<ToursEntity> toursesById;

    public HotelsEntity(int stars)
    {
        this.stars = stars;
    }

    public HotelsEntity() {
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id", nullable = false, insertable = false, updatable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "hotel", nullable = false, insertable = true, updatable = true, length = 50)
    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Basic
    @Column(name = "stars", nullable = true, insertable = true, updatable = true)
    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Basic
    @Column(name = "board", nullable = false, insertable = true, updatable = true, length = 5)
    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    @Basic
    @Column(name = "rooms", nullable = false, insertable = true, updatable = true)
    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Basic
    @Column(name = "service", nullable = false, insertable = true, updatable = true)
    public byte getService() {
        return service;
    }

    public void setService(byte service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelsEntity that = (HotelsEntity) o;

        if (cityId != that.cityId) return false;
        if (id != that.id) return false;
        if (rooms != that.rooms) return false;
        if (service != that.service) return false;
        if (board != null ? !board.equals(that.board) : that.board != null) return false;
        if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null) return false;
        if (stars != null ? !stars.equals(that.stars) : that.stars != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cityId;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        result = 31 * result + (board != null ? board.hashCode() : 0);
        result = 31 * result + rooms;
        result = 31 * result + (int) service;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public CitiesEntity getCitiesByCityId() {
        return citiesByCityId;
    }

    public void setCitiesByCityId(CitiesEntity citiesByCityId) {
        this.citiesByCityId = citiesByCityId;
    }

    @OneToMany(mappedBy = "hotelsByHotelId")
    public Collection<ToursEntity> getToursesById() {
        return toursesById;
    }

    public void setToursesById(Collection<ToursEntity> toursesById) {
        this.toursesById = toursesById;
    }
}
