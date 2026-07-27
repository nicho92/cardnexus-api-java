package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumBulkKind;
import org.api.cardnexus.model.enums.EnumRunStatus;

public record Job(String id, EnumBulkKind kind, EnumRunStatus status, String format, Double progress, JobCount counts, String errorMessage, String downloadUrl, String errorReportUrl, Date expiresAt, Date createdAt) { }



record JobCount( int total, int succeeded, int failed){}