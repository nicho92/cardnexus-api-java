package org.api.cardnexus.model;

import org.api.cardnexus.model.enums.EnumAccountStatus;

public record ManagedAccount(String accountId, String username, EnumAccountStatus status) {

}
