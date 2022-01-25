package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.BaseValidator.ValidateAction;
import com.anasdidi.ecommerce.common.ResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/producttype")
class ProductTypeControllerV1 implements ProductTypeController {

  private static final Logger logger = LoggerFactory.getLogger(ProductTypeControllerV1.class);
  private final ProductTypeService productTypeService;
  private final ProductTypeValidator productTypeValidator;

  ProductTypeControllerV1(ProductTypeService productTypeService, ProductTypeValidator productTypeValidator) {
    this.productTypeService = productTypeService;
    this.productTypeValidator = productTypeValidator;
  }

  @Override
  @RequestMapping(method = RequestMethod.POST, value = "")
  public Mono<ResponseEntity<ResponseDTO>> create(@RequestBody ProductTypeDTO requestBody,
      ServerWebExchange serverWebExchange) {
    String logPrefix = serverWebExchange.getLogPrefix();

    logger.debug("[create]{}{}", logPrefix, requestBody);

    return Mono.just(requestBody)
        .flatMap(dto -> productTypeValidator.validate(ValidateAction.CREATE, dto, logPrefix))
        .flatMap(dto -> productTypeService.create(dto, logPrefix))
        .map(result -> ResponseDTO.builder().code(result.getCode()).build())
        .map(responseBody -> ResponseEntity.status(HttpStatus.CREATED).body(responseBody));
  }
}
