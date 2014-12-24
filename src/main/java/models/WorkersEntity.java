package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by yaroslav on 17.11.2014.
 */
@Entity
@Table(name = "workers", schema = "", catalog = "test")
public class WorkersEntity {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private float salary;
    private String workPost;
    private String phone;
    private int experience;
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
    @Column(name = "birthday", nullable = false, insertable = true, updatable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "salary", nullable = false, insertable = true, updatable = true, precision = 0)
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "work_post", nullable = false, insertable = true, updatable = true, length = 50)
    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    @Basic
    @Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 15)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "experience", nullable = false, insertable = true, updatable = true)
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkersEntity that = (WorkersEntity) o;

        if (experience != that.experience) return false;
        if (id != that.id) return false;
        if (Float.compare(that.salary, salary) != 0) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (workPost != null ? !workPost.equals(that.workPost) : that.workPost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + (workPost != null ? workPost.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + experience;
        return result;
    }

    @OneToMany(mappedBy = "workersByWorkerId")
    public Collection<OrdersEntity> getOrdersesById() {
        return ordersesById;
    }

    public void setOrdersesById(Collection<OrdersEntity> ordersesById) {
        this.ordersesById = ordersesById;
    }
}
