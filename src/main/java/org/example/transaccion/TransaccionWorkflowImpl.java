package org.example.transaccion;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Objects;

public class TransaccionWorkflowImpl implements TransaccionWorkflow {
    private final ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();
private final TransaccionActivities activities =
        Workflow.newActivityStub(TransaccionActivities.class, options);

    @Override
    public String transaccionEnProceso(String tipoTransaccion, String accountId, double amount) {
        String transaccionActivitiesCargo= activities.cargo(accountId, amount);
        String transaccionActivitiesAbono= activities.abono(accountId, amount);


        if (Objects.equals(tipoTransaccion, "cargo")){
            return "Transacción completa: " + transaccionActivitiesCargo;
        } else if (Objects.equals(tipoTransaccion, "abono")) {
            return "Transacción completa: " + transaccionActivitiesAbono;

        }
        else{
            return "Transacción no valida";
        }

    }
}
