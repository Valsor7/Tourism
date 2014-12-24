package models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by yaroslav on 18.11.2014.
 */
@Entity
@Table(name = "orders", schema = "", catalog = "test")
public class OrdersEntity {
    private int id;
    private int tourId;
    private int clientId;
    private int workerId;
    private Date date;
    private ClientsEntity clientsByClientId;
    private ToursEntity toursByTourId;
    private WorkersEntity workersByWorkerId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tour_id", nullable = false, insertable = false, updatable = false)
    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    @Basic
    @Column(name = "client_id", nullable = false, insertable = false, updatable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "worker_id", nullable = false, insertable = false, updatable = false)
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (clientId != that.clientId) return false;
        if (id != that.id) return false;
        if (tourId != that.tourId) return false;
        if (workerId != that.workerId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + tourId;
        result = 31 * result + clientId;
        result = 31 * result + workerId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClientId() {
        return clientsByClientId;
    }

    public void setClientsByClientId(ClientsEntity clientsByClientId) {
        this.clientsByClientId = clientsByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id", nullable = false)
    public ToursEntity getToursByTourId() {
        return toursByTourId;
    }

    public void setToursByTourId(ToursEntity toursByTourId) {
        this.toursByTourId = toursByTourId;
    }

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    public WorkersEntity getWorkersByWorkerId() {
        return workersByWorkerId;
    }

    public void setWorkersByWorkerId(WorkersEntity workersByWorkerId) {
        this.workersByWorkerId = workersByWorkerId;
    }
}
