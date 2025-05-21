package org.example.transaccion;

public class TransaccionActivitiesImpl implements TransaccionActivities {
    @Override
    public String cargo(String accountId, double amount){
        return "Cargo por " + amount + " en la cuenta" + accountId;
        //Agregar timer para simular el proceso
    }

    @Override
    public String abono(String accountId, double amount) {
        return "Abono  " + amount + " a la cuenta" + accountId;
    }
}
