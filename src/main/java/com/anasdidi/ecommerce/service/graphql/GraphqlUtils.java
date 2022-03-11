package com.anasdidi.ecommerce.service.graphql;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

final class GraphqlUtils {

  static final String getFormattedDate(Instant date, String format) {
    if (format == null) {
      return date.toString();
    }
    return new SimpleDateFormat(format).format(Date.from(date));
  }
}
