package model.car;

import model.EngineType;

public class Car {
    final int id;
    private final String brand;
    private final String model;
    final EngineType engineType;
    private final String registrationNumber;

    public Car(int id, String brand, String model, EngineType engineType, String registrationNumber) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
        this.registrationNumber = registrationNumber;
    }

    public boolean hasPrefix(String prefix) {
        return this.registrationNumber.startsWith(prefix);
    }

    public boolean hasBrand(String brand) {
        return this.brand.equals(brand);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Car[%d, %s, %s, %s, %s]".formatted(id, brand, model, engineType.name(), registrationNumber);
    }

}
