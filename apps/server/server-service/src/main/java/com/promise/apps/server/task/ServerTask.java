package com.promise.apps.server.task;

import com.promise.platform.sdk.dto.task.CreateTaskRequestV1;
import com.promise.platform.sdk.dto.task.CreateTaskStepRequestV1;

import java.util.Arrays;

/**
 * Includes all kinds of server task information.
 */
public class ServerTask {
    public static final CreateTaskStepRequestV1 updateBasicInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateBasicInfo",
            "Update Server basic information.",
            "Update Server basic information.",
            20);

    public static final CreateTaskStepRequestV1 updateProcessorInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateProcessorInfo",
            "Update Server processor information.",
            "Update Server processor information.",
            20);

    public static final CreateTaskStepRequestV1 updateMemoryInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateMemoryInfo",
            "Update Server memory information.",
            "Update Server memory information.",
            20);

    public static final CreateTaskStepRequestV1 updateStorageInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateStorageInfo",
            "Update Server storage information.",
            "Update Server storage information.",
            20);

    public static final CreateTaskStepRequestV1 updateManagerInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateManagerInfo",
            "Update Server manager information.",
            "Update Server manager information.",
            20);

    public static final CreateTaskStepRequestV1 updateBiosInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateBiosInfo",
            "Update Server BIOS information.",
            "Update Server BIOS information.",
            20);

    public static final CreateTaskStepRequestV1 updateBoardInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateBoardInfo",
            "Update Server board information.",
            "Update Server board information.",
            20);

    public static final CreateTaskStepRequestV1 updatePcieInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updatePcieInfo",
            "Update Server PCIe information.",
            "Update Server PCIe information.",
            20);

    public static final CreateTaskStepRequestV1 updateAdapterInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateAdapterInfo",
            "Update Server adapter information.",
            "Update Server adapter information.",
            20);

    public static final CreateTaskStepRequestV1 updatePowerInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updatePowerInfo",
            "Update Server power information.",
            "Update Server power information.",
            20);

    public static final CreateTaskStepRequestV1 updateThermalInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateThermalInfo",
            "Update Server thermal information.",
            "Update Server thermal information.",
            20);

    public static final CreateTaskStepRequestV1 updateFirmwareInfo = new CreateTaskStepRequestV1(
            "message.taskstep.server.updateFirmwareInfo",
            "Update Server firmware information.",
            "Update Server firmware information.",
            20);

    public static final CreateTaskStepRequestV1 finishServerUpdate = new CreateTaskStepRequestV1(
            "message.taskstep.server.finishServerUpdate",
            "finish server update.",
            "finish server update.",
            20);

    // Task definition.
    public static CreateTaskRequestV1 generateAddServerTask(String parentTaskUri, String createdByUri, String targetUri) {
        return new CreateTaskRequestV1(
                "message.task.server.addServer",
                "Add Server",
                "Add the server to the system.",
                parentTaskUri,
                createdByUri,
                targetUri,
                Arrays.asList(
                        updateBasicInfo,
                        updateProcessorInfo,
                        updateMemoryInfo,
                        updateStorageInfo,
                        updateManagerInfo,
                        updateBiosInfo,
                        updateBoardInfo,
                        updatePcieInfo,
                        updateAdapterInfo,
                        updatePowerInfo,
                        updateThermalInfo,
                        updateFirmwareInfo));
    }

}
