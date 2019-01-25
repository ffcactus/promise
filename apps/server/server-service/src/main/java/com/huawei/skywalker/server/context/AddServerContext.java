package com.huawei.skywalker.server.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.huawei.skywalker.server.model.Server;
import com.huawei.skywalker.server.repository.ServerRepository;
import com.huawei.skywalker.server.strategy.BasicServerStrategy;
import com.huawei.skywalker.server.strategy.DefaultRackServerAddStrategy;
import com.huawei.skywalker.server.task.ServerTask;
import com.promise.apps.server.sdk.dto.ServerStateV1;
import com.promise.platform.sdk.client.TaskServiceClient;
import com.promise.platform.sdk.dto.task.ExecutionResultStateV1;
import com.promise.platform.sdk.dto.task.ExecutionStateV1;
import com.promise.platform.sdk.dto.task.GetTaskResponseV1;
import com.promise.platform.sdk.dto.task.UpdateExecutionResultRequestV1;
import com.promise.platform.sdk.dto.task.UpdateTaskStepRequestV1;
import com.promise.platform.sdk.model.AsynchResult;

/**
 * Context for add server. After the client call the <tt>run()</tt> method, it
 * can get the task and server from the context.
 *
 */
public class AddServerContext implements ServerOperationContext
{
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
    public AsynchResult<Server> run()
    {
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
    private void asyncAdd()
    {
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

    private void setStepRunning(String stepName)
    {
        taskServiceClient.updateTaskStep(
                task.id,
                new UpdateTaskStepRequestV1(stepName, ExecutionStateV1.Running, null));
    }

    private void setStepFinished(String stepName)
    {
        taskServiceClient.updateTaskStep(
                task.id,
                new UpdateTaskStepRequestV1(
                        stepName,
                        null,
                        new UpdateExecutionResultRequestV1(ExecutionResultStateV1.Finished, null)));
    }
}
