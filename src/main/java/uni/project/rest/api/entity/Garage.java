package uni.project.rest.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, name = "garageId")
    private long id;

    @Column(unique = false,updatable = true, name = "capacity")
    private int capacity;

    @Column(unique = false,updatable = true, length = 50, name = "city")
    private String city;

    @Column(unique = false,updatable = true, length = 50, name = "location")
    private String location;

    @Column(unique = false,updatable = true, length = 100, name = "name")
    private String name;

    public Garage() {
    }

    public Garage(int capacity, String city, String location, String name) {
        this.capacity = capacity;
        this.city = city;
        this.location = location;
        this.name = name;
    }

    public long getId() {
        return id;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
