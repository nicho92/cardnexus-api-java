package org.api.cardnexus.webhooks;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumRunStatus;

public record ExportRunFinishedHook (ExportRunFinishedHookData data, String eventId, Date timestamp, String type) {}

record ExportRunFinishedHookData(String downloadUrl, Date expiresAt, String jobId, EnumRunStatus status) {}
