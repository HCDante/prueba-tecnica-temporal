package org.example;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.example.transaccion.TransaccionActivitiesImpl;
import org.example.transaccion.TransaccionWorkflowImpl;

public class WorkerStarter {

    public static void main(String[] args) {

        //Conexión al servicio de Temporal
        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(serviceStubs);

        //Creación de Worker Factory y Worker
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("Transaccion_TASK_QUEUE");

        //Registro de Workflow y Activities
        worker.registerWorkflowImplementationTypes(TransaccionWorkflowImpl .class);
        worker.registerActivitiesImplementations(new TransaccionActivitiesImpl());

        //Inicialización del Worker
        factory.start();

    }
}
