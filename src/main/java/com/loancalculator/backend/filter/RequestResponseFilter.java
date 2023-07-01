package com.loancalculator.backend.filter;

import com.loancalculator.backend.entity.Request;
import com.loancalculator.backend.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Filter
@Component
@RequiredArgsConstructor
public class RequestResponseFilter extends OncePerRequestFilter {
    private final RequestService requestService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        requestService.save(Request.fromHttpReq(request));
        filterChain.doFilter(request, response);
    }



}