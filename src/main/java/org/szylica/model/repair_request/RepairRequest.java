package model.repair_request;

import model.IssueType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RepairRequest {
    private final int id;
    final int carId;
    final LocalDate requestDate;
    private final LocalDate visitDate;
    final IssueType issue;
    final BigDecimal estimatedCost;
    private boolean cancelled;

    public void cancelRepairRequest() {
        cancelled = true;
    }

    public boolean hasRequestDate(LocalDate visitDate) {
        return this.visitDate.equals(visitDate);
    }

    public boolean hasIssue(IssueType issue) {
        return this.issue.equals(issue);
    }



    public RepairRequest(int id, int carId, LocalDate requestDate, LocalDate visitDate, IssueType issue, BigDecimal estimatedCost) {
        this.id = id;
        this.carId = carId;
        this.requestDate = requestDate;
        this.visitDate = visitDate;
        this.issue = issue;
        this.estimatedCost = estimatedCost;
        this.cancelled = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RepairRequest that = (RepairRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Request[%d, %d, %s, %s, %s, %s, %s]".formatted(id, carId, requestDate, visitDate, issue, estimatedCost, cancelled);
    }

}
