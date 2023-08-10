package me.bilousov.requestfilterssandbox.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.Objects;

@Component
@WebFilter("/*")
@RequiredArgsConstructor
public class RequestFilter implements Filter {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Custom logic to be executed before the servlet
        System.out.println("CustomFilter: Before servlet processing");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        try {
            System.out.println(requestMappingHandlerMapping.getHandler(httpServletRequest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        requestMappingHandlerMapping.getHandlerMethods().keySet().forEach(
//                requestMappingInfo -> {
//                    System.out.println("Patterns: " + requestMappingInfo.getPatternsCondition());
//                }
//        );

//        boolean isProperPath = requestMappingHandlerMapping.getHandlerMethods().keySet()
//                .stream()
//                .anyMatch(requestMappingInfo -> {
//                    PatternsRequestCondition patterns = requestMappingInfo.getPatternsCondition();
//                    return patterns.getPatterns().stream().anyMatch(pattern -> pattern.matches(requestURI));
//                });

//        if (isProperPath) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            return;
//        }

        filterChain.doFilter(servletRequest, servletResponse);

        // Custom logic to be executed after the servlet
        System.out.println("CustomFilter: After servlet processing");
    }

    @Override
    public void destroy() {}
}
