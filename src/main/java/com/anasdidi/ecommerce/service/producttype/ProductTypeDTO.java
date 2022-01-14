package com.anasdidi.ecommerce.service.producttype;

import java.time.Instant;

public final record ProductTypeDTO(String code, String description, boolean isDeleted, String lastModifiedBy,
    Instant lastModifiedDate,
    Integer version) {
}
