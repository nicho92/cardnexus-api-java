package org.api.cardnexus.model;

import java.util.Date;

public record Balance (Date updatedAt,Amount available,Amount pending) {}
        