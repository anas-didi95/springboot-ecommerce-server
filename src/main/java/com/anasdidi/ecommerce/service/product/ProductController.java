package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

interface ProductController {

  Mono<ResponseEntity<ResponseDTO>> create(ProductDTO requestBody, ServerWebExchange serverWebExchange);

  Mono<ResponseEntity<ResponseDTO>> update(String id, ProductDTO requestBody, ServerWebExchange serverWebExchange);
}
