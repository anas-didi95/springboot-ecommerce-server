package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

interface ProductTypeController {

  Mono<ResponseEntity<ResponseDTO>> create(ProductTypeDTO requestBody, ServerWebExchange serverWebExchange);

  Mono<ResponseEntity<ResponseDTO>> update(String code, ProductTypeDTO requestBody,
      ServerWebExchange serverWebExchange);
}
