package service.repair_request_service_analitics;

import model.EngineType;
import model.IssueType;
import model.car.Car;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepairRequestServiceAnalitics {

    BigDecimal getTotalCostForCar(int carId);

    BigDecimal getTotalCostForRegistrationPrefix(String prefix);

    List<Car> getMostExpensiveCarsForIssue(IssueType issueType);

    Set<IssueType> getMostFrequentIssue();

    Map<EngineType, Long> countRepairsByEngineType();

    int countRepairsByBrand(String brand);

    Map<DayOfWeek, Long> countRepairsGroupedByWeekDay();


}
