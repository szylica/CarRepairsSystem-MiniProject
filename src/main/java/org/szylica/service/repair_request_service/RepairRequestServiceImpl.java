package service.repair_request_service;

import model.repair_request.RepairRequest;
import repositories.repair_request_repository.RepairRequestRepository;

import java.time.LocalDate;

public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;

    public RepairRequestServiceImpl(RepairRequestRepository repairRequestRepository) {
        this.repairRequestRepository = repairRequestRepository;
    }

    @Override
    public void registerRepairRequest(RepairRequest request) {
        repairRequestRepository.addRepair(request);
    }

    @Override
    public void cancelVisit(int carId, LocalDate visitDate) {
        repairRequestRepository.cancelRepair(carId, visitDate);
    }

}
