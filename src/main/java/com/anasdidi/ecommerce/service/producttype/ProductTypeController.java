package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.springframework.http.ResponseEntity;

interface ProductTypeController {

  ResponseEntity<ResponseDTO> create();
}
