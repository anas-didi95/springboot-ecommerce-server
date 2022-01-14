package com.anasdidi.ecommerce.service.producttype;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/producttype")
final class ProductTypeControllerV1 implements ProductTypeController {

  @Override
  @RequestMapping(method = RequestMethod.GET, value = "")
  public ResponseEntity<String> create() {
    return ResponseEntity.status(HttpStatus.OK).body("Hello world");
  }
}
