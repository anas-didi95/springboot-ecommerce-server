package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.ResponseDTO;
import com.anasdidi.ecommerce.common.BaseValidator.ValidateAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/product")
class ProductControllerV1 implements ProductController {

  private final Logger logger = LoggerFactory.getLogger(ProductControllerV1.class);
  private final ProductService productService;
  private final ProductValidator productValidator;

  @Autowired
  ProductControllerV1(ProductService productService, ProductValidator productValidator) {
    this.productService = productService;
    this.productValidator = productValidator;
  }

  @Override
  @RequestMapping(method = RequestMethod.POST, value = "")
  public Mono<ResponseEntity<ResponseDTO>> create(@RequestBody ProductDTO requestBody,
      ServerWebExchange serverWebExchange) {
    String logPrefix = serverWebExchange.getLogPrefix();

    logger.debug("[create]{}requestBody={}", logPrefix, requestBody);

    return Mono.just(requestBody)
        .flatMap(dto -> productValidator.validate(ValidateAction.CREATE, dto, logPrefix))
        .flatMap(dto -> productService.create(dto, logPrefix))
        .map(result -> ResponseDTO.builder().id(result.getId()).build())
        .map(responseBody -> ResponseEntity.status(HttpStatus.CREATED).body(responseBody));
  }

  @Override
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  public Mono<ResponseEntity<ResponseDTO>> update(@PathVariable String id, @RequestBody ProductDTO requestBody,
      ServerWebExchange serverWebExchange) {
    String logPrefix = serverWebExchange.getLogPrefix();

    logger.debug("[update]{}id={}, requestBody={}", logPrefix, id, requestBody);

    return Mono
        .just(
            ProductDTO.builder().id(id).description(requestBody.getDescription()).isDeleted(requestBody.getIsDeleted())
                .price(requestBody.getPrice()).productTypeCode(requestBody.getProductTypeCode())
                .title(requestBody.getTitle()).version(requestBody.getVersion()).build())
        .flatMap(dto -> productValidator.validate(ValidateAction.UPDATE, dto, logPrefix))
        .flatMap(dto -> productService.update(dto, logPrefix))
        .map(result -> ResponseDTO.builder().id(result.getId()).build())
        .map(responseBody -> ResponseEntity.status(HttpStatus.OK).body(responseBody));
  }
}
