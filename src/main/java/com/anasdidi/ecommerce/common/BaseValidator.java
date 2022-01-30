package com.anasdidi.ecommerce.common;

import java.util.List;

import com.anasdidi.ecommerce.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import reactor.core.publisher.Mono;

public abstract class BaseValidator<T extends BaseDTO> {

  private static final Logger logger = LoggerFactory.getLogger(BaseValidator.class);

  public enum ValidateAction {
    CREATE, UPDATE
  }

  protected List<String> validateCreate(T dto) {
    return null;
  };

  protected List<String> validateUpdate(T dto) {
    return null;
  }

  public Mono<T> validate(ValidateAction action, T dto, String logPrefix) {
    List<String> errorList = switch (action) {
      case CREATE -> validateCreate(dto);
      case UPDATE -> validateUpdate(dto);
    };

    if (errorList == null) {
      logger.warn("[validate]{}Validator not implemented! Skipping validation action={}", logPrefix, action);
    } else if (!errorList.isEmpty()) {
      logger.error("[validate]{}{}", logPrefix, dto);
      return Mono.error(new ValidationException(errorList));
    }

    return Mono.just(dto);
  }

  protected void isMandatoryField(List<String> errorList, String field, Object value) {
    String template = "[%s] is mandatory field!";
    boolean isFail = (value instanceof String s && !StringUtils.hasText(s)) || (value == null);

    if (isFail) {
      errorList.add(String.format(template, field));
    }
  }
}
