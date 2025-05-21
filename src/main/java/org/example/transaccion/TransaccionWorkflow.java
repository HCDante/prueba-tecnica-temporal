package org.example.transaccion;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TransaccionWorkflow {
    @WorkflowMethod
    String transaccionEnProceso (String tipoTransaccion, String accountId, double amount);
}
