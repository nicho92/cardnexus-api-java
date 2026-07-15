package org.api.cardnexus.model;

import java.util.Date;

public record Payout (Amount amount,Date eligibleAt,Date paidOutAt)
{
    
}