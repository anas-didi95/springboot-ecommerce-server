package com.anasdidi.ecommerce.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseDTO {

  private String id;
}
