package org.example.transaccion;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TransaccionActivities {
    @ActivityMethod
    String cargo(String accountId, double amount);

    @ActivityMethod
    String abono(String accountId, double amount);
}
