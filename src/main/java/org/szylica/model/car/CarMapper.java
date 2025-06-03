package model.car;

import model.EngineType;

import java.util.function.Function;

public interface CarMapper {
    Function<Car, Integer> carToId = car -> car.id;

    Function<Car, EngineType> carToEngineType = car -> car.engineType;
}
