package repositories.car_repository;

import model.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    void addCar(Car car);
    Optional<Car> getCar(int id);
    List<Car> getCars();
}
