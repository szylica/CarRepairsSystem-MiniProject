package model.repair_request;

import model.IssueType;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.function.Function;

public interface RepairRequestMapper {

    Function<RepairRequest, Integer> repairRequestToCarId = repairRequest -> repairRequest.carId;

    Function<RepairRequest, BigDecimal> repairRequestToPrice = repairRequest -> repairRequest.estimatedCost;

    Function<RepairRequest, IssueType> repairRequestToIssue = repairRequest -> repairRequest.issue;

    Function<RepairRequest, DayOfWeek> repairRequestToDayOfWeek = repairRequest -> repairRequest.requestDate.getDayOfWeek();

}
