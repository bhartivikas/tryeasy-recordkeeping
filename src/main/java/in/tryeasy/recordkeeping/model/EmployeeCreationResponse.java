package in.tryeasy.recordkeeping.model;

import java.util.UUID;

public class EmployeeCreationResponse {
    private UUID employeeId;

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
}
