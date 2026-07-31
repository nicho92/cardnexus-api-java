package org.api.cardnexus.webhooks;

import java.util.Date;
import java.util.List;

import org.api.cardnexus.model.Amount;

public record OptimizerRunCompletedHook (OptimizerRunCompletedData data, String eventId, Date timestamp, String type) {}

record OptimizerRunCompletedData(List<OptimizerRunOptions> options, String runId, int unmetCount) {}

record OptimizerRunOptions(List<String> modes, int sellerCount, Amount shipping, Amount subtotal, Amount total) {}