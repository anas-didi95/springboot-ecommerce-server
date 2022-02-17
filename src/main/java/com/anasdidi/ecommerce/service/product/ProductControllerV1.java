package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/product")
class ProductControllerV1 implements ProductController {

  @Override
  @RequestMapping(method = RequestMethod.POST, value = "")
  public Mono<ResponseEntity<ResponseDTO>> create(ServerWebExchange serverWebExchange) {
    return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.builder().id("HELLO").build()));
  }
}
