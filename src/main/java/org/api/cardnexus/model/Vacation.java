package org.api.cardnexus.model;

import java.util.Date;

import org.api.cardnexus.model.enums.EnumAccountStatus;

public record Vacation (boolean enabled, EnumAccountStatus status, Date since)
{
     
}
