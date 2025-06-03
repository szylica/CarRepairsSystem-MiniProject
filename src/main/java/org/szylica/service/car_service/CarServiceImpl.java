package service.car_service;

import model.car.Car;
import repositories.car_repository.CarRepository;

import java.util.Optional;

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void registerCar(Car car) {
        carRepository.addCar(car);
    }

    @Override
    public Optional<Car> getCarById(int id) {
        return carRepository.getCar(id);
    }
}
