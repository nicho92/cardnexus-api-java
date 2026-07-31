package org.api.cardnexus.webhooks;

import java.util.Date;
import java.util.List;

public record InventoryQtyChangedHook(HookData data, String eventId, Date timestamp, String type)
{

}
record HookData(List<Change> changes, String reason) {}
record Change(int after, int before,String customId,String inventoryId, int productId) {}