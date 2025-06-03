package repositories.repair_request_repository;

import model.repair_request.RepairRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RepairRequestRepository {

    void addRepair(RepairRequest repairRequest);

    void cancelRepair(int carId, LocalDate visitDate);

    List<RepairRequest> getRepairsByCarId(int carId);

    Map<Integer, List<RepairRequest>> getAllRepairs();


}
