package uni.project.rest.api.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "car")
public class Car {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, name = "carId")
    private long id;

    @Column(nullable = false, name = "make")
    private String make;

    @Column(nullable = false, name = "model")
    private String model;

    @Column(nullable = false, name = "production_year")
    private int year;

    @Column(nullable = false,name = "license_plate")
    private String licensePlate;


    public Car() {
    }

    public Car(String make, String model, int year, String licensePlate) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }


    // Define getters and setters




    public long getId() {
        return id;
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
