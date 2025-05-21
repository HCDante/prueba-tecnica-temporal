package org.example;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerFactoryOptions;
import org.apache.tomcat.util.threads.TaskQueue;
import org.example.transaccion.TransaccionActivitiesImpl;
import org.example.transaccion.TransaccionWorkflow;
import org.example.transaccion.TransaccionWorkflowImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Starter {
    public static void main(String[] args) {
        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(serviceStubs);

        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("Transaccion_TASK_QUEUE");

        worker.registerWorkflowImplementationTypes(TransaccionWorkflowImpl.class);
        worker.registerActivitiesImplementations(new TransaccionActivitiesImpl());

        factory.start();

        TransaccionWorkflow transaccionWorkflow = client.newWorkflowStub(
                TransaccionWorkflow.class, WorkflowOptions.newBuilder().setTaskQueue("Transaccion_TASK_QUEUE").build());
        String transaccionResult = transaccionWorkflow.transaccionEnProceso("cargo", "45425425425", 250.0);
        System.out.println("Cargo: " + transaccionResult);
        String transaccionResult2 = transaccionWorkflow.transaccionEnProceso("abono", "5664363553", 500.0 );
        System.out.println("Abono: " + transaccionResult2);

        System.exit(0);

        }



  }