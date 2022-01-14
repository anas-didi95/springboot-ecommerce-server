package com.anasdidi.ecommerce.service.producttype;

import java.time.Instant;

final record ProductType(String code, String description, boolean isDeleted, String lastModifiedBy,
    Instant lastModifiedDate,
    Integer version) {
}
