package com.stefanini.pruebatecnica.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SessionFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String transactionId = request.getHeader("X-Transaction-Id");

    if (transactionId == null || transactionId.isBlank()) {
      transactionId = UUID.randomUUID().toString();
    }

    MDC.put("transactionId", transactionId);
    response.setHeader("X-Transaction-Id", transactionId);

    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.clear();
    }
  }
}
