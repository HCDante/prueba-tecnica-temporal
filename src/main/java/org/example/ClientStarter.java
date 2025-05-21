package org.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.example.transaccion.TransaccionWorkflow;

import java.util.UUID;

public class ClientStarter {
    public static void main(String[] args) {

        //Conexión al servidor de Temporal
        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(serviceStubs);

        //Ejecución del "Cargo"
        WorkflowOptions optionsCargo = WorkflowOptions.newBuilder()
                .setTaskQueue("Transaccion_TASK_QUEUE")
                .setWorkflowId("cargo-" + UUID.randomUUID())
                .build();
        TransaccionWorkflow transaccionWorkflow = client.newWorkflowStub(TransaccionWorkflow.class,optionsCargo);
        String transaccionResult = transaccionWorkflow.transaccionEnProceso("cargo", "45425425425", 250.0);
        System.out.println("Cargo: " + transaccionResult);

        //Ejecución del "Abono"
        WorkflowOptions optionsAbono = WorkflowOptions.newBuilder()
                .setTaskQueue("Transaccion_TASK_QUEUE")
                .setWorkflowId("cargo-" + UUID.randomUUID())
                .build();
        TransaccionWorkflow transaccionWorkflow2 = client.newWorkflowStub(TransaccionWorkflow.class,optionsAbono);
        String transaccionResult2 = transaccionWorkflow2.transaccionEnProceso("abono", "5664363553", 500.0 );
        System.out.println("Abono: " + transaccionResult2);

        //Finalización del Worker
        System.exit(0);

        }



  }