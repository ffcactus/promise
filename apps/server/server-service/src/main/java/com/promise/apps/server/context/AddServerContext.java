package com.promise.apps.server.context;

import com.promise.apps.server.model.Server;
import com.promise.apps.server.repository.ServerRepository;
import com.promise.apps.server.sdk.dto.ServerStateV1;
import com.promise.apps.server.strategy.BasicServerStrategy;
import com.promise.apps.server.strategy.DefaultRackServerAddStrategy;
import com.promise.apps.server.task.ServerTask;
import com.promise.platform.common.model.AsynchResult;
import com.promise.platform.task.sdk.client.TaskServiceClient;
import com.promise.platform.task.sdk.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

/**
 * Context for add server. After the client call the <tt>run()</tt> method, it
 * can get the task and server from the context.
 */
public class AddServerContext implements ServerOperationContext {
    private BasicServerStrategy basicStrategy;
    private DefaultRackServerAddStrategy addStrategy;
    private String id;
    private Server server;
    private GetTaskResponseV1 task;

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private TaskServiceClient taskServiceClient;

    @Override
    public AsynchResult<Server> run() {
        server = serverRepository.findById(id).get();
        server = basicStrategy.lock(id);
        task = taskServiceClient.createTask(ServerTask.generateAddServerTask(null, null, server.uri)).getBody();
        asyncAdd();
        return new AsynchResult<Server>(task.uri, server);
    }

    /**
     * The asynchronous process of adding a server.
     */
    @Async
    public void asyncAdd() {
        setStepRunning(ServerTask.updateBasicInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateBasicInfo.name);

        setStepRunning(ServerTask.updateProcessorInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateProcessorInfo.name);

        setStepRunning(ServerTask.updateMemoryInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateMemoryInfo.name);

        setStepRunning(ServerTask.updateStorageInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateStorageInfo.name);

        setStepRunning(ServerTask.updateManagerInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateManagerInfo.name);

        setStepRunning(ServerTask.updateBiosInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateBiosInfo.name);

        setStepRunning(ServerTask.updateBoardInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateBoardInfo.name);

        setStepRunning(ServerTask.updatePcieInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updatePcieInfo.name);

        setStepRunning(ServerTask.updateAdapterInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateAdapterInfo.name);

        setStepRunning(ServerTask.updatePowerInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updatePowerInfo.name);

        setStepRunning(ServerTask.updateThermalInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateThermalInfo.name);

        setStepRunning(ServerTask.updateFirmwareInfo.name);
        addStrategy.refreshServer(id);
        setStepFinished(ServerTask.updateFirmwareInfo.name);

        setStepRunning(ServerTask.finishServerUpdate.name);
        basicStrategy.setState(id, ServerStateV1.Ready);
        setStepFinished(ServerTask.finishServerUpdate.name);
    }

    private void setStepRunning(String stepName) {
        taskServiceClient.updateTaskStep(
                task.id,
                new UpdateTaskStepRequestV1(stepName, ExecutionStateV1.Running, null));
    }

    private void setStepFinished(String stepName) {
        taskServiceClient.updateTaskStep(
                task.id,
                new UpdateTaskStepRequestV1(
                        stepName,
                        null,
                        new UpdateExecutionResultRequestV1(ExecutionResultStateV1.Finished, null)));
    }
}
