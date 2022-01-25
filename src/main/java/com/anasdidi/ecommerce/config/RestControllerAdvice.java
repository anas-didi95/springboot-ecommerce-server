package com.anasdidi.ecommerce.config;

import java.util.List;

import com.anasdidi.ecommerce.common.ResponseDTO;
import com.anasdidi.ecommerce.common.CommonConstants.Error;
import com.anasdidi.ecommerce.exception.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);
  private final MessageUtils messageUtils;

  @Autowired
  public RestControllerAdvice(MessageUtils messageUtils) {
    this.messageUtils = messageUtils;
  }

  @ExceptionHandler(ValidationException.class)
  public Mono<ResponseEntity<ResponseDTO>> handleValidationException(ValidationException ex,
      ServerWebExchange serverWebExchange) {
    String logPrefix = serverWebExchange.getLogPrefix();
    String requestId = getRequestId(logPrefix);
    Error error = Error.VALIDATION;
    String code = error.code;
    String message = messageUtils.getErrorMessage(error);
    List<String> errorList = ex.getErrorList();

    logger.error("[handleValidationException]{}code={}, message={}, errorList.size={}", logPrefix, code, message,
        errorList.size());

    ResponseDTO responseBody = ResponseDTO.builder().code(code).message(message).errorList(errorList)
        .requestId(requestId).build();
    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody));
  }

  private String getRequestId(String logPrefix) {
    return logPrefix.trim().replace("[", "").replace("]", "");
  }
}
