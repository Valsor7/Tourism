package models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "clients", schema = "", catalog = "test")
public class ClientsEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String adress;
    private Collection<OrdersEntity> ordersesById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = false, insertable = true, updatable = true)
    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "adress", nullable = false, insertable = true, updatable = true, length = 30)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientsEntity that = (ClientsEntity) o;

        if (id != that.id) return false;
        if (phone != that.phone) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (phone ^ (phone >>> 32));
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientsByClientId")
    public Collection<OrdersEntity> getOrdersesById() {
        return ordersesById;
    }

    public void setOrdersesById(Collection<OrdersEntity> ordersesById) {
        this.ordersesById = ordersesById;
    }
}
