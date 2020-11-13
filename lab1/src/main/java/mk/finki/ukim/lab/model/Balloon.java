package mk.finki.ukim.lab.model;

import lombok.Data;

@Data
public class Balloon {
    String name, description;
    Manufacturer manufacturer;
    Long id;

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.id = (long)(Math.random()*1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
