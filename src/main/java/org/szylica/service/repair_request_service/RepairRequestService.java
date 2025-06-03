package service.repair_request_service;

import model.IssueType;
import model.repair_request.RepairRequest;
import model.repair_request.RepairRequestMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RepairRequestService {

    void registerRepairRequest(RepairRequest request);
    void cancelVisit(int carId, LocalDate visitDate);

}
