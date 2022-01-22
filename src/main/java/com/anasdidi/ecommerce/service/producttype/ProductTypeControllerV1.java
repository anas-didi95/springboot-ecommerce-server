package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/producttype")
class ProductTypeControllerV1 implements ProductTypeController {

  @Override
  @RequestMapping(method = RequestMethod.GET, value = "")
  public ResponseEntity<ResponseDTO> create() {
    ResponseDTO responseBody = ResponseDTO.builder().id("Hello world").build();
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
