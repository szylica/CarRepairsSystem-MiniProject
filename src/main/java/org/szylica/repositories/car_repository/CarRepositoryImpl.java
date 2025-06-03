package repositories.car_repository;

import model.car.Car;
import model.car.CarMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {

    private final Map<Integer, Car> cars;

    public CarRepositoryImpl(Map<Integer, Car> cars) {
        this.cars = cars;
    }

    @Override
    public void addCar(Car car) {
        cars.put(CarMapper.carToId.apply(car), car);
    }

    @Override
    public Optional<Car> getCar(int id) {
        return Optional.ofNullable(cars.get(id));
    }

    @Override
    public List<Car> getCars() {
        return new ArrayList<>(cars.values());
    }
}
