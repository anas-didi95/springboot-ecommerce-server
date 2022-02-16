package com.anasdidi.ecommerce.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class BaseService {

  public Pageable getPageable(Integer page, Integer size, Sort sort) {
    page = page <= 0 ? 1 : page;
    size = size <= 0 ? 1 : size;
    return PageRequest.of(page - 1, size, sort);
  }

  public Pageable getPageable(Integer page, Integer size) {
    return getPageable(page, size, Sort.unsorted());
  }
}
