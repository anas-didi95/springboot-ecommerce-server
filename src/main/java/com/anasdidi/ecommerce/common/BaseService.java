package com.anasdidi.ecommerce.common;

import com.anasdidi.ecommerce.exception.RecordAlreadyExistedException;
import com.anasdidi.ecommerce.exception.VersionNotMatchedException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import reactor.core.publisher.Mono;

public abstract class BaseService<T1, T2> {

  public abstract T1 merge(T1 domain, T2 dto);

  public Pageable getPageable(Integer page, Integer size, Sort sort) {
    page = page <= 0 ? 1 : page;
    size = size <= 0 ? 1 : size;
    return PageRequest.of(page - 1, size, sort);
  }

  public Pageable getPageable(Integer page, Integer size) {
    return getPageable(page, size, Sort.unsorted());
  }

  public Mono<T1> checkRecordVersion(T1 domain, Long expectedVersion, Long actualVersion) {
    if (expectedVersion != actualVersion) {
      return Mono.error(new VersionNotMatchedException(expectedVersion, actualVersion));
    }
    return Mono.just(domain);
  }

  public Mono<Boolean> checkRecordExist(Boolean isExist, String value) {
    if (isExist) {
      return Mono.error(new RecordAlreadyExistedException(value));
    }
    return Mono.just(isExist);
  }
}
