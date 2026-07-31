package org.api.cardnexus.webhooks;

import java.util.Date;
import java.util.List;

public record InventoryQtyChangedHook(InventoryQtyChangedHookData data, String eventId, Date timestamp, String type){ }
record InventoryQtyChangedHookData(List<InventoryQtyChangedHookChange> changes, String reason) {}
record InventoryQtyChangedHookChange(int after, int before,String customId,String inventoryId, int productId) {}