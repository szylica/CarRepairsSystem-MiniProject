package repositories.repair_request_repository;

import model.repair_request.RepairRequest;
import model.repair_request.RepairRequestMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepairRequestRepositoryImpl implements RepairRequestRepository {

    private final Map<Integer, List<RepairRequest>> repairRequestData;

    public RepairRequestRepositoryImpl(Map<Integer, List<RepairRequest>> repairRequestData) {
        this.repairRequestData = repairRequestData;
    }

    @Override
    public void addRepair(RepairRequest repairRequest) {
        repairRequestData.merge(
                RepairRequestMapper.repairRequestToCarId.apply(repairRequest),
                new ArrayList<>(List.of(repairRequest)),
                (oldVal, newVal) -> {
                    oldVal.addAll(newVal);
                    return oldVal;
                }
                );
    }

    @Override
    public void cancelRepair(int carId, LocalDate visitDate) {
        var request = repairRequestData.get(carId)
                .stream()
                .filter(req -> req.hasRequestDate(visitDate))
                .toList();

        for(var req : request) {
            req.cancelRepairRequest();
        }


    }

    @Override
    public List<RepairRequest> getRepairsByCarId(int carId) {
        return repairRequestData.getOrDefault(carId, new ArrayList<>());
    }

    @Override
    public Map<Integer, List<RepairRequest>> getAllRepairs() {
        return repairRequestData;
    }


}
