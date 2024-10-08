package com.frankmoley.lil.designpatternsapp.strategy.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponseDto {
  private String message;
  private HttpStatus status;
}