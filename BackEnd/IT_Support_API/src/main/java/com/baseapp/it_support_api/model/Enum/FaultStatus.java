package com.baseapp.it_support_api.model.Enum;

public enum FaultStatus {

    REPORTED,      // Initial state when a fault is reported
    IN_PROGRESS,   // Fault is currently being addressed
    REPAIRED,      // Fault has been fixed
    UNRESOLVABLE   // Fault cannot be fixed
}
