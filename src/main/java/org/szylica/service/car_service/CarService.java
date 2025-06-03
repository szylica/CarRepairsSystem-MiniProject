package service.car_service;

import model.car.Car;

import java.util.Optional;

public interface CarService {

    void registerCar(Car car);
    Optional<Car> getCarById(int id);
}
