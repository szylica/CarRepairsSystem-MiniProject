package service.repair_request_service_analitics;

import collector.MostFrequentIssuesCollector;
import model.EngineType;
import model.IssueType;
import model.car.Car;
import model.car.CarMapper;
import model.repair_request.RepairRequest;
import model.repair_request.RepairRequestMapper;
import repositories.car_repository.CarRepository;
import repositories.repair_request_repository.RepairRequestRepository;
import service.repair_request_service.RepairRequestService;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static model.repair_request.RepairRequestMapper.repairRequestToIssue;

public class RepairRequestServiceAnalyticsImpl implements RepairRequestServiceAnalitics {

    private final RepairRequestRepository repairRequestRepository;
    private final CarRepository carRepository;

    public RepairRequestServiceAnalyticsImpl(RepairRequestRepository repairRequestRepository, CarRepository carRepository) {
        this.repairRequestRepository = repairRequestRepository;
        this.carRepository = carRepository;
    }

    @Override
    public BigDecimal getTotalCostForCar(int carId) {
        return repairRequestRepository.getRepairsByCarId(carId)
                .stream()
                .map(RepairRequestMapper.repairRequestToPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalCostForRegistrationPrefix(String prefix) {
        return getTotalCostForRepairIf(entry -> carRepository.getCar(entry.getKey()).orElseThrow().hasPrefix(prefix));
    }

    @Override
    public List<Car> getMostExpensiveCarsForIssue(IssueType issueType) {
        return repairRequestRepository.getAllRepairs()
                .entrySet()
                .stream()
                .filter(e -> hasIssue(e.getValue(), issueType))
                .collect(toMap(
                        entry -> carRepository.getCar(entry.getKey()).orElseThrow(),
                        entry -> sumRepairRequests(entry.getValue())
                ))
                .entrySet()
                .stream()
                .collect(groupingBy(
                        entry -> entry.getValue(),
                        mapping(Map.Entry::getKey, toList())
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    @Override
    public Set<IssueType> getMostFrequentIssue() {
        return repairRequestRepository.getAllRepairs()
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .map(repairRequestToIssue)
                .collect(new MostFrequentIssuesCollector());
    }

    @Override
    public Map<EngineType, Long> countRepairsByEngineType() {
        return getRepairsByCar()
                .entrySet()
                .stream()
                .collect(groupingBy(
                        entry -> CarMapper.carToEngineType.apply(entry.getKey()),
                        flatMapping(entry -> entry.getValue().stream(), counting())
                ));
    }

    @Override
    public int countRepairsByBrand(String brand) {
        return getRepairsByCar()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().hasBrand(brand))
                .flatMap(entry -> entry.getValue().stream())
                .toList()
                .size();
    }

    @Override
    public Map<DayOfWeek, Long> countRepairsGroupedByWeekDay() {
        return getRepairsByCar()
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(groupingBy(
                        RepairRequestMapper.repairRequestToDayOfWeek,
                        counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    private BigDecimal getTotalCostForRepairIf(Predicate<Map.Entry<Integer, List<RepairRequest>>> predicate) {
        return repairRequestRepository.getAllRepairs()
                .entrySet()
                .stream()
                .filter(predicate)
                .flatMap(entry -> entry.getValue().stream())
                .map(RepairRequestMapper.repairRequestToPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private Map<Car, List<RepairRequest>> getRepairsByCar() {
        return repairRequestRepository.getAllRepairs()
                .entrySet()
                .stream()
                .collect(toMap(
                        entry -> carRepository.getCar(entry.getKey()).orElseThrow(),
                        Map.Entry::getValue
                ));
    }

    private static boolean hasIssue(List<RepairRequest> repairRequests, IssueType issueType) {
        return repairRequests
                .stream()
                .anyMatch(request -> request.hasIssue(issueType));
    }

    private static BigDecimal sumRepairRequests(List<RepairRequest> repairRequests) {
        return repairRequests
                .stream()
                .map(RepairRequestMapper.repairRequestToPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
